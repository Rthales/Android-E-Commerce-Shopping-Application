package com.example.weshopapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

// Author of Application: Sabin Constantin Lungu
// Purpose of Class: Allows customers to Submit a complaint through the Contact Us Form if they are experiencing any issues with the app.
// Date of Last Modification: 22/02/2020
// Any Bugs? None.

public class SubmitComplaint extends AppCompatActivity implements View.OnClickListener {
    private DatabaseManipulator databaseManipulator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_complaint);

        View submitComplaintBtn = findViewById(R.id.btnSubmit);
        submitComplaintBtn.setOnClickListener(this);

        View backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnBack:
                this.finish();
                break;

            case R.id.btnSubmit:

                String username = ((EditText) findViewById(R.id.add_usernameField)).getText().toString();
                String email = ((EditText) findViewById(R.id.add_complaintEmailField)).getText().toString();

                String phone_number = ((EditText) findViewById(R.id.add_complaint_phoneNumberField)).getText().toString();
                String problem = ((EditText) findViewById(R.id.add_complaint_fieldProblem)).getText().toString();

                this.databaseManipulator = new DatabaseManipulator(this);
                this.databaseManipulator.insert(username, email, phone_number, problem);

                showSavedComplaintsDialog();

                break;
        }
    }

    protected void showSavedComplaintsDialog() {
        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(SubmitComplaint.this);

        builder.setMessage(R.string.add_next_dialog_message);
        builder.setCancelable(false);

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int which) {
                SubmitComplaint.this.finish();
            }
        });

        builder.setPositiveButton(R.string.add_next_dialog_confirm_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
