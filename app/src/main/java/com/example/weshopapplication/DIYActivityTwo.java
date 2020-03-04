package com.example.weshopapplication;

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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

// Author of Application: Sabin Constantin Lungu
// Purpose of Application / Class: Contains the Java code for the DIY activity 2.
// Date of Last Modification : 04/03/2020
// Any Errors? None

public class DIYActivityTwo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int current_product_id = 1; // The current product id to add to basket (will be incremented)
    private TextView diyThirdProductTxt; // The first product text

    private ImageView diyThirdProductImg; // Image of the first DIY product
    private TextView diyThirdProductCost;

    private TextView diyThirdProductColourLbl;
    private Spinner diyThirdProductColourMenu;

    private TextView diyThirdProductSizeLbl;
    private Spinner diyThirdProductSizeMenu;

    private TextView diyThirdProductQuantityLbl;
    private Spinner diyThirdProductQuantityMenu;
    private Button diyThirdProductToAddToBasketBtn;

    private TextView diyFourthProductTxt;
    private ImageView diyFourthProductImg;

    private TextView diyFourthProductCost;

    private TextView diyFourthProductColourLbl;
    private Spinner diyFourthProductColourMenu;

    private TextView diyFourthProductSizeLbl;
    private Spinner diyFourthProductSizeMenu;

    private TextView diyFourthProductQuantityLbl;
    private Spinner diyFourthProductQuantityMenu;

    private Button diyFourthProductAddToBasketBtn;

    private double[] diyThirdProductCosts = new double[]{0.00, 50.00, 100.00, 200.00, 400.00, 800.00}; // A double array of product costs for the first DIY product
    private double[] diySecondProductCosts = new double[]{0.00, 15.00, 30.00, 45.00, 60.00, 75.00};

    private boolean coloursAdded = false;
    private boolean sizesAdded = false;
    private boolean quantitiesAdded = false;

    // Adapters for the objects to add to the list
    private CustomArrayAdapter quantitiesAdapter;
    private SizeArrayAdapter sizeArrayAdapter;
    private ColourArrayAdapter coloursAdapter;

    private ArrayList<TechActivity.Colours> diyListOfColoursOne = null; // An array list of colours
    private ArrayList<Size> diyListOfSizesOne = null;
    private ArrayList<TechActivity.Quantities> diyListOfQuantitiesOne = null; // An Array list of quantities for the first diy product

    // Creates the array lists for the second DIY product.
    private ArrayList<TechActivity.Colours> diyListOfColoursTwo = null;
    private ArrayList<Size> diyListOfSizesTwo = null;
    private ArrayList<TechActivity.Quantities> diyListOfQuantitiesTwo = null;

    private ImageView cartIcon;
    private HashMap<Integer, Products> listOfProductsToAddToBasket = new HashMap<Integer, Products>(); // Creates a new hash map of products with an associated ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_i_y_two);

        this.diyThirdProductTxt = findViewById(R.id.diyThirdProductTxt);
        this.diyThirdProductImg = findViewById(R.id.diyThirdProductImg);

        this.diyThirdProductCost = findViewById(R.id.diyThirdProductCostTxt);
        this.diyThirdProductColourLbl = findViewById(R.id.diyThirdColourLbl);
        this.diyThirdProductColourMenu = findViewById(R.id.diyThirdColourMenu);

        this.diyThirdProductSizeLbl = findViewById(R.id.diyThirdSizeLbl);
        this.diyThirdProductSizeMenu = findViewById(R.id.diyThirdSizeMenu);

        this.diyThirdProductQuantityLbl = findViewById(R.id.diyThirdQuantityLbl);
        this.diyThirdProductQuantityMenu = findViewById(R.id.diyThirdQuantityMenu);
        this.diyThirdProductToAddToBasketBtn = findViewById(R.id.diyThirdAddToBasketBtn);

        this.diyFourthProductTxt = findViewById(R.id.diyFourthProductTxt);
        this.diyFourthProductImg = findViewById(R.id.diyFourthProductImg);

        this.diyFourthProductCost = findViewById(R.id.diyFourthProductCostTxt);
        this.diyFourthProductColourLbl = findViewById(R.id.diyFourthProductColourLbl);

        this.diyFourthProductColourMenu = findViewById(R.id.diyFourthColourMenu);
        this.diyFourthProductQuantityMenu = findViewById(R.id.diyFourthProductQuantityMenu);

        this.diyFourthProductSizeLbl = findViewById(R.id.fourthProductSizeLbl);
        this.diyFourthProductSizeMenu = findViewById(R.id.diyFourthProductSizeMenu);

        this.diyFourthProductAddToBasketBtn = findViewById(R.id.diyFourthProductAddToBasketBtn);

        this.diyListOfColoursOne = new ArrayList<>();
        this.diyListOfColoursTwo = new ArrayList<>();

        this.diyListOfSizesOne = new ArrayList<>();
        this.diyListOfSizesTwo = new ArrayList<>();

        this.diyListOfQuantitiesOne = new ArrayList<>();
        this.diyListOfQuantitiesTwo = new ArrayList<>();

        addToDIYColourListThree();
        addToDIYSizesListThree();

        addToDIYQuantitiesListThree();
        addToDIYQuantitiesListFour();

        // Set-up adapters for the firsts Array List

        this.coloursAdapter = new ColourArrayAdapter(DIYActivityTwo.this, diyListOfColoursOne);
        coloursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        diyThirdProductColourMenu.setAdapter(coloursAdapter);
        diyThirdProductColourMenu.setOnItemSelectedListener(DIYActivityTwo.this);

        this.quantitiesAdapter = new CustomArrayAdapter(DIYActivityTwo.this, diyListOfQuantitiesOne); // Creates the quantities adapter for the first list of quantities.
        quantitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        diyThirdProductQuantityMenu.setAdapter(quantitiesAdapter);
        diyThirdProductQuantityMenu.setOnItemSelectedListener(DIYActivityTwo.this);

        this.sizeArrayAdapter = new SizeArrayAdapter(DIYActivityTwo.this, diyListOfSizesOne);
        sizeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        diyThirdProductSizeMenu.setAdapter(sizeArrayAdapter);
        diyThirdProductSizeMenu.setOnItemSelectedListener(DIYActivityTwo.this);

        // Set-up adapters for the second ArrayList

        this.coloursAdapter = new ColourArrayAdapter(DIYActivityTwo.this, diyListOfColoursTwo);
        coloursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        diyFourthProductColourMenu.setAdapter(coloursAdapter);
        diyFourthProductColourMenu.setOnItemSelectedListener(this);

        this.quantitiesAdapter = new CustomArrayAdapter(DIYActivityTwo.this, diyListOfQuantitiesTwo);
        quantitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        diyFourthProductQuantityMenu.setAdapter(quantitiesAdapter);
        diyFourthProductQuantityMenu.setOnItemSelectedListener(this);

        this.sizeArrayAdapter = new SizeArrayAdapter(DIYActivityTwo.this, diyListOfSizesTwo);
        sizeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        diyFourthProductSizeMenu.setAdapter(sizeArrayAdapter);
        diyFourthProductSizeMenu.setOnItemSelectedListener(this);

        this.diyThirdProductToAddToBasketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View thirdBtn) {
                if (diyThirdProductToAddToBasketBtn.getId() == R.id.diyThirdAddToBasketBtn) {

                    if (diyThirdProductColourMenu.getSelectedItemPosition() == 0 || diyThirdProductSizeMenu.getSelectedItemPosition() == 0 || diyThirdProductQuantityMenu.getSelectedItemPosition() == 0) {
                        AlertDialog.Builder error = new AlertDialog.Builder(DIYActivityTwo.this)
                                .setTitle(R.string.error)
                                .setMessage(R.string.errorMsg)
                                .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                    }
                                });

                        error.show();
                        error.setCancelable(true);
                    } else {
                        diyAddToBasketThree();
                    }
                }
            }
        });

        this.diyFourthProductAddToBasketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View fourthBtn) {
                if (diyFourthProductAddToBasketBtn.getId() == R.id.diyFourthProductAddToBasketBtn) {

                    if (diyFourthProductColourMenu.getSelectedItemPosition() == 0 || diyFourthProductSizeMenu.getSelectedItemPosition() == 0 || diyFourthProductQuantityMenu.getSelectedItemPosition() == 0) {
                        AlertDialog.Builder error = new AlertDialog.Builder(DIYActivityTwo.this)
                                .setTitle(R.string.error)
                                .setMessage(R.string.errorMsg)
                                .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                    }
                                });

                        error.show();
                        error.setCancelable(true);
                    } else {
                        diyAddToBasketFour();
                    }
                }
            }
        });

    }

    private boolean addToDIYColourListThree() {
        Context context = getApplicationContext();
        String[] diyColoursResources = new String[]{context.getString(R.string.colourPrompt), context.getString(R.string.gallantGray), context.getString(R.string.darkBlack), context.getString(R.string.strawberryRed), context.getString(R.string.gardenGreen)};

        TechActivity.Colours[] colours = new TechActivity.Colours[]{new TechActivity.Colours(0, diyColoursResources[0]), new TechActivity.Colours(1, diyColoursResources[1]), new TechActivity.Colours(2, diyColoursResources[2]), new TechActivity.Colours(3, diyColoursResources[3]),
                new TechActivity.Colours(4, diyColoursResources[4])};

        for (TechActivity.Colours theColours : colours) {
            diyListOfColoursOne.add(theColours);
            diyListOfColoursTwo.add(theColours);
            coloursAdded = true;
        }

        return true;
    }

    private boolean addToDIYSizesListThree() {
        Context context = getApplicationContext();
        String[] diySizesResources = new String[]{context.getString(R.string.sizePrompt), context.getString(R.string.smallSize), context.getString(R.string.mediumSize), context.getString(R.string.largeSize), context.getString(R.string.extraLargeSize)};

        Size[] sizes = new Size[]{new Size(0, diySizesResources[0]), new Size(1, diySizesResources[1]), new Size(2, diySizesResources[2]), new Size(3, diySizesResources[3]), new Size(4, diySizesResources[4])};

        for (Size theSizes : sizes) {
            diyListOfSizesOne.add(theSizes);
            diyListOfSizesTwo.add(theSizes);

            sizesAdded = true;
        }

        return true;
    }

    private boolean addToDIYQuantitiesListThree() {

        Context context = getApplicationContext();
        String[] quantityResources = new String[]{context.getString(R.string.quantitiesPrompt), context.getString(R.string.zero), context.getString(R.string.one), context.getString(R.string.two), context.getString(R.string.three), context.getString(R.string.four), context.getString(R.string.five)};

        TechActivity.Quantities[] quantities = new TechActivity.Quantities[]{new TechActivity.Quantities(quantityResources[0]), new TechActivity.Quantities(quantityResources[1]), new TechActivity.Quantities(quantityResources[2]), new TechActivity.Quantities(quantityResources[3]), new TechActivity.Quantities(quantityResources[4]),
                new TechActivity.Quantities(quantityResources[5])};

        for (TechActivity.Quantities theQuantities : quantities) {
            diyListOfQuantitiesOne.add(theQuantities);

            sizesAdded = true;
        }


        return true;
    }

    private boolean addToDIYQuantitiesListFour() {
        Context context = getApplicationContext();
        String[] quantityResources = new String[]{context.getString(R.string.quantitiesPrompt), context.getString(R.string.zero), context.getString(R.string.one), context.getString(R.string.two), context.getString(R.string.three), context.getString(R.string.four), context.getString(R.string.five)};

        TechActivity.Quantities[] quantities = new TechActivity.Quantities[]{new TechActivity.Quantities(quantityResources[0]), new TechActivity.Quantities(quantityResources[1]), new TechActivity.Quantities(quantityResources[2]), new TechActivity.Quantities(quantityResources[3]), new TechActivity.Quantities(quantityResources[4]),
                new TechActivity.Quantities(quantityResources[5])};

        for (TechActivity.Quantities theQuantities : quantities) {
            diyListOfQuantitiesTwo.add(theQuantities);

            sizesAdded = true;
        }


        return true;
    }

    private boolean diyAddToBasketThree() {
        return true;
    }

    private boolean diyAddToBasketFour() {
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

                Intent basketIntent = new Intent(DIYActivityTwo.this, BasketActivity.class); // Create a basket intent
                basketIntent.putExtra("map", listOfProductsToAddToBasket); // Transit over the hash map data to the basket
                startActivity(basketIntent); // Start the intent
            }
        });

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) { // Routine that determines which item has been selected.

        try {
            switch (item.getItemId()) {

                case R.id.mainActivity:
                    Intent homePage = new Intent(DIYActivityTwo.this, MainActivity.class);
                    startActivity(homePage);
                    break;

                case R.id.sportsAndOutdoorsCategory:
                    Intent sportsCategory = new Intent(DIYActivityTwo.this, SportsAndOutdoorsActivity.class);
                    startActivity(sportsCategory);

                    break;

                case R.id.techCategory:
                    Intent techActivity = new Intent(DIYActivityTwo.this, TechActivity.class);
                    startActivity(techActivity);
                    break;

                case R.id.clothingCategory:
                    Intent clothingActivity = new Intent(DIYActivityTwo.this, ClothingCategory.class);
                    startActivity(clothingActivity);
                    break;

                case R.id.diyCategory:
                    Intent diyActivity = new Intent(DIYActivityTwo.this, DIYActivity.class);
                    startActivity(diyActivity);
                    break;

                default:
                    super.onOptionsItemSelected(item);

            }

        } catch (ActivityNotFoundException exc) {
            Log.d(String.valueOf(R.string.error), exc.toString());
        }

        return true;
    }
}
