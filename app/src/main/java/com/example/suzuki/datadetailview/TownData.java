package com.example.suzuki.datadetailview;

public class TownData
{
    private String data_name_;
    private String data_val_;
    private int school_num;
    private String prefecture;
    private int station_num;
    private Double crime_per;
    private int people_num;

    public void setPeople_num(int people_num) {
        this.people_num = people_num;
    }

    public int getPeople_num() {

        return people_num;
    }
    /*
        temperture
        station_count
        people_count
        shop_count

    private int station_num;
    */

    TownData(){
        this.data_name_ = "NO NAME";
        this.data_val_ = "NO DATA";
    }
    TownData(String name, int school_num){
        this.data_name_ = name;
        this.school_num = school_num;
    }

    TownData(String name, String value, int school_num){
        this.data_name_ = name;
        this.data_val_ = value;
        this.school_num = school_num;
    }

    public void setData_name_(String data_name_) {
        this.data_name_ = data_name_;
    }

    public void setData_val_(String data_val_) {
        this.data_val_ = data_val_;
    }

    public void setSchool_num(int school_num) {
        this.school_num = school_num;
    }


    public String getPrefecture() {
        return prefecture;
    }

    public int getStation_num() {
        return station_num;
    }

    public Double getCrime_per() {
        return crime_per;
    }

    public String getData_name_() {
        return data_name_;
    }

    public String getData_val_() {
        return data_val_;
    }

    public int getSchool_num() {
        return school_num;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public void setCrime_per(Double crime_per) {
        this.crime_per = crime_per;
    }

    public void setStation_num(int station_num) {
        this.station_num = station_num;
    }
}
