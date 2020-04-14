import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import web.Brdagelabz.CensusAnalyser.CensusAnalyserException;
import web.Brdagelabz.CensusAnalyser.IndiannCensusAnalyser;

import java.io.FileNotFoundException;
import java.io.IOException;

import static web.Brdagelabz.CensusAnalyser.CensusAnalyserException.*;

public class TestCases
{
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_PATH = "./src/main/resources/IndiaStateCensusData.csv";
//@Before
//{

//}
    @Test
    public void checkTheNumOfRecordsReturnsHappy() throws IOException, CensusAnalyserException {
          IndiannCensusAnalyser indiannCensusAnalyser= new IndiannCensusAnalyser();
        Assert.assertEquals(29, indiannCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH));
    }

    @Test
    public void WhenFilePathIsWrong() {

        try {
            IndiannCensusAnalyser indiannCensusAnalyser= new IndiannCensusAnalyser();
            ExpectedException exception=ExpectedException.none();
            exception.expect(CensusAnalyserException.class);
            indiannCensusAnalyser.loadIndiaCensusData(WRONG_PATH);
        } catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND,e.getMessage());
        }


    }
}
