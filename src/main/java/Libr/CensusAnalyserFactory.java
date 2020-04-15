package Libr;

import Libr.ICSVBuilder;
import Libr.OpenCSVBuilder;

public class CensusAnalyserFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilder();

    }
}
