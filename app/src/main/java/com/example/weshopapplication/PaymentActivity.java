package com.example.weshopapplication;

import android.content.Context;
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
        boolean month_added = false;
        Context context = getApplicationContext();

        String[] monthsResources = new String[]{context.getString(R.string.monthPrompt), context.getString(R.string.januaryMonth),
                context.getString(R.string.februaryMonth),
                context.getString(R.string.marchMonth),
                context.getString(R.string.aprilMonth), context.getString(R.string.mayMonth), context.getString(R.string.juneMonth), context.getString(R.string.julyMonth),
                context.getString(R.string.augustMonth), context.getString(R.string.septemberMonth), context.getString(R.string.octoberMonth), context.getString(R.string.novemberMonth), context.getString(R.string.decemberMonth)};


        Months[] theMonths = new Months[]{new Months(monthsResources[0]), new Months(monthsResources[1]), new Months(monthsResources[2]), new Months(monthsResources[3]), new Months(monthsResources[4]),
                new Months(monthsResources[5]), new Months(monthsResources[6]), new Months(monthsResources[7]), new Months(monthsResources[8]), new Months(monthsResources[9]), new Months(monthsResources[10]), new Months(monthsResources[11]),
                new Months(monthsResources[12])};

        for (Months month : theMonths) {
            months.add(month);
            month_added = true;
        }

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
