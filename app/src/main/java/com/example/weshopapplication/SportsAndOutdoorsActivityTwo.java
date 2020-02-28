package com.example.weshopapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

// Author of Application & Class: Sabin Constantin Lungu
// Purpose of Class: Stores the code needed to implement the Sports and Outdoors Activity 2.
// Date of Last Modification: 13/02/2020
// Any Errors? Currently None

public class SportsAndOutdoorsActivityTwo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int current_product_id = 1;
    private ImageView cartIcon;
    private TextView sportsOutdoorsTxtTwo;
    private ImageView thirdSportsOutdoorsImg;

    private TextView thirdSportsOutdoorsCostLbl;
    private TextView thirdSportsOutdoorsColoursLbl;
    private Spinner thirdSportsOutdoorsColoursMenu;

    private TextView thirdSportsOutdoorsSizeLbl;
    private Spinner thirdSportsOutdoorsSizeMenu;
    private TextView thirdSportsOutdoorsQuantityLbl;

    private Spinner thirdSportsOutdoorsQuantityMenu;
    private Button thirdSportsOutdoorsAddToBasketBtn;

    private TextView fourthSportsOutdoorsTxt;
    private ImageView fourthSportsOutdoorsImg;

    private TextView fourthSportsOutdoorsColourLbl;
    private Spinner fourthSportsOutdoorsColourMenu;

    private TextView fourthSportsOutdoorsSizeLbl;
    private Spinner fourthSportsOutdoorsSizeMenu;

    private TextView fourthSportsOutdoorsQuantityLbl;

    private Spinner fourthSportsOutdoorsQuantityMenu;
    private Button fourthAddToBasketBtn;

    private CustomArrayAdapter quantitiesAdapter;
    private SizesAdapter sizesAdapter;
    private ColourArrayAdapter coloursAdapter;

    private double[] thirdProductCosts = {0.00, 60.00, 120.00, 240.00, 480.00, 1020.00}; // The costs for the third product
    private double[] fourthProductCosts = {0.00, 100.00, 200.00, 400.00, 800.00, 1600.00}; // Costs for the fourth product

    private boolean coloursAdded; // Determines if the colours have been added to the array list successfully.
    private boolean quantitiesAdded;

    private boolean sizesAdded;
    private boolean addedToBasket;

    private ArrayList<TechActivity.Colours> listOfColoursOne; // An Array list of colours
    private ArrayList<TechActivity.Size> listOfSizesOne;
    private ArrayList<TechActivity.Quantities> listOfQuantitiesOne;

    private ArrayList<TechActivity.Colours> listOfColoursTwo;
    private ArrayList<TechActivity.Size> listOfSizesTwo;
    private ArrayList<TechActivity.Quantities> listOfQuantitiesTwo;

    private HashMap<Integer, Products> listOfProductsToAddToBasket; // A HashMap to store the products when adding to the basket


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_and_outdoors_two);

        // Initialise components
        this.sportsOutdoorsTxtTwo = findViewById(R.id.sportsOutdoorsTxtTwo);
        this.thirdSportsOutdoorsImg = findViewById(R.id.thirdSportsOutdoorsImg);
        this.thirdSportsOutdoorsCostLbl = findViewById(R.id.thirdSportsOutdoorsCostLbl);

        this.cartIcon = findViewById(R.id.cart_icon);

        this.thirdSportsOutdoorsColoursLbl = findViewById(R.id.thirdSportsOutdoorsColourLbl);
        this.thirdSportsOutdoorsColoursMenu = findViewById(R.id.thirdSportsOutdoorsColourMenu);

        this.thirdSportsOutdoorsSizeLbl = findViewById(R.id.thirdSportsOutdoorsSizeLbl);
        this.thirdSportsOutdoorsSizeMenu = findViewById(R.id.thirdSportsOutdoorsSizeMenu);

        this.thirdSportsOutdoorsQuantityLbl = findViewById(R.id.thirdQuantityLbl);
        this.thirdSportsOutdoorsQuantityMenu = findViewById(R.id.thirdSportsOutdoorsQuantityMenu);
        this.thirdSportsOutdoorsAddToBasketBtn = findViewById(R.id.thirdSportsOutdoorsAddToBasketBtn);

        this.fourthSportsOutdoorsTxt = findViewById(R.id.fourthSportsOutdoorsTxt);
        this.fourthSportsOutdoorsImg = findViewById(R.id.fourthSportsOutdoorsImg);

        this.fourthSportsOutdoorsColourLbl = findViewById(R.id.fourthSportsOutdoorsColourLbl);
        this.fourthSportsOutdoorsColourMenu = findViewById(R.id.fourthSportsOutdoorsColourMenu);

        this.fourthSportsOutdoorsSizeLbl = findViewById(R.id.fourthProductSizeLbl);
        this.fourthSportsOutdoorsSizeMenu = findViewById(R.id.fourthSportsOutdoorsSizeMenu);

        this.fourthSportsOutdoorsQuantityLbl = findViewById(R.id.fourthSportsOutdoorsQuantityLbl);
        this.fourthSportsOutdoorsQuantityMenu = findViewById(R.id.fourthSportsOutdoorsQuantityMenu);
        this.fourthAddToBasketBtn = findViewById(R.id.fourthAddToBasketButton);

        this.listOfColoursOne = new ArrayList<>();
        this.listOfColoursTwo = new ArrayList<>();

        this.listOfSizesOne = new ArrayList<>();
        this.listOfSizesTwo = new ArrayList<>();

        this.listOfQuantitiesOne = new ArrayList<>();
        this.listOfQuantitiesTwo = new ArrayList<>();

        addToColoursListOne();
        addToColoursListTwo();

        addToSizesListOne();
        addToSizesListTwo();

        addToQuantitiesListOne();
        addToQuantitiesListTwo();

        this.quantitiesAdapter = new CustomArrayAdapter(SportsAndOutdoorsActivityTwo.this, listOfQuantitiesOne);
        quantitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        thirdSportsOutdoorsQuantityMenu.setAdapter(quantitiesAdapter);
        thirdSportsOutdoorsQuantityMenu.setOnItemSelectedListener(this);

        this.sizesAdapter = new SizesAdapter(SportsAndOutdoorsActivityTwo.this, listOfSizesOne);
        sizesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        thirdSportsOutdoorsSizeMenu.setAdapter(sizesAdapter);
        thirdSportsOutdoorsSizeMenu.setOnItemSelectedListener(this);

        this.coloursAdapter = new ColourArrayAdapter(SportsAndOutdoorsActivityTwo.this, listOfColoursOne);


        // Initialise adapters

        this.thirdSportsOutdoorsAddToBasketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.thirdAddToBasketBtn) {

                }
            }
        });

        this.fourthAddToBasketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private boolean addToColoursListOne() { // Adds the colours for the third product to the array list
        return true;
    }

    private boolean addToColoursListTwo() { // Adds the colours for the fourth product to the array list
        return true;
    }

    private boolean addToSizesListOne() { // Adds the sizes for the third product to the array list
        return true;
    }

    private boolean addToSizesListTwo() {
        return true;
    }

    private boolean addToQuantitiesListOne() {
        return true;
    }

    private boolean addToQuantitiesListTwo() {
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { // Routine to determine which item has been selected

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
