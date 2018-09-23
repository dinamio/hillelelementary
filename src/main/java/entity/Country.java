package entity;

public class Country {
    private int countryId;
    private String countryName;
    private String countryHead;
    private double countrySquare;
    private int population;

    public Country(){

    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryHead() {
        return countryHead;
    }

    public void setCountryHead(String countryHead) {
        this.countryHead = countryHead;
    }

    public double getCountrySquare() {
        return countrySquare;
    }

    public void setCountrySquare(double countrySquare) {
        this.countrySquare = countrySquare;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", countryHead='" + countryHead + '\'' +
                ", countrySquare=" + countrySquare +
                ", population=" + population +
                '}';
    }
}

