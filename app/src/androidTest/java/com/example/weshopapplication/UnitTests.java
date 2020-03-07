package com.example.weshopapplication;

import android.view.View;
import android.widget.EditText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.weshopapplication.ApplicationLayer.BasketActivity;
import com.example.weshopapplication.ApplicationLayer.ClothingCategory;
import com.example.weshopapplication.ApplicationLayer.DIYActivity;
import com.example.weshopapplication.ApplicationLayer.DIYActivityTwo;
import com.example.weshopapplication.ApplicationLayer.LoginActivity;
import com.example.weshopapplication.ApplicationLayer.MainActivity;
import com.example.weshopapplication.ApplicationLayer.PaymentActivity;
import com.example.weshopapplication.ApplicationLayer.RegisterActivity;
import com.example.weshopapplication.ApplicationLayer.SportsAndOutdoorsActivity;
import com.example.weshopapplication.ApplicationLayer.TechActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

// Purpose of Test: To test if the MainActivity loads
// Author of Test: Sabin Constantin Lungu
// Date of Last Modification: 4/2/2020
// Tests Pass? : Yes

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UnitTests {

    // Rules created for each test
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<RegisterActivity> registerRule = new ActivityTestRule<>(RegisterActivity.class);

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Rule
    public ActivityTestRule<TechActivity> techActivityActivityTestRule = new ActivityTestRule<>(TechActivity.class);

    @Rule
    public ActivityTestRule<BasketActivity> basketActivityActivityTestRule = new ActivityTestRule<>(BasketActivity.class);

     @Rule
     public ActivityTestRule<ClothingCategory> clothingCategoryActivityTestRule = new ActivityTestRule<>(ClothingCategory.class);

    @Rule
    public ActivityTestRule<SportsAndOutdoorsActivity> sportsAndOutdoorsActivityActivityTestRule = new ActivityTestRule<>(SportsAndOutdoorsActivity.class);

    @Rule
    public ActivityTestRule<DIYActivity> diyActivityActivityTestRule = new ActivityTestRule<>(DIYActivity.class);

    @Rule
    public ActivityTestRule<DIYActivityTwo> diyActivityTwoActivityTestRule = new ActivityTestRule<>(DIYActivityTwo.class);

    @Rule
    public ActivityTestRule<PaymentActivity> paymentActivityActivityTestRule = new ActivityTestRule<>(PaymentActivity.class);

    // Activities to be tested
    private MainActivity mainActivity = null;
    private RegisterActivity registerActivity = null;
    private LoginActivity loginActivity = null;

    private SportsAndOutdoorsActivity sportsAndOutdoorsActivity = null;
    private TechActivity techActivity = null;

    private ClothingCategory clothingCategory = null;
    private DIYActivity diyActivity = null;

    private BasketActivity productsBasket = null;
    private PaymentActivity paymentActivity = null;

    // Retrieve the data from the fields
    private EditText usernameTest;
    private EditText emailAddressTest;
    private EditText passwordTest;

    // Test Payment Input Fields

    private EditText cardNumberFieldTest;
    private EditText cardCVVFieldTest;
    private EditText cardHolderNameTest;

    @Before
    public void setUp() { // Sets up the tests
        getActivities();
        // Get the edit text fields
        usernameTest = registerActivity.findViewById(R.id.usernameField);
        emailAddressTest = registerActivity.findViewById(R.id.emailAddressField);
        passwordTest = registerActivity.findViewById(R.id.passwordField);

        cardNumberFieldTest = paymentActivity.findViewById(R.id.creditCardNumberField);
        cardCVVFieldTest = paymentActivity.findViewById(R.id.cardCVVField);
        cardHolderNameTest = paymentActivity.findViewById(R.id.cardNameField);
    }

    public void getActivities() { // Retrieves the activities
        mainActivity = activityRule.getActivity(); // Get the activity
        registerActivity = registerRule.getActivity();
        clothingCategory = clothingCategoryActivityTestRule.getActivity(); // Get the clothing activity

        loginActivity = loginActivityRule.getActivity(); // Get the login activity
        techActivity = techActivityActivityTestRule.getActivity(); // Get the tech activity
        productsBasket = basketActivityActivityTestRule.getActivity();
        paymentActivity = paymentActivityActivityTestRule.getActivity();

        diyActivity = diyActivityActivityTestRule.getActivity();

        sportsAndOutdoorsActivity = sportsAndOutdoorsActivityActivityTestRule.getActivity(); // Get the sports and outdoors activity
    }

    @Test
    public void testMainActivityLauncher() { // Routine that tests if the main activity launches.
        View view = mainActivity.findViewById(R.id.welcomeTxt);

        assertNotNull(view);
    }

    public void testPreconditions() { // Test preconditions
        assertNotNull(usernameTest);
        assertNotNull(emailAddressTest);
        assertNotNull(passwordTest);

        assertNotNull(cardNumberFieldTest);
        assertNotNull(cardCVVFieldTest);
        assertNotNull(cardHolderNameTest);
    }

    @Test
    public void testValidUsername() {
        assertTrue(usernameTest.getText().toString(), RegisterValidators.isValidUsername("sabin2000")); // Test should pass because username is valid
    }

    @Test
    public void testInvalidUsername() {
        assertTrue(usernameTest.getText().toString(), RegisterValidators.isValidUsername("sabin@(£("));
    }

    @Test
    public void testValidEmailAddress() { // Test stub to test if the E-mail Address entered is the one to expect. Test should pass
        assertTrue(emailAddressTest.getText().toString(), RegisterValidators.isValidEmailAddress("sabinlungu293@gmail.com"));
    }

    @Test
    public void testEmailAddressLength() { // Test Method to test if the E-mail Address length is > 30. Test Should Fail as it exceeds 30 characters
        assertTrue(emailAddressTest.getText().toString(), RegisterValidators.isValidEmailAddress("bobmichaeltesemailaddressparkinsonapplescarstobuy@yahoo.com"));
    }

    @Test
    public void emailAddressTestTwo() { // Test Stub to test if the email address entered is an empty string. This test should fail as the entry field is empty
        assertTrue(emailAddressTest.getText().toString(), RegisterValidators.isValidEmailAddress(" "));
    }

    @Test
    public void testEmailAddressRegex() { // Test Routine to test if the E-mail Address contains an @ symbol. Test will fail because it doesn't.
        assertTrue(emailAddressTest.getText().toString(), RegisterValidators.isValidEmailAddress("sabinlungu293gmail.com"));
    }

    @Test
    public void testRegisterActivityLauncher() {
        View registerView = registerActivity.findViewById(R.id.registerTxt);
        assertNotNull(registerView);
    }

    @Test
    public void testSportsAndOutdoorsActivityLauncher() {
        View sportsView = sportsAndOutdoorsActivity.findViewById(R.id.firstSportsOutdoorCostLbl);
        assertNotNull(sportsView);
    }

    @Test
    public void testTechActivityLauncher() { // Tests to see if the tech activity loads correctly.
        View activityView = techActivity.findViewById(R.id.firstProductImg);
        assertNotNull(activityView);
    }

    @Test
    public void testClothingActivityLauncher() { // Tests to see if the clothing activity is loaded
        View activityView = clothingCategory.findViewById(R.id.clothingFirstProductCostLbl);
        assertNotNull(activityView);
    }

    @Test
    public void testDIYActivityLauncher() {
        View diyView = diyActivity.findViewById(R.id.diyFirstProductCostTxt);
        assertNotNull(diyView);
    }

    @Test
    public void testLoadBasketActivity() { // Tests to see if the basket loads correctly.
        View basketView = productsBasket.findViewById(R.id.placeOrderBtn);
        assertNotNull(basketView);
    }

    @Test
    public void testLoginActivityLauncher() { // Test stub that tests to see if the login activity launches
        View loginView = loginActivity.findViewById(R.id.loginBtn); // Finds the login button
        assertNotNull(loginView); // Check condition
    }

    @After
    public void tearDown() { // After testing
        // Empty activities after testing
        mainActivity = null;
        registerActivity = null;
        loginActivity = null;

        diyActivity = null;

        techActivity = null;
        productsBasket = null;
    }
}