package entity;

public class City {
    private int cityId;
    private String cityName;
    private String cityHead;
    private int countryId;

    public City(){

    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityHead() {
        return cityHead;
    }

    public void setCityHead(String cityHead) {
        this.cityHead = cityHead;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", cityHead='" + cityHead + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
