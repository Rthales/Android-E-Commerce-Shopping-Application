package com.example.weshopapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

// Author of Application: Sabin Constantin Lungu
// Purpose of Application: To create a SQLite database to store the data filled in the contact us form activity
// Date of Last Modification: 15/02/2020
// Any Bugs? No. Will be tested with JUnit

public class DatabaseManipulator {

    private static final String DATABASE_NAME = "complaints.db"; // The Database name to create
    private static int DB_VERSION = 1; // Database version

    private static final String TABLE_NAME = "issues"; // The table name
    private static Context context;

    private static final String INSERT_DATA = "INSERT INTO " + TABLE_NAME
            + " (username, email, phone_number, problem) VALUES (?,?,?,?)";
    private SQLiteStatement sqlStatement; // The SQL statement

    private static SQLiteDatabase db;

    public DatabaseManipulator(Context context) {
        DatabaseManipulator.context = context;
        OpenHelper helper = new OpenHelper(DatabaseManipulator.context);
        DatabaseManipulator.db = helper.getWritableDatabase();

        this.sqlStatement = DatabaseManipulator.db.compileStatement(INSERT_DATA);

    }

    // Routine that inserts data into the table
    public long insert(String username, String email, String phone_number, String problem) { // Routine to insert data into the table
        this.sqlStatement.bindString(1, username);
        this.sqlStatement.bindString(2, email);

        this.sqlStatement.bindString(3, phone_number);
        this.sqlStatement.bindString(4, problem);

        return this.sqlStatement.executeInsert();
    }


    public void deleteAllData() { // Routine to delete all the data from the DB
        db.delete(TABLE_NAME, null, null); // Deletes the table if required
    }

    public List<String[]> selectAllData() { // Routine to select all the data from the db
        List<String[]> listOfComplaints = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, new String[]{"id", "username", "email", "phone_number", "problem"}, null, null, null, null, "username ASC");

        int index = 0;

        if (cursor.moveToFirst()) {

            do {

                String[] complaints_data = new String[]{cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)};

                listOfComplaints.add(complaints_data); // Add the retrieved data to the array list
                index++; // Increment the index

            } while (cursor.moveToNext()); // While loop to go to the next row.
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close(); // Close cursor
        }

        cursor.close();

        return listOfComplaints;
    }

    public static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) { // Creates the DB
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, username TEXT, email TEXT, phone_number TEXT, problem TEXT)");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            DB_VERSION = newVersion;

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}