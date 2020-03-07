package com.example.weshopapplication.ApplicationLayer;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weshopapplication.BusinessObjects.Products;
import com.example.weshopapplication.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

// Author of Application: Sabin Constantin Lungu
// Purpose of Application & Class: To store the products added to the basket in a List View.
// Date of Last Modification: 13/02/2020.
// Any Errors: N/A

public class BasketActivity extends AppCompatActivity implements View.OnClickListener {
    private Button placeOrderBtn;
    private HashMap<Integer, Products> listOfProductsToAddToBasket = new HashMap<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basket);

        this.placeOrderBtn = findViewById(R.id.placeOrderBtn);
        this.placeOrderBtn.setOnClickListener(this);

        Intent intent = getIntent();
        HashMap<Integer, Products> hashMap = (HashMap<Integer, Products>) intent.getSerializableExtra("map"); // Get the hash map from the tech activity
        ArrayList<String> products = new ArrayList<>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BasketActivity.this, android.R.layout.simple_list_item_1, products) {

            public View getView(int position, View convertView, @NotNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView tv = view.findViewById(android.R.id.text1);

                tv.setTextColor(Color.WHITE); // Change the colour of the text

                return view; // Return the view
            }
        };

        ListView view = findViewById(R.id.listViewBasket); // Find the list view component
        view.setAdapter(arrayAdapter); // Set its adapter

//        for (Map.Entry<Integer, Products> entry : hashMap.entrySet()) { // Loop over the hash map of products
        // arrayAdapter.add(entry.toString()); // Add the entries to the adapter list
        //}
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onStop() {
        super.onStop();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        Context context = getApplicationContext();
        String[] temp = new String[]{context.getString(R.string.finish)};

        try {
            if (v.getId() == R.id.placeOrderBtn) {

                AlertDialog.Builder builder = new AlertDialog.Builder(BasketActivity.this)
                        .setTitle(R.string.checkout)
                        .setMessage(temp[0]) // Set the message to the first index in the array

                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override

                            public void onClick(DialogInterface dialog, int which) {
                                if (dialog != null) {

                                    dialog.dismiss();
                                    finish();
                                }
                            }
                        }).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent checkOutActivity = new Intent(BasketActivity.this, PaymentActivity.class);
                                checkOutActivity.putExtra("map", listOfProductsToAddToBasket);
                                startActivity(checkOutActivity);
                            }
                        });

                builder.show();
                builder.setCancelable(true);
            }
        } catch (ActivityNotFoundException exc) {
            Log.d(String.valueOf(R.string.error), exc.toString());
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}