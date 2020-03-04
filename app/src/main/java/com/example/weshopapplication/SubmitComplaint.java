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

    private EditText usernameField;
    private EditText emailAddressField;
    private EditText phoneNumberField;

    private EditText problemField;
    private boolean isValidated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_complaint);

        View submitComplaintBtn = findViewById(R.id.btnSubmit);
        submitComplaintBtn.setOnClickListener(this);

        View backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(this);

        this.usernameField = findViewById(R.id.add_usernameField);
        this.emailAddressField = findViewById(R.id.add_complaintEmailField);

        this.phoneNumberField = findViewById(R.id.add_complaint_phoneNumberField);
        this.problemField = findViewById(R.id.add_complaint_fieldProblem);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnBack:
                this.finish();
                break;

            case R.id.btnSubmit:
                validateUsernameField();
                validateEmailAddressField();

                validatePhoneNumberField();
                validateProblemField();
                String username = ((EditText) findViewById(R.id.add_usernameField)).getText().toString();
                String email = ((EditText) findViewById(R.id.add_complaintEmailField)).getText().toString();

                String phone_number = ((EditText) findViewById(R.id.add_complaint_phoneNumberField)).getText().toString();
                String problem = ((EditText) findViewById(R.id.add_complaint_fieldProblem)).getText().toString();

                this.databaseManipulator = new DatabaseManipulator(this);
                this.databaseManipulator.insert(username, email, phone_number, problem);

                break;
        }
    }

    private boolean validateUsernameField() {

        if (usernameField.getText().toString().isEmpty()) {
            usernameField.setText("");
            usernameField.setError("Field Can't Be Empty");

            isValidated = true;
        }

        if (!usernameField.getText().toString().isEmpty()) {
            isValidated = false;
        }

        return true;
    }

    private boolean validateEmailAddressField() {

        if (emailAddressField.getText().toString().isEmpty()) {
            emailAddressField.setText("");
            emailAddressField.setError("Field Can't Be Empty");

            isValidated = true;
        }

        if (!emailAddressField.getText().toString().isEmpty()) {
            isValidated = false;
        } else {
            showSavedComplaintsDialog();
        }


        return true;
    }

    private boolean validatePhoneNumberField() {

        if (phoneNumberField.getText().toString().isEmpty()) {
            phoneNumberField.setText("");
            phoneNumberField.setError("Field Can't Be Empty");

            isValidated = true;
        }

        if (!phoneNumberField.getText().toString().isEmpty()) {
            isValidated = false;
        }

        return true;
    }

    private boolean validateProblemField() {

        if (problemField.getText().toString().isEmpty()) {
            problemField.setText("");
            problemField.setError("Field Can't Be Empty");

            isValidated = true;
        }

        if (!problemField.getText().toString().isEmpty()) {
            isValidated = false;
        }


        return true;
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
