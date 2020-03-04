package com.example.weshopapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

// Author of Application/Class: Sabin Constantin Lungu
// Purpose of Application/Class: Allows Customers to pay for the products chosen
// Date of Last Modification: 22/02/2020
// Any Bugs? None

public class PaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private RadioButton visaPayment;
    private RadioButton paypalPayment;
    private RadioButton masterCardPayment;

    private EditText cardNumber;
    private EditText cardCVV;
    private EditText cardholdersName;

    private TextView expiryMonthLbl;
    private Spinner monthMenu;
    private Spinner yearsMenu;

    private MonthsArrayAdapter monthsArrayAdapter;

    private ArrayList<Months> months = null;
    private ArrayList<String> years = null;

    private Button confirmPaymentBtn;
    private HashMap<Integer, Products> orderSummary = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        this.months = new ArrayList<>();
        this.years = new ArrayList<>();

        this.visaPayment = findViewById(R.id.visaOption);
        this.paypalPayment = findViewById(R.id.paypalOption);
        this.masterCardPayment = findViewById(R.id.masterCardOption);

        this.cardNumber = findViewById(R.id.creditCardNumberField);
        this.cardCVV = findViewById(R.id.cardCVVField);
        this.cardholdersName = findViewById(R.id.cardNameField);

        this.monthMenu = findViewById(R.id.monthMenu);
        this.yearsMenu = findViewById(R.id.yearMenu);

        this.expiryMonthLbl = findViewById(R.id.monthLbl);
        this.confirmPaymentBtn = findViewById(R.id.confirmPaymentBtn);

        addToMonthsList();
        addToYearsList();

        this.monthsArrayAdapter = new MonthsArrayAdapter(PaymentActivity.this, months);
        monthsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        monthMenu.setAdapter(monthsArrayAdapter);
        monthMenu.setOnItemSelectedListener(this);


        this.confirmPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private boolean addToMonthsList() {
        return true;
    }

    private boolean addToYearsList() {
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
