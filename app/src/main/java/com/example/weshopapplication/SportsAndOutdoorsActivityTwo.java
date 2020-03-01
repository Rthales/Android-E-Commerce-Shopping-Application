package com.example.weshopapplication;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
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
    private Button nextPageBtn;

    private double[] thirdProductCosts = {0.00, 60.00, 120.00, 240.00, 480.00, 1020.00}; // The costs for the third product
    private double[] fourthProductCosts = {0.00, 100.00, 200.00, 400.00, 800.00, 1600.00}; // Costs for the fourth product

    private boolean coloursAdded; // Determines if the colours have been added to the array list successfully.
    private boolean quantitiesAdded;

    private boolean sizesAdded;
    private boolean addedToBasket;

    private ArrayList<TechActivity.Colours> listOfColoursOne; // An Array list of colours
    private ArrayList<TechActivity.Quantities> listOfQuantitiesOne;
    private ArrayList<Size> listOfSizesOne;

    private ArrayList<TechActivity.Colours> listOfColoursTwo;
    private ArrayList<Size> listOfSizesTwo;
    private ArrayList<TechActivity.Quantities> listOfQuantitiesTwo;

    private CustomArrayAdapter quantitiesAdapter;
    private SizeArrayAdapter sizesAdapter;
    private ColourArrayAdapter coloursAdapter;

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

        this.fourthAddToBasketBtn = findViewById(R.id.fourthSportsOutdoorsAddToBasketBtn);

        this.listOfColoursOne = new ArrayList<>();
        this.listOfColoursTwo = new ArrayList<>();

        this.listOfSizesOne = new ArrayList<>();
        this.listOfSizesTwo = new ArrayList<>();

        this.listOfQuantitiesOne = new ArrayList<>();
        this.listOfQuantitiesTwo = new ArrayList<>();

        this.listOfProductsToAddToBasket = new HashMap<>();

        addToColoursListOne();
        addToColoursListTwo();

        addToQuantitiesList();
        addToSizesListOne();

        this.quantitiesAdapter = new CustomArrayAdapter(SportsAndOutdoorsActivityTwo.this, listOfQuantitiesOne);
        quantitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        thirdSportsOutdoorsQuantityMenu.setAdapter(quantitiesAdapter);
        thirdSportsOutdoorsQuantityMenu.setOnItemSelectedListener(this);

        this.sizesAdapter = new SizeArrayAdapter(SportsAndOutdoorsActivityTwo.this, listOfSizesOne);
        sizesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        thirdSportsOutdoorsSizeMenu.setAdapter(sizesAdapter);
        thirdSportsOutdoorsSizeMenu.setOnItemSelectedListener(this);

        this.coloursAdapter = new ColourArrayAdapter(SportsAndOutdoorsActivityTwo.this, listOfColoursOne);
        coloursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        thirdSportsOutdoorsColoursMenu.setAdapter(coloursAdapter);
        thirdSportsOutdoorsColoursMenu.setOnItemSelectedListener(this);

        this.quantitiesAdapter = new CustomArrayAdapter(SportsAndOutdoorsActivityTwo.this, listOfQuantitiesTwo);
        quantitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fourthSportsOutdoorsQuantityMenu.setAdapter(quantitiesAdapter);
        fourthSportsOutdoorsQuantityMenu.setOnItemSelectedListener(this);

        this.coloursAdapter = new ColourArrayAdapter(SportsAndOutdoorsActivityTwo.this, listOfColoursTwo);
        coloursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fourthSportsOutdoorsColourMenu.setAdapter(coloursAdapter);
        fourthSportsOutdoorsColourMenu.setOnItemSelectedListener(this);

        this.sizesAdapter = new SizeArrayAdapter(SportsAndOutdoorsActivityTwo.this, listOfSizesTwo);
        sizesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fourthSportsOutdoorsSizeMenu.setAdapter(sizesAdapter);
        fourthSportsOutdoorsSizeMenu.setOnItemSelectedListener(this);

        // Initialise adapters

        this.thirdSportsOutdoorsAddToBasketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.thirdSportsOutdoorsAddToBasketBtn) {

                    if(thirdSportsOutdoorsColoursMenu.getSelectedItemPosition() == 0 || thirdSportsOutdoorsSizeMenu.getSelectedItemPosition() == 0 || thirdSportsOutdoorsQuantityMenu.getSelectedItemPosition() == 0) {
                        AlertDialog.Builder chooseError = new AlertDialog.Builder(SportsAndOutdoorsActivityTwo.this)
                                .setTitle("Error")
                                .setMessage("You must choose an appropriate option before adding to the basket")
                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override

                                    public void onClick(DialogInterface dialog, int which) {
                                        if(dialog != null) {
                                            dialog.dismiss();
                                        }
                                    }
                                });

                        chooseError.show();
                        chooseError.setCancelable(false);
                    }

                    else {
                        addToBasketThree();
                    }
                }
            }
        });

        this.fourthAddToBasketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fourthSportsOutdoorsColourMenu.getSelectedItemPosition() == 0 || fourthSportsOutdoorsSizeMenu.getSelectedItemPosition() == 0 || fourthSportsOutdoorsQuantityMenu.getSelectedItemPosition() == 0) {
                    AlertDialog.Builder chooseError = new AlertDialog.Builder(SportsAndOutdoorsActivityTwo.this)
                            .setTitle("Error")

                            .setMessage("You must choose an appropriate option before adding to the basket")
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                @Override

                                public void onClick(DialogInterface dialog, int which) {
                                    if (dialog != null) {
                                        dialog.dismiss();
                                    }
                                }
                            });
                    chooseError.show();
                    chooseError.setCancelable(false);
                }

                else {
                    addToBasketFour();
                }
            }
        });
    }

    private boolean addToColoursListOne() { // Adds the colours for the third product to the array list
        boolean coloursAdded = false;

        TechActivity.Colours[] colours = {new TechActivity.Colours(0, "Choose colour please"),
        new TechActivity.Colours(1, "Red"), new TechActivity.Colours(2, "Sky Blue")
        , new TechActivity.Colours(3, "Dark Yellow"), new TechActivity.Colours(4, "Blood Orange")};

        for(TechActivity.Colours theColours : colours) {
            listOfColoursOne.add(theColours);
            coloursAdded = true;
        }

        return true;
    }

    private boolean addToColoursListTwo() { // Adds the colours for the fourth product to the array list
        boolean coloursAdded = false;

        TechActivity.Colours[] colours = {new TechActivity.Colours(0, "Choose colour please"),
                new TechActivity.Colours(1, "Black"), new TechActivity.Colours(2, "Red")
                , new TechActivity.Colours(3, "Orange"), new TechActivity.Colours(4, "White")};

        for(TechActivity.Colours theColours : colours) {
            listOfColoursTwo.add(theColours);
            coloursAdded = true;
        }

        return true;
    }

    private boolean addToQuantitiesList() {
        TechActivity.Quantities[] quantities = {new TechActivity.Quantities(0), new TechActivity.Quantities(1), new TechActivity.Quantities(2),
                new TechActivity.Quantities(3), new TechActivity.Quantities(4), new TechActivity.Quantities(5)};

        for (TechActivity.Quantities theQuantities : quantities) {
            listOfQuantitiesOne.add(theQuantities);
            listOfQuantitiesTwo.add(theQuantities);
        }

        return true;
    }

    private void addToBasketThree() {
        final ProgressDialog dialog = new ProgressDialog(SportsAndOutdoorsActivityTwo.this); // Spinning progress dialog
        dialog.setTitle("Adding to Basket.."); // Set the title of the dialog
        dialog.setMessage("Please Wait");

        dialog.setCancelable(false);

        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Sets the style of the progress bar

        new Thread(new Runnable() { // Create a new thread

            @Override
            public void run() {
                try {

                    Thread.sleep(1900); // Sleep for 1.9 seconds.
                } catch (InterruptedException exc) {
                    Log.d("Error : ", exc.toString());
                }

                dialog.dismiss();
            }
        }).start(); // Starts the thread

        dialog.show();

        // Create an instance for the first product and adds it to the hash map.
        Products thirdProduct = new Products(current_product_id, sportsOutdoorsTxtTwo.getText().toString(), thirdSportsOutdoorsColoursMenu.getSelectedItem().toString(), (int) thirdSportsOutdoorsQuantityMenu.getSelectedItemId(), thirdSportsOutdoorsCostLbl.getText().toString(), thirdSportsOutdoorsSizeMenu.getSelectedItem().toString());
        listOfProductsToAddToBasket.put(current_product_id, thirdProduct);
    }

    private void addToBasketFour() {

    }

    private boolean addToSizesListOne() { // Adds the sizes for the third product to the array list
        boolean sizesAdded = false;
        Size[] sizes = {new Size(0, "Choose a Size Please"), new Size(1, "S"), new Size(2, "M"),
        new Size(3, "L"), new Size(4, "XL")};

        for(Size theSize : sizes) {

            listOfSizesOne.add(theSize);
            listOfSizesTwo.add(theSize);
            sizesAdded = true;
        }

        return true;
    }

    private boolean addToQuantitiesListOne() {
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

                Intent basketIntent = new Intent(SportsAndOutdoorsActivityTwo.this, BasketActivity.class); // Create a basket intent
                basketIntent.putExtra("map", listOfProductsToAddToBasket); // Transit over the hash map data to the basket
                startActivity(basketIntent); // Start the intent
            }
        });

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { // Routine to determine which item has been selected

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        try {

            switch (item.getItemId()) {
                case R.id.sportsAndOutdoorsCategory:
                    Intent sportsCategory = new Intent(SportsAndOutdoorsActivityTwo.this, SportsAndOutdoorsActivity.class);
                    startActivity(sportsCategory);

                    break;

                case R.id.techCategory:
                    Intent techActivity = new Intent(SportsAndOutdoorsActivityTwo.this, TechActivity.class);
                    startActivity(techActivity);
                    break;

                case R.id.clothingCategory:
                    Intent clothingActivity = new Intent(SportsAndOutdoorsActivityTwo.this, ClothingCategory.class);
                    startActivity(clothingActivity);
                    break;

                case R.id.diyCategory:
                    Intent diyActivity = new Intent(SportsAndOutdoorsActivityTwo.this, DIYActivity.class);
                    startActivity(diyActivity);
                    break;

                default:
                    super.onOptionsItemSelected(item);

            }

        } catch (ActivityNotFoundException exc) {
            Log.d("Error ", exc.toString());
        }

        return true;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
