package com.example.weshopapplication.ApplicationLayer;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weshopapplication.R;

public class PaypalPaymentGateway extends AppCompatActivity {
    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal_payment_gateway);
    }
}
