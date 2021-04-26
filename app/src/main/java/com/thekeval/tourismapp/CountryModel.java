package com.thekeval.tourismapp;

import java.util.ArrayList;
import java.util.HashMap;

public class CountryModel {
    String name;
    public String flag;
    String capital;
    ArrayList<PlaceModel> poi;

    public CountryModel(String name, String flag, String capital, ArrayList<PlaceModel> poi) {
        this.name = name;
        this.flag = flag;
        this.capital = capital;
        this.poi = poi;
    }

    // region getters & setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public ArrayList<PlaceModel> getPoi() {
        return poi;
    }

    public void setPoi(ArrayList<PlaceModel> poi) {
        this.poi = poi;
    }

    // endregion


    @Override
    public String toString() {
        return "CountryModel{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", poi=" + poi +
                '}';
    }
}
