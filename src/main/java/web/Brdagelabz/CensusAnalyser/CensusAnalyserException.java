package web.Brdagelabz.CensusAnalyser;

public class CensusAnalyserException extends Exception
{

   public  enum ExceptionType {
        FILE_NOT_FOUND,PARSE_PROBLEM;
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
