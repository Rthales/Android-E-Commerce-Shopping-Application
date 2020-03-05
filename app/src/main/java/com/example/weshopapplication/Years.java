package com.example.weshopapplication;

public class Years {
    private String year;

    public Years(String year) {
        this.year = year;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return " " + year + " ";
    }
}
