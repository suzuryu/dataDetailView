package com.example.suzuki.datadetailview;

public class schoolData {

    private String name;
    private int peopleNum;

    schoolData(String name, int peopleName){
        this.name = name;
        this.peopleNum = peopleNum;
    }
    // public void setSchoolData(String name, int peopleNum){
    //   this.name = name;
    //   this.peopleNum = peopleNum;
    // }

    public String getName(){
        return this.name;
    }

    public int getPeopleNum(){
        return this.peopleNum;
    }

}
