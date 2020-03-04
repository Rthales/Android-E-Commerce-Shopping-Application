package com.example.weshopapplication;

public class Months {
    private int monthID;
    private String month;

    public Months(int monthID, String month) {
        this.monthID = monthID;
        this.month = month;
    }

    public int getMonthID() {
        return monthID;
    }

    public void setMonthID(int monthID) {
        this.monthID = monthID;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return " " + this.month + " ";
    }
}
