package web.Brdagelabz.CensusAnalyser;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CensusAnalyser {
    Reader reader = Files.newBufferedReader(Paths.get(StateCensusData.csv));
    CSVReader csvReader = new CSVReader(reader);
}
