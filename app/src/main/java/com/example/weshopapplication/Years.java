package com.example.weshopapplication;

public class Years {
    private int year;

    public Years(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return " " + year + " ";
    }
}
