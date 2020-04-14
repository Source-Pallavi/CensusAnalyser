package web.Brdagelabz.CensusAnalyser;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class IndiannCensusAnalyser
{
    public  int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {

        try {
            Reader Reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndiaCensusCSV> cadToBeanBuilder = new CsvToBeanBuilder<IndiaCensusCSV>(Reader);
            CsvToBean csvToBean = cadToBeanBuilder.withType(IndiaCensusCSV.class).withIgnoreLeadingWhiteSpace(true).build();
            Iterator<IndiaCensusCSV> iterator = csvToBean.iterator();
            int countEnteries = 0;
            while (iterator.hasNext())
            {
                countEnteries++;
                IndiaCensusCSV dataCSV = iterator.next();
            }
            return countEnteries;
        } catch (FileNotFoundException e)
        {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.FILE_NOT_FOUND);

        }
    }
}
