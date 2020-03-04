package com.example.weshopapplication;

public class Months {
    private String month;

    public Months(String month) {

        this.month = month;
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
