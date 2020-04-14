package web.Brdagelabz.CensusAnalyser;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class IndiannCensusAnalyser
{
    public static  int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException, IOException
    {
        Reader Reader;
        Reader = Files.newBufferedReader(Paths.get(csvFilePath));
        CsvToBeanBuilder<IndiaCensusCSV> cadToBeanBuilder = new CsvToBeanBuilder<IndiaCensusCSV>(Reader).withType(IndiaCensusCSV.class).withIgnoreLeadingWhiteSpace(true);
      CsvToBean csvToBean=cadToBeanBuilder.build();
        Iterator<IndiaCensusCSV> iterator=csvToBean.iterator();
        int countEnteries= 0;
        while (iterator.hasNext())
        {
            countEnteries++;
            IndiaCensusCSV dataCSV=iterator.next();

        }
        return countEnteries;
    }
}
