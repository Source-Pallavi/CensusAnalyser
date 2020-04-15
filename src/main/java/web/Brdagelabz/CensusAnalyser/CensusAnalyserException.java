package web.Brdagelabz.CensusAnalyser;

public class CensusAnalyserException extends Exception
{

    public CensusAnalyserException(String message, String name) {
    }

    public  enum ExceptionType {
        FILE_NOT_FOUND,PARSE_PROBLEM, FILE_EXTENSION_MISMATCH,FILE_DELIMITER_MISMATCH,NO_CENSUS_DATA,CENSUS_FILE_PROBLEM;
    }

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message,cause);
        this.type = type;
    }
}
