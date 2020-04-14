import org.junit.Assert;
import org.junit.Test;
import web.Brdagelabz.CensusAnalyser.CensusAnalyserException;
import web.Brdagelabz.CensusAnalyser.IndiannCensusAnalyser;

import java.io.IOException;

public class TestCases
{
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";

    @Test
    public void checkTheNumOfRecordsReturnsHappy() throws IOException, CensusAnalyserException {
        Assert.assertEquals(29, IndiannCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH));
    }
}
