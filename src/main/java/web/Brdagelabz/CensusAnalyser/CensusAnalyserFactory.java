package web.Brdagelabz.CensusAnalyser;

public class CensusAnalyserFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilder();

    }
}
