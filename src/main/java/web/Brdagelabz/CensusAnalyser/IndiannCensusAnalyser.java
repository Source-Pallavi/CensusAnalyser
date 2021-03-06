package web.Brdagelabz.CensusAnalyser;

import Libr.CSVBuilderException;
import Libr.CensusAnalyserFactory;
import Libr.ICSVBuilder;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import web.Brdagelabz.CSVBuilderFactory.CSVBuilderFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IndiannCensusAnalyser {
    public enum Country{
        INDIA,US;
    }
    private Object censusList;
    private Object Comparator;
    public String getStateWiseSortedCensusdata() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No census data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List<CensusDAO> stateCensusList=censusMap.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        List list = this.sortInAscendingOrder(stateCensusList, censusComparator);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }


    public String getPopulationWiseSortedState() throws CensusAnalyserException, IOException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No census data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
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


    private void sortAscending( Comparator<IndiaCensusDAO> censusComparator) {
        for (int i = 0; i < censusList.size() - 1; i++) {
            for (int j = 0; j < censusList.size()-i-1; j++) {
                IndiaCensusDAO census1 = censusList.get(j);
                IndiaCensusDAO census2 = censusList.get(j + 1);
                if (censusComparator.compare(census1, census2) >0) {
                    censusList.set(j, census2);
                    censusList.set(j + 1, census1);
                }

            }

        }
    }


    private void sortDescending( Comparator<IndiaCensusDAO> censusComparator) {
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
    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if(censusList==null || censusList.size() == 0){
            throw new CensusAnalyserException("No census data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        this.sortAscending(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(this.censusList);
        return sortedStateCensusJson;

    }
    private void sortAscending( Comparator<CensusDAO> censusComparator) {
        for (int i = 0; i < censusList.size() - 1; i++) {
            for (int j = 0; j < censusList.size()-i-1; j++) {
                CensusDAO census1 = censusList.get(j);
                CensusDAO census2 = censusList.get(j + 1);
                if (censusComparator.compare(census1, census2) >0) {
                    censusList.set(j, census2);
                    censusList.set(j + 1, census1);
                }

            }

        }
    }

    private <E> int loadCensusData(String csvFilePath, Class<E> censusCSVClass) throws CensusAnalyserException {
        if (!csvFilePath.contains(".csv")) {
            throw new CensusAnalyserException("Enter proper file Extension",
                    CensusAnalyserException.ExceptionType.TYPE_EXTENSION_WRONG);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvfileIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            Iterable<E> csvIterable = () -> csvfileIterator;
            if (censusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.state, new CensusDAO(censusCSV)));
            }else  if (censusCSVClass.getName().equals("censusanalyser.USCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.state, new CensusDAO(censusCSV)));
            }
            return censusMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
}
