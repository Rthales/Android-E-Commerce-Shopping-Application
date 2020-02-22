package com.example.weshopapplication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Vector;

// Author of Application/Class: Sabin Constantin Lungu
// Purpose of Application/Class: Allows Customers to pay for the products chosen
// Date of Last Modification: 22/02/2020
// Any Bugs? None

public class PaymentActivity extends AppCompatActivity {
    private RadioButton visaPayment;
    private RadioButton paypalPayment;
    private RadioButton masterCardPayment;

    private EditText cardNumber;
    private EditText cardCVV;
    private EditText cardholdersName;
    private TextView expiryMonthLbl;

    private Vector<String> months;
    private Vector<String> years; // The years in a txt file will get stored in a vector

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

    }

}
