package web.Brdagelabz.CensusAnalyser;

public class IndiaCensusDAO {  public int population;
    public  int densityPerSqKm;
    public   int areaInSqKm;
    public String state;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        this.state=indiaCensusCSV.state;
        this.areaInSqKm=indiaCensusCSV.areaInSqKm;
        this.densityPerSqKm=indiaCensusCSV.densityPerSqKm;
        this.population=indiaCensusCSV.population;
    }
}
