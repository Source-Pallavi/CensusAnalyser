package web.Brdagelabz.CensusAnalyser;
import Libr.CSVBuilderException;
import Libr.CensusAnalyserFactory;
import Libr.ICSVBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.bridgelabz.CSVBuilderFactory.CSVBuilderException;
import com.bridgelabz.CSVBuilderFactory.CSVBuilderFactory;
import com.bridgelabz.CSVBuilderFactory.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.StreamSupport;
import web.Brdagelabz.CSVBuilderFactory.CSVBuilderException;
import web.Brdagelabz.CSVBuilderFactory.CSVBuilderFactory;
import web.Brdagelabz.CSVBuilderFactory.ICSVBuilder;

public class IndiannCensusAnalyser {
    private Object censusList;
    private Object Comparator;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {

        try {
            Reader Reader;
             Reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndiaCensusCSV> cadToBeanBuilder = new CsvToBeanBuilder<IndiaCensusCSV>(Reader);
            CsvToBean csvToBean = cadToBeanBuilder.withType(IndiaCensusCSV.class).withIgnoreLeadingWhiteSpace(true).build();
            Iterator<IndiaCensusCSV> iterator = csvToBean.iterator();
            Iterable<IndiaCensusCSV> Iterable = () -> iterator;
            int countEnteries = (int) StreamSupport.stream(Iterable.spliterator(), false).count();
            return countEnteries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.FILE_NOT_FOUND);

        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.PARSE_PROBLEM);

        }
    }

    public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CensusAnalyserFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> stateCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            return this.getCount(stateCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch(RuntimeException e){
            throw new CensusAnalyserException("Delimiter mismatch",
                    CensusAnalyserException.ExceptionType.FILE_DELIMITER_MISMATCH);
        }
        catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),e.type.name());
        }

    }








    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int numOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numOfEnteries;
    }


    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("No census data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(this.censusList);
        return sortedStateCensusJson;

    }

    private void sort(Comparator<IndiaCensusDAO> censusComparator) {
        for (int i = 0; i < censusList.size() - 1; i++) {
            for (int j = 0; j < censusList.size() - i - 1; j++) {
                IndiaCensusDAO census1 = censusList.get(j);
                IndiaCensusDAO census2 = censusList.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    censusList.set(j, census2);
                    censusList.set(j + 1, census1);
                }

            }

        }


        private void sort( Comparator<IndiaCensusDAO> censusComparator) {
            for (int i = 0; i < censusList.size() - 1; i++) {
                for (int j = 0; j < censusList.size()-i-1; j++) {
                    IndiaCensusDAO census1 = censusList.get(j);
                    IndiaCensusDAO census2 = censusList.get(j + 1);
                    if (censusComparator.compare(census1, census2) <0) {
                        censusList.set(j, census2);
                        censusList.set(j + 1, census1);
                    }

                }

            }
        }
}

    public String getMostPopulationDensitySqPerkm() throws CensusAnalyserException {
        if(censusList==null || censusList.size() == 0){
            throw new CensusAnalyserException("No census data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        this.sort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(this.censusList);
        return sortedStateCensusJson;

    }
    }
