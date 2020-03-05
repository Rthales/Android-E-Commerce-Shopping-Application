package com.example.weshopapplication.ApplicationLayer;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.weshopapplication.DataLayer.ContactUsDatabaseManipulator;
import com.example.weshopapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CheckComplaints extends ListActivity {
    public int idToModify;
    TextView selection;
    ContactUsDatabaseManipulator manipulator;

    List<String[]> listOfComplaints = new ArrayList<>();
    List<String[]> listOfUsernames = null;

    String[] displayDataStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_complaints);


        manipulator = new ContactUsDatabaseManipulator(this);
        listOfUsernames = manipulator.selectAllData();

        displayDataStrings = new String[listOfUsernames.size()];
        int index = 0;
        String temp;

        for (String[] usernames : listOfUsernames) {
            temp = usernames[1] + " - " + usernames[2] + " - " + usernames[3] + " - " + usernames[4];
            displayDataStrings[index] = temp;
            index++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(CheckComplaints.this, android.R.layout.simple_list_item_1, displayDataStrings);
        this.setListAdapter(adapter);
        selection = findViewById(R.id.check_selection);
    }

    public void onListItemClick(ListView parent, View view, int position, long id) {
        selection.setText(displayDataStrings[position]);
    }
}
