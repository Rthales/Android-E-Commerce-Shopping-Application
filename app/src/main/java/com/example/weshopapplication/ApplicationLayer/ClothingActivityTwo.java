package com.example.weshopapplication.ApplicationLayer;

import android.app.ProgressDialog;
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

import com.example.weshopapplication.BusinessObjects.ColourArrayAdapter;
import com.example.weshopapplication.BusinessObjects.CustomArrayAdapter;
import com.example.weshopapplication.BusinessObjects.Products;
import com.example.weshopapplication.BusinessObjects.Size;
import com.example.weshopapplication.BusinessObjects.SizeArrayAdapter;
import com.example.weshopapplication.R;

import java.util.ArrayList;
import java.util.HashMap;

// Author of Application/Class: Sabin Constantin Lungu
// Purpose of Application / Class: To show customers the clothing category two activity.
// Date of Last Modification: 02/03/2020
// Any Errors? None

public class ClothingActivityTwo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int current_product_id = 1;
    private ImageView cartIcon;

    private TextView clothingThirdProductTxt;
    private ImageView clothingThirdProductImg;

    private TextView clothingThirdProductColourLbl;
    private TextView clothingThirdProductCostLbl;
    private Spinner clothingThirdProductColourMenu;

    private TextView clothingThirdProductSizeLbl;
    private Spinner clothingThirdProductSizeMenu;
    private TextView clothingFourthProductCostLbl;

    private TextView clothingThirdProductQuantityLbl;
    private Spinner clothingThirdProductQuantityMenu;
    private Button clothingThirdAddToBasketBtn;

    private TextView clothingFourthProductTxt;
    private ImageView clothingFourthProductImageView;

    private TextView clothingFourthProductColourLbl;
    private Spinner clothingFourthProductColourMenu;

    private TextView clothingFourthProductSizeLbl;
    private Spinner clothingFourthProductSizeMenu;

    private TextView clothingFourthProductQuantityLbl;
    private Spinner clothingFourthProductQuantityMenu;
    private Button clothingFourthProductAddToBasketBtn;

    private double[] clothingThirdProductCosts = new double[]{0.00, 30.00, 60.00, 120.00, 240.00, 480.00};
    private double[] clothingFourthProductCosts = new double[]{0.00, 40.00, 80.00, 160.00, 320.00, 640.00};

    private CustomArrayAdapter quantitiesAdapter;
    private ColourArrayAdapter coloursAdapter;
    private SizeArrayAdapter sizesAdapter;

    private ArrayList<TechActivity.Colours> listOfClothingColoursOne = null;
    private ArrayList<Size> listOfClothingSizesOne = null;
    private ArrayList<TechActivity.Quantities> listOfClothingQuantitiesOne = null;

    private ArrayList<TechActivity.Colours> listOfClothingColoursTwo = null;
    private ArrayList<Size> listOfClothingSizesTwo = null;
    private ArrayList<TechActivity.Quantities> listOfClothingQuantitiesTwo = null;

    private boolean coloursAdded = false; // Boolean variable that stores either true or false if the colours have been added to the array list
    private boolean quantitiesAdded = false;
    private boolean sizesAdded = false;

    private HashMap<Integer, Products> listOfProductsToAddToBasket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_two);

        this.clothingThirdProductTxt = findViewById(R.id.clothingThirdProductTxt); // Set up the clothing third product text view.
        this.clothingThirdProductImg = findViewById(R.id.clothingThirdProductImg); // Set-up the image component

        this.clothingThirdProductColourLbl = findViewById(R.id.clothingThirdProductColourLbl);
        this.clothingThirdProductColourMenu = findViewById(R.id.clothingThirdProductColourMenu);
        this.clothingThirdProductCostLbl = findViewById(R.id.clothingThirdProductCost);

        this.clothingThirdProductQuantityLbl = findViewById(R.id.clothingThirdProductQuantityLbl);
        this.clothingThirdProductQuantityMenu = findViewById(R.id.clothingThirdProductQuantityMenu);

        this.clothingThirdProductSizeLbl = findViewById(R.id.clothingThirdProductSizeLbl);
        this.clothingThirdProductSizeMenu = findViewById(R.id.clothingThirdProductSizeMenu);
        this.clothingThirdAddToBasketBtn = findViewById(R.id.clothingThirdProductAddToBasketBtn);

        this.clothingFourthProductTxt = findViewById(R.id.clothingFourthProductTxt);
        this.clothingFourthProductCostLbl = findViewById(R.id.clothingFourthProductCost);
        this.clothingFourthProductImageView = findViewById(R.id.clothingFourthProductImg);

        this.clothingFourthProductColourLbl = findViewById(R.id.clothingFourthProductColourLbl);
        this.clothingFourthProductColourMenu = findViewById(R.id.clothingFourthProductColourMenu);

        this.clothingFourthProductSizeLbl = findViewById(R.id.clothingFourthProductSizeLbl);
        this.clothingFourthProductSizeMenu = findViewById(R.id.clothingFourthProductSizeMenu);

        this.clothingFourthProductQuantityLbl = findViewById(R.id.clothingFourthProductQuantityLbl);
        this.clothingFourthProductQuantityMenu = findViewById(R.id.clothingFourthProductQuantityMenu);

        this.clothingFourthProductAddToBasketBtn = findViewById(R.id.clothingFourthProductAddToBasketBtn);

        this.listOfClothingColoursOne = new ArrayList<>();
        this.listOfClothingSizesOne = new ArrayList<>();
        this.listOfClothingQuantitiesOne = new ArrayList<>();

        this.listOfClothingColoursTwo = new ArrayList<>();
        this.listOfClothingSizesTwo = new ArrayList<>();
        this.listOfClothingQuantitiesTwo = new ArrayList<>();

        this.listOfProductsToAddToBasket = new HashMap<>();

        addToColoursList();
        addToSizesList();

        addToQuantitiesListOne();
        addToQuantitiesListTwo();

        // Set-up Adapters.
        this.coloursAdapter = new ColourArrayAdapter(ClothingActivityTwo.this, listOfClothingColoursOne);
        coloursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        clothingThirdProductColourMenu.setAdapter(coloursAdapter);
        clothingThirdProductColourMenu.setOnItemSelectedListener(ClothingActivityTwo.this);

        this.sizesAdapter = new SizeArrayAdapter(ClothingActivityTwo.this, listOfClothingSizesOne);
        sizesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        clothingThirdProductSizeMenu.setAdapter(sizesAdapter);
        clothingThirdProductSizeMenu.setOnItemSelectedListener(this);

        this.quantitiesAdapter = new CustomArrayAdapter(ClothingActivityTwo.this, listOfClothingQuantitiesOne);
        quantitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        clothingThirdProductQuantityMenu.setAdapter(quantitiesAdapter);
        clothingThirdProductQuantityMenu.setOnItemSelectedListener(this);

        this.coloursAdapter = new ColourArrayAdapter(ClothingActivityTwo.this, listOfClothingColoursTwo);
        coloursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        clothingFourthProductColourMenu.setAdapter(coloursAdapter);
        clothingFourthProductColourMenu.setOnItemSelectedListener(ClothingActivityTwo.this);

        this.sizesAdapter = new SizeArrayAdapter(ClothingActivityTwo.this, listOfClothingSizesTwo);
        sizesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        clothingFourthProductSizeMenu.setAdapter(sizesAdapter);
        clothingFourthProductSizeMenu.setOnItemSelectedListener(this);

        this.quantitiesAdapter = new CustomArrayAdapter(ClothingActivityTwo.this, listOfClothingQuantitiesTwo);
        quantitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        clothingFourthProductQuantityMenu.setAdapter(quantitiesAdapter);
        clothingFourthProductQuantityMenu.setOnItemSelectedListener(this);

        this.clothingThirdAddToBasketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View thirdBtn) {
                if (thirdBtn.getId() == R.id.clothingThirdProductAddToBasketBtn) {

                    if (clothingThirdProductColourMenu.getSelectedItemPosition() == 0 || clothingThirdProductSizeMenu.getSelectedItemPosition() == 0 || clothingThirdProductQuantityMenu.getSelectedItemPosition() == 0) {
                        AlertDialog.Builder error = new AlertDialog.Builder(ClothingActivityTwo.this)
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
                        clothingAddToBasketThree();
                    }
                }
            }
        });

        this.clothingFourthProductAddToBasketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View fourthBtn) {
                if (fourthBtn.getId() == R.id.clothingFourthProductAddToBasketBtn) {

                    if (clothingFourthProductColourMenu.getSelectedItemPosition() == 0 || clothingFourthProductSizeMenu.getSelectedItemPosition() == 0 || clothingFourthProductQuantityMenu.getSelectedItemPosition() == 0) {
                        AlertDialog.Builder error = new AlertDialog.Builder(ClothingActivityTwo.this)
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
                        clothingAddToBasketFour();
                    }
                }
            }
        });

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

        cartIcon.setOnClickListener(new View.OnClickListener() { // Add an action listener to the cart icon
            @Override
            public void onClick(View v) {

                Intent basketIntent = new Intent(ClothingActivityTwo.this, BasketActivity.class); // Create a basket intent
                basketIntent.putExtra("map", listOfProductsToAddToBasket); // Transit over the hash map data to the basket
                startActivity(basketIntent); // Start the intent
            }
        });

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) { // Routine that determines which item has been selected from the menu

        try {
            switch (item.getItemId()) {
                case R.id.sportsAndOutdoorsCategory:
                    Intent sportsCategory = new Intent(ClothingActivityTwo.this, SportsAndOutdoorsActivity.class);
                    startActivity(sportsCategory);

                    break;

                case R.id.techCategory:
                    Intent techActivity = new Intent(ClothingActivityTwo.this, TechActivity.class);
                    startActivity(techActivity);
                    break;

                case R.id.clothingCategory:
                    Intent clothingActivity = new Intent(ClothingActivityTwo.this, ClothingCategory.class);
                    startActivity(clothingActivity);
                    break;

                case R.id.diyCategory:
                    Intent diyActivity = new Intent(ClothingActivityTwo.this, DIYActivity.class);
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

    private boolean addToColoursList() {

        Context context = getApplicationContext();

        String[] clothingResources = new String[]{context.getString(R.string.colourPrompt), context.getString(R.string.salmonPink), context.getString(R.string.skyBlue),
                context.getString(R.string.rubyRed), context.getString(R.string.cityGray)};

        TechActivity.Colours[] colours = new TechActivity.Colours[]{new TechActivity.Colours(0, clothingResources[0]), new TechActivity.Colours(1, clothingResources[1]), new TechActivity.Colours(2, clothingResources[2]),
                new TechActivity.Colours(3, clothingResources[3]), new TechActivity.Colours(4, clothingResources[4])};

        for (TechActivity.Colours theColours : colours) {
            listOfClothingColoursOne.add(theColours);
            listOfClothingColoursTwo.add(theColours);

            coloursAdded = true;
        }

        return true;
    }

    private boolean addToSizesList() {

        boolean sizes_added = false;
        Context context = getApplicationContext();

        String[] clothingSizes = new String[]{context.getString(R.string.sizePrompt), context.getString(R.string.smallSize), context.getString(R.string.mediumSize)
                , context.getString(R.string.largeSize), context.getString(R.string.extraLargeSize)};

        Size[] sizes = new Size[]{new Size(0, clothingSizes[0]), new Size(1, clothingSizes[1]), new Size(2, clothingSizes[2]), new Size(3, clothingSizes[3]), new Size(4, clothingSizes[4])};

        for (Size theSizes : sizes) {
            listOfClothingSizesOne.add(theSizes);
            listOfClothingSizesTwo.add(theSizes);
            sizes_added = true;
        }

        return true;
    }

    private boolean addToQuantitiesListOne() {

        boolean quantitiesAdded = false;
        Context context = getApplicationContext();

        String[] quantitiesResources = new String[]{context.getString(R.string.zero), context.getString(R.string.one), context.getString(R.string.two),
                context.getString(R.string.three), context.getString(R.string.four), context.getString(R.string.five)};

        TechActivity.Quantities[] quantities = new TechActivity.Quantities[]{new TechActivity.Quantities(quantitiesResources[0]), new TechActivity.Quantities(quantitiesResources[1]), new TechActivity.Quantities(quantitiesResources[2]),
                new TechActivity.Quantities(quantitiesResources[3]), new TechActivity.Quantities(quantitiesResources[4]), new TechActivity.Quantities(quantitiesResources[5])};

        for (TechActivity.Quantities theQuantities : quantities) {
            listOfClothingQuantitiesOne.add(theQuantities); // Add the quantities to the first array list
            quantitiesAdded = true;
        }

        return true;
    }

    private boolean addToQuantitiesListTwo() {

        boolean quantitiesAdded = false;
        Context context = getApplicationContext();

        String[] quantitiesResources = new String[]{context.getString(R.string.zero), context.getString(R.string.one), context.getString(R.string.two),
                context.getString(R.string.three), context.getString(R.string.four), context.getString(R.string.five)};

        TechActivity.Quantities[] quantities = new TechActivity.Quantities[]{new TechActivity.Quantities(quantitiesResources[0]), new TechActivity.Quantities(quantitiesResources[1]), new TechActivity.Quantities(quantitiesResources[2]),
                new TechActivity.Quantities(quantitiesResources[3]), new TechActivity.Quantities(quantitiesResources[4]), new TechActivity.Quantities(quantitiesResources[5])};

        for (TechActivity.Quantities theQuantities : quantities) {
            listOfClothingQuantitiesTwo.add(theQuantities); // Add the quantities to the first array list
            quantitiesAdded = true;
        }

        return true;
    }

    private boolean clothingAddToBasketThree() {
        Context context = getApplicationContext();
        String[] temp = new String[]{context.getString(R.string.addingBasket), context.getString(R.string.wait)};

        final ProgressDialog dialog = new ProgressDialog(ClothingActivityTwo.this); // Spinning progress dialog
        dialog.setTitle(temp[0]); // Set the title of the dialog
        dialog.setMessage(temp[1]);

        dialog.setCancelable(false);

        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Sets the style of the progress bar

        new Thread(new Runnable() { // Create a new thread

            @Override
            public void run() {
                try {

                    Thread.sleep(1900); // Sleep for 1.9 seconds.
                } catch (InterruptedException exc) {
                    Log.d(String.valueOf(R.string.error), exc.toString());
                }

                dialog.dismiss();
            }
        }).start(); // Starts the thread

        dialog.show();

        // Create an instance for the first product and adds it to the hash map.
        Products clothingThirdProduct = new Products(current_product_id, clothingThirdProductTxt.getText().toString(), clothingThirdProductColourMenu.getSelectedItem().toString(), (int) clothingThirdProductQuantityMenu.getSelectedItemId(), clothingThirdProductCostLbl.getText().toString(), clothingThirdProductSizeMenu.getSelectedItem().toString());
        listOfProductsToAddToBasket.put(current_product_id, clothingThirdProduct);

        return true;
    }

    private boolean clothingAddToBasketFour() {

        Context context = getApplicationContext();
        String[] temp = new String[]{context.getString(R.string.addingBasket), context.getString(R.string.wait)};

        final ProgressDialog dialog = new ProgressDialog(ClothingActivityTwo.this); // Spinning progress dialog
        dialog.setTitle(temp[0]); // Set the title of the dialog
        dialog.setMessage(temp[1]);

        dialog.setCancelable(false);

        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Sets the style of the progress bar

        new Thread(new Runnable() { // Create a new thread

            @Override
            public void run() {
                try {

                    Thread.sleep(1900); // Sleep for 1.9 seconds.
                } catch (InterruptedException exc) {
                    Log.d(String.valueOf(R.string.error), exc.toString());
                }

                dialog.dismiss();
            }
        }).start(); // Starts the thread

        dialog.show();

        // Create an instance for the first product and adds it to the hash map.
        Products clothingThirdProduct = new Products(current_product_id++, clothingFourthProductTxt.getText().toString(), clothingFourthProductColourMenu.getSelectedItem().toString(), (int) clothingFourthProductQuantityMenu.getSelectedItemId(), clothingFourthProductCostLbl.getText().toString(), clothingThirdProductSizeMenu.getSelectedItem().toString());
        listOfProductsToAddToBasket.put(current_product_id++, clothingThirdProduct);

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        boolean valueAppended = false;

        int[] indexes = new int[]{0, 1, 2, 3, 4};

        Context context = getApplicationContext();
        String[] productResources = new String[]{context.getString(R.string.productCost)};

        if (parent.getItemAtPosition(position).equals(listOfClothingQuantitiesOne.get(indexes[0]))) {
            clothingThirdProductCostLbl.setText(null);
            clothingThirdProductCostLbl.append(productResources[0] + clothingThirdProductCosts[0]);
            valueAppended = true;

        } else if (parent.getItemAtPosition(position).equals(listOfClothingQuantitiesOne.get(indexes[1]))) {
            clothingThirdProductCostLbl.setText(null);
            clothingThirdProductCostLbl.append(productResources[0] + clothingThirdProductCosts[1]);
            valueAppended = true; // Value is appended

        } else if (parent.getItemAtPosition(position).equals(listOfClothingQuantitiesOne.get(indexes[2]))) {
            clothingThirdProductCostLbl.setText(null);
            clothingThirdProductCostLbl.append(productResources[0] + clothingThirdProductCosts[2]);
            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfClothingQuantitiesOne.get(indexes[3]))) {
            clothingThirdProductCostLbl.setText(null);
            clothingThirdProductCostLbl.append(productResources[0] + clothingThirdProductCosts[3]);
            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfClothingQuantitiesOne.get(indexes[4]))) {
            clothingThirdProductCostLbl.setText(null);
            clothingThirdProductCostLbl.append(productResources[0] + clothingThirdProductCosts[4]);
            valueAppended = true;
        }

        if (parent.getItemAtPosition(position).equals(listOfClothingQuantitiesTwo.get(indexes[0]))) {
            clothingFourthProductCostLbl.setText(null);
            clothingFourthProductCostLbl.append(productResources[0] + clothingFourthProductCosts[0]);
            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfClothingQuantitiesTwo.get(indexes[1]))) {
            clothingFourthProductCostLbl.setText(null);
            clothingFourthProductCostLbl.append(productResources[0] + clothingFourthProductCosts[1]);
            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfClothingQuantitiesTwo.get(indexes[2]))) {
            clothingFourthProductCostLbl.setText(null);
            clothingFourthProductCostLbl.append(productResources[0] + clothingFourthProductCosts[2]);
            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfClothingQuantitiesTwo.get(indexes[3]))) {
            clothingFourthProductCostLbl.setText(null);
            clothingFourthProductCostLbl.append(productResources[0] + clothingFourthProductCosts[3]);
            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfClothingQuantitiesTwo.get(indexes[4]))) {
            clothingFourthProductCostLbl.setText(null);
            clothingFourthProductCostLbl.append(productResources[0] + clothingFourthProductCosts[4]);
            valueAppended = true;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}