package com.example.weshopapplication.ApplicationLayer;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weshopapplication.BusinessObjects.Months;
import com.example.weshopapplication.BusinessObjects.MonthsArrayAdapter;
import com.example.weshopapplication.BusinessObjects.Products;
import com.example.weshopapplication.BusinessObjects.SendPaymentInvoiceAPI;
import com.example.weshopapplication.BusinessObjects.Years;
import com.example.weshopapplication.BusinessObjects.YearsArrayAdapter;
import com.example.weshopapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

// Author of Application/Class: Sabin Constantin Lungu
// Purpose of Application/Class: Allows Customers to pay for the products chosen
// Date of Last Modification: 22/02/2020
// Any Bugs? None

public class PaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private RadioGroup paymentGroup;
    private RadioButton visaPayment;
    private int cardCVVLength = 3;

    private RadioButton masterCardPayment;
    private EditText emailAddressField;

    private EditText cardNumber;
    private EditText cardCVV;

    private EditText cardholdersName;
    private ImageView cartIcon;
    private Button paypalBtn;

    private TextView expiryMonthLbl;
    private Spinner monthMenu;
    private Spinner yearsMenu;

    private MonthsArrayAdapter monthsArrayAdapter;
    private YearsArrayAdapter yearsArrayAdapter;

    private ArrayList<Months> listOfMonths = null; // An array list of months to add to the spinner
    private ArrayList<Years> listOfYears = null;

    private boolean isEmpty = false;
    private boolean isValid = false;
    private boolean paymentOptionChosen = false;

    private boolean isMonthChosen;
    private boolean isYearChosen;

    private boolean exceedsLength;
    private boolean hasRegex;
    private boolean hasSpace;

    private boolean hasDigits;

    private Button confirmPaymentBtn;
    private Pattern regexPatterns = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]"); // Regex patterns
    private HashMap<Integer, Products> orderSummary = new HashMap<>(); // A Hash Map data structure that stores the order summary.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        this.paymentGroup = findViewById(R.id.paymentGroup);

        this.listOfMonths = new ArrayList<>();
        this.listOfYears = new ArrayList<>();

        this.visaPayment = findViewById(R.id.visaOption);
        this.masterCardPayment = findViewById(R.id.masterCardOption);
        this.paypalBtn = findViewById(R.id.paypalBtn);

        this.emailAddressField = findViewById(R.id.emailAddressPaymentField);

        this.cardNumber = findViewById(R.id.creditCardNumberField);
        this.cardCVV = findViewById(R.id.cardCVVField);
        this.cardholdersName = findViewById(R.id.cardNameField);

        this.monthMenu = findViewById(R.id.monthMenu);
        this.yearsMenu = findViewById(R.id.yearMenu);
        this.cartIcon = findViewById(R.id.cart_icon);

        this.expiryMonthLbl = findViewById(R.id.monthLbl);
        this.confirmPaymentBtn = findViewById(R.id.confirmPaymentBtn);

        addToMonthsList(); // Routine to add to the months list
        addToYearsList(); // Routine to add to the years array list.

        this.monthsArrayAdapter = new MonthsArrayAdapter(PaymentActivity.this, listOfMonths);
        monthsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        monthMenu.setAdapter(monthsArrayAdapter);
        monthMenu.setOnItemSelectedListener(this);

        this.yearsArrayAdapter = new YearsArrayAdapter(PaymentActivity.this, listOfYears);
        yearsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yearsMenu.setAdapter(yearsArrayAdapter);
        yearsMenu.setOnItemSelectedListener(this);

        this.paypalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent paypalGateway = new Intent(PaymentActivity.this, PaypalPaymentGateway.class);
                    startActivity(paypalGateway);

                } catch (ActivityNotFoundException exc) {
                    Log.d(String.valueOf(R.string.error), exc.toString());
                }
            }
        });

        this.confirmPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View confirmPaymentBtn) {

                if (confirmPaymentBtn.getId() == R.id.confirmPaymentBtn) {

                    if (monthMenu.getSelectedItemId() == 0 || yearsMenu.getSelectedItemId() == 0) { // If the index is 0 for the month menu or the years menu

                        AlertDialog.Builder paymentError = new AlertDialog.Builder(PaymentActivity.this)
                                .setTitle(R.string.paymentErrorTitle)
                                .setMessage(R.string.expiryDateError)
                                .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                    }
                                });

                        paymentError.show(); // Show the payment error
                        paymentError.setCancelable(true);

                        isMonthChosen = false;
                        isYearChosen = false;
                    } else {
                        isMonthChosen = true;
                        isYearChosen = true;
                    }

                    if (isMonthChosen && isYearChosen) { // If the month is chosen and the year is chosen by the customer
                        validatePaymentOptions(); // Call method to validate the payment options
                    }
                }
            }
        });
    }

    private boolean validatePaymentOptions() { // Routine to validate the payment options Radio Buttons

        if (paymentGroup.getCheckedRadioButtonId() == -1) { // If the visa payment or paypal or the mastercard payment are not checked.
            AlertDialog.Builder paymentError = new AlertDialog.Builder(PaymentActivity.this)

                    .setTitle(R.string.paymentErrorTitle)
                    .setMessage(R.string.paymentChoose)
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

            paymentOptionChosen = false;
            isValid = false;

            return true;
        } else {
            isValid = true;
            paymentOptionChosen = true;
        }

        if (isValid && paymentOptionChosen) {
            validateEmailAddress();
        }

        return true;
    }

    private boolean validateEmailAddress() {
        Context context = getApplicationContext();
        String[] emailResources = new String[]{context.getString(R.string.emailError), context.getString(R.string.flushPaymentField)};

        String emailInput = emailAddressField.getText().toString();

        if (emailInput.isEmpty()) {
            emailAddressField.setText(emailResources[1]);
            emailAddressField.setError(emailResources[0]);

            isEmpty = true;
            isValid = false;
        } else {
            isEmpty = false;
            isValid = true;
        }

        if (!isEmpty && isValid) {
            validateCardNumber();
        }

        return true;

    }

    private boolean validateCardNumber() {
        int cardLength = 20;
        String cardInput = cardNumber.getText().toString();
        Context context = getApplicationContext();

        String[] paymentErrors = new String[]{context.getString(R.string.cardEmpty)
                , context.getString(R.string.cardLength), context.getString(R.string.flushPaymentField), context.getString(R.string.cardDigitsOnly),
        };

        if (cardInput.isEmpty()) {
            cardNumber.setError(paymentErrors[0]);
            cardNumber.setText(paymentErrors[2]);

            isEmpty = true;
            isValid = false;
        } else {
            isEmpty = false;
            isValid = true;
        }

        for (int i = 0; i < cardInput.length(); i++) {

            if (!Character.isDigit(cardInput.charAt(i))) { // If there are no digits or there is no space in between the digits

                cardNumber.setText("");
                cardNumber.setError("Card Number Must Only Contain Digits");

                hasDigits = false;
                isValid = false;
                break; // Break out the loop
            }

            if (cardInput.length() > cardLength) { // If the card input length exceeds 20 digits

                cardNumber.setError("Card Number Should Not Exceed 16 Digits"); // Show the error message
                cardNumber.setText(""); // Set the field to empty

                hasDigits = true; // Has digits is true
                exceedsLength = true; // Exceeds length is true

                isValid = false;
                break;
            } else {
                hasDigits = true;
                exceedsLength = false;
                isValid = true;
            }

            if (hasDigits && isValid && !isEmpty && !exceedsLength) {
                validateCardCVV();
                break;
            }
        }

        return true;
    }


    private boolean validateCardCVV() {
        Context context = getApplicationContext();

        String[] cardCVVResources = new String[]{context.getString(R.string.cardCVVError), context.getString(R.string.flushPaymentField), context.getString(R.string.cardCVVError)};
        String cardCVVInput = cardCVV.getText().toString();

        if (cardCVVInput.isEmpty()) {
            cardCVV.setText(cardCVVResources[1]);
            cardCVV.setError(cardCVVResources[0]);

            isEmpty = true;
            isValid = false;
        }

        if (cardCVVInput.length() > cardCVVLength) {
            cardCVV.setText(cardCVVResources[1]);
            cardCVV.setError(cardCVVResources[2]);

            exceedsLength = true;
            isValid = false;
        }

        if (!isEmpty && isValid && !exceedsLength) {
            validateCardHolderName();
        }

        return true;
    }

    private boolean validateCardHolderName() {
        Context context = getApplicationContext();

        String[] cardHolderNameResources = new String[]{context.getString(R.string.cardHolderNameEmpty), context.getString(R.string.flushPaymentField), context.getString(R.string.cardHolderRegex), context.getString(R.string.cardHolderDigits)};

        String cardHolderNameInput = cardholdersName.getText().toString(); // Get the user input.

        if (cardHolderNameInput.isEmpty()) {
            cardholdersName.setText(cardHolderNameResources[1]);
            cardholdersName.setError(cardHolderNameResources[0]);

            isEmpty = true;
            isValid = false;
        }

        if (regexPatterns.matcher(cardHolderNameInput).find()) {
            cardholdersName.setText(cardHolderNameResources[1]);
            cardholdersName.setError(cardHolderNameResources[2]);

            hasRegex = true;
            isValid = false;
        } else {
            isEmpty = false;
            isValid = true;
            hasRegex = false;
        }

        if (!hasRegex && isValid && !isEmpty) { // If the field does not have special characters, has no digits and is not empty
            sendPaymentInvoice();
            sendProductInvoice();
        }

        return true;
    }

    private boolean sendPaymentInvoice() {
        String cardInputInvoice = cardNumber.getText().toString().trim();
        String cardCVVInvoice = cardCVV.getText().toString().trim();
        String cardHolderName = cardholdersName.getText().toString().trim();

        SendPaymentInvoiceAPI paymentInvoiceAPI = new SendPaymentInvoiceAPI(PaymentActivity.this, cardInputInvoice, cardCVVInvoice, cardHolderName);
        paymentInvoiceAPI.execute();

        return true;
    }

    private boolean sendProductInvoice() {

        return true;
    }

    public void checkButton(View view) { // Routine attached to the radio group to determine which radio button has been selected.
        int optionChecked = paymentGroup.getCheckedRadioButtonId();

        visaPayment = findViewById(optionChecked);
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
    public boolean onCreateOptionsMenu(Menu menu) { // Add the toolbar menu
        // Inflate the activities menu
        MenuInflater activityInflater = getMenuInflater(); // Get the activity inflater
        activityInflater.inflate(R.menu.homepagemenu, menu);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.basket_action_button, menu);

        View view = menu.findItem(R.id.cart_menu).getActionView();

        cartIcon = view.findViewById(R.id.cart_icon);

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent basketIntent = new Intent(PaymentActivity.this, BasketActivity.class); // Create a basket intent
                basketIntent.putExtra("map", orderSummary); // Transit over the hash map data to the basket
                startActivity(basketIntent); // Start the intent
            }
        });

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

    public boolean onOptionsItemSelected(MenuItem item) { // Routine that determines which menu item is chosen

        try {
            switch (item.getItemId()) {

                case R.id.sportsAndOutdoorsCategory: // If the sports and outdoors category is clicked on
                    Intent sportsActivity = new Intent(PaymentActivity.this, SportsAndOutdoorsActivity.class); // Create intent for sports activity
                    startActivity(sportsActivity);

                    return true;

                case R.id.techCategory:
                    Intent techActivity = new Intent(PaymentActivity.this, TechActivity.class);
                    startActivity(techActivity);

                    return true;

                case R.id.clothingCategory:
                    Intent clothingCategory = new Intent(PaymentActivity.this, ClothingCategory.class);
                    startActivity(clothingCategory);

                    return true;

                case R.id.diyCategory:
                    Intent diyCategory = new Intent(PaymentActivity.this, DIYActivity.class);
                    startActivity(diyCategory);

                    return true;

                default:

                    return super.onOptionsItemSelected(item); // Return the base item selected
            }
        } catch (ActivityNotFoundException act) {
            Log.d(String.valueOf(R.string.error), act.toString()); // Get the cause of the error.
        }

        return true;
    }
}