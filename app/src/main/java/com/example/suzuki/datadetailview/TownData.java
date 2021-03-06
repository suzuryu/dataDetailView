package com.example.suzuki.datadetailview;

public class TownData
{
    private String  cityName;
    private int     schoolCount;
    private String  prefecture;
    private int     stationCount;
    private Double  crimePer;
    private int     population;

    TownData(){
        this.cityName = "NO NAME";
        this.schoolCount = 0;
        this.prefecture = "NO PREFECTURE";
        this.stationCount = 0;
        this.crimePer = 0.0;
        this.population = 0;
    }

    public String getCityName() {
        return cityName;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() { return population; }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setSchoolCount(int schoolCount) {
        this.schoolCount = schoolCount;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public int getStationCount() {
        return stationCount;
    }

    public Double getCrimePer() {
        return crimePer;
    }

    public int getSchoolCount() {
        return schoolCount;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public void setCrimePer(Double crimePer) {
        this.crimePer = crimePer;
    }

    public void setStationCount(int stationCount) {
        this.stationCount = stationCount;
    }
}
