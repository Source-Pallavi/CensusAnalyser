import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import web.Brdagelabz.CensusAnalyser.CensusAnalyserException;
import web.Brdagelabz.CensusAnalyser.IndiaCensusCSV;
import web.Brdagelabz.CensusAnalyser.IndiaStateCodeCSV;
import web.Brdagelabz.CensusAnalyser.IndiannCensusAnalyser;
import web.Brdagelabz.CSVBuilderFactory.CSVBuilderException;
import web.Brdagelabz.CSVBuilderFactory.CSVBuilderFactory;
import web.Brdagelabz.CSVBuilderFactory.ICSVBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;

import static web.Brdagelabz.CensusAnalyser.CensusAnalyserException.*;

public class TestCases
{
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String HEADER_INCORRECT= "./src/test/resources/ChangeHeader.csv";
    private static final String INCORRECT_FILE_TYPE= "./src/test/resources/IndiaStateCensusData.pdf";
    private static final String INDIA_CENSUS_CSV_INCORRECT_DELIMITER = "./src/test/resources/withoutcommavalues.csv";
    private static final String INDIAN_STATE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";

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
            IndiannCensusAnalyser indiannCensusAnalyser = new IndiannCensusAnalyser();
            ExpectedException exception = ExpectedException.none();
            exception.expect(CensusAnalyserException.class);
            indiannCensusAnalyser.loadIndiaCensusData(WRONG_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND,
                    e.getMessage());
        }
    }
        @Test
        public void givenIndiaCensusData_WithWrongFileExtension_ShouldThrowException(){
            try {
                IndiannCensusAnalyser censusAnalyser=new IndiannCensusAnalyser();
                censusAnalyser.loadIndiaCensusData(INCORRECT_FILE_TYPE);
            } catch (CensusAnalyserException e) {
                Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_EXTENSION_MISMATCH,e.type);
            }
        }
        @Test
        public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException(){
            try {
                IndiannCensusAnalyser indiannCensusAnalyser= new IndiannCensusAnalyser();
                indiannCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_INCORRECT_DELIMITER);
            } catch (CensusAnalyserException e) {
                Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_DELIMITER_MISMATCH,e.type);
            }
        }
        @Test
        public void givenIndiaCensusData_HeaderIncorrect_ShouldThrowException(){
            try {
                IndiannCensusAnalyser indiannCensusAnalyser= new IndiannCensusAnalyser();
                indiannCensusAnalyser.loadIndiaCensusData(HEADER_INCORRECT);
            } catch (CensusAnalyserException e) {
                Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_DELIMITER_MISMATCH,e.type);
            }
        }

        @Test
        public void givenIndianCSV_ShouldReturnExactCount() throws CensusAnalyserException {
            IndiannCensusAnalyser indiannCensusAnalyser = new IndiannCensusAnalyser();
            int numOfStateCode = indiannCensusAnalyser.loadIndianStateCode(INDIAN_STATE_CSV_FILE_PATH);
            Assert.assertEquals(37,numOfStateCode);
        }

        @Test
        public void givenStateData_WhenSortedState_ShouldReturnSortResult() throws CensusAnalyserException {
            IndiannCensusAnalyser indiannCensusAnalyser = new IndiannCensusAnalyser();
            indiannCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = indiannCensusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh",censusCSV[0].state);
        }

    @Test
    public void givenStateData_WhenSortedStateCode_ShouldReturnSortResult() throws CensusAnalyserException {
        IndiannCensusAnalyser indiannCensusAnalyser = new IndiannCensusAnalyser();
        indiannCensusAnalyser.loadIndiaCensusData(INDIA_STATE_CODE);
        String sortedCensusData = indiannCensusAnalyser.getStateWiseSortedCensusData();
        IndiaStateCodeCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaStateCodeCSV[].class);
        Assert.assertEquals("AD",censusCSV[0].stateCode);
    }
    @Test
    public void givenStateData_MostPopulousState() throws CensusAnalyserException {
        IndiannCensusAnalyser indiannCensusAnalyser = new IndiannCensusAnalyser();
        indiannCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = indiannCensusAnalyser.getStateWiseSortedCensusData();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals(199812341,censusCSV[0].population);
    }
    @Test
    public void givenStateData_MostPopulousDensityState() throws CensusAnalyserException {
        IndiannCensusAnalyser indiannCensusAnalyser = new IndiannCensusAnalyser();
        indiannCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = indiannCensusAnalyser.getMostPopulationDensitySqPerkm();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals(1102,censusCSV[0].densityPerSqKm);
    }
    @Test
    public void givenStateData_WhenSortedStateCode_ShouldReturnSortResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndianStateCode(INDIA_STATE_CODE);
        String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
        IndiaStateCodeCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaStateCodeCSV[].class);
        Assert.assertEquals("AD",censusCSV[0].stateCode);
    }
    @Test
    public void givenStateData_MostPopulousState() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals(199812341,censusCSV[0].population);
    }
    @Test
    public void givenStateData_MostPopulousDensityState() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getMostPopulationDensityState();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals(1102,censusCSV[0].densityPerSqKm);
    }
    @Test
    public void givenStateData_MostPopulousDensitySqPerkmState() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getMostPopulationDensitySqPerkm();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals(94163,censusCSV[0].areaInSqKm);
    }
    @Test
    public void givenUsCensusData_ShouldReturnCorrectData() throws CensusAnalyserException   {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        int usCensusDataCount = censusAnalyser.loadUsCensusData(US_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(51,usCensusDataCount);
    }


}
