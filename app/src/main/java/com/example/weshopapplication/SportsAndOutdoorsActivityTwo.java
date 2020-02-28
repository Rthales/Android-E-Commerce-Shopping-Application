package com.example.weshopapplication;

import android.os.Bundle;
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

public class SportsAndOutdoorsActivityTwo extends AppCompatActivity {
    private int current_product_id = 1;
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


    }
}
