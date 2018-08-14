package com.example.suzuki.datadetailview;

public class TownData
{
    private String data_name_;
    private String data_val_;

    TownData(){
        this.data_name_ = "NO NAME";
        this.data_val_ = "NO DATA";
    }

    TownData(String name, String value){
        this.data_name_ = name;
        this.data_val_ = value;
    }

    public void setData_name_(String data_name_) {
        this.data_name_ = data_name_;
    }

    public void setData_val_(String data_val_) {
        this.data_val_ = data_val_;
    }

    public String getData_name_() {
        return data_name_;
    }

    public String getData_val_() {
        return data_val_;
    }
}
