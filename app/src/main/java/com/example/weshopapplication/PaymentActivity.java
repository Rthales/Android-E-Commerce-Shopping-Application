package com.example.weshopapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
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
    private YearsArrayAdapter yearsArrayAdapter;

    private ArrayList<Months> listOfMonths = null;
    private ArrayList<Years> listOfYears = null;

    private Button confirmPaymentBtn;
    private HashMap<Integer, Products> orderSummary = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        this.listOfMonths = new ArrayList<>();
        this.listOfYears = new ArrayList<>();

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

        addToMonthsList(); // Routine to add to the months list
        addToYearsList();

        this.monthsArrayAdapter = new MonthsArrayAdapter(PaymentActivity.this, listOfMonths);
        monthsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        monthMenu.setAdapter(monthsArrayAdapter);
        monthMenu.setOnItemSelectedListener(this);

        this.yearsArrayAdapter = new YearsArrayAdapter(PaymentActivity.this, listOfYears);
        yearsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yearsMenu.setAdapter(yearsArrayAdapter);
        yearsMenu.setOnItemSelectedListener(this);


        this.confirmPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View confirmPaymentBtn) {

                if (confirmPaymentBtn.getId() == R.id.confirmPaymentBtn) {

                    if (monthMenu.getSelectedItemId() == 0 || yearsMenu.getSelectedItemId() == 0) {

                        AlertDialog.Builder paymentError = new AlertDialog.Builder(PaymentActivity.this)
                                .setTitle(R.string.paymentErrorTitle)

                                .setMessage(R.string.paymentErrorMsg)
                                .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                    }
                                });

                        paymentError.show();
                        paymentError.setCancelable(true);
                    }
                }
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
            listOfMonths.add(month);
            month_added = true;
        }

        return true;
    }

    private boolean addToYearsList() {
        boolean years_added = false;
        Context context = getApplicationContext();

        String[] yearsResources = new String[]{context.getString(R.string.yearsPrompt), context.getString(R.string.firstYear), context.getString(R.string.secondYear),
                context.getString(R.string.thirdYear), context.getString(R.string.fourthYear), context.getString(R.string.fifthYear),
                context.getString(R.string.sixthYear), context.getString(R.string.seventhYear), context.getString(R.string.eighthYear)};

        Years[] years = new Years[]{new Years(yearsResources[0]), new Years(yearsResources[1]), new Years(yearsResources[2]), new Years(yearsResources[3]),
                new Years(yearsResources[4]), new Years(yearsResources[5]), new Years(yearsResources[6]), new Years(yearsResources[7]), new Years(yearsResources[8])};

        for (Years theYears : years) {
            listOfYears.add(theYears);
            years_added = true;
        }

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
