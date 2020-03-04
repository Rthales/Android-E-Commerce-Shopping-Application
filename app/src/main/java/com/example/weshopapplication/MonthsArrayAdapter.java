package com.example.weshopapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MonthsArrayAdapter extends ArrayAdapter<Months> {
    private Context context;
    private ArrayList<Months> listOfMonths = null;

    public MonthsArrayAdapter(Context context, ArrayList<Months> listOfMonths) {
        super(context, 0, listOfMonths);
        this.context = context;
        this.listOfMonths = listOfMonths;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listOfItems = convertView;

        if (listOfItems == null) {
            listOfItems = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);

            Months theMonths = listOfMonths.get(position);
        }

        return listOfItems;
    }

}
