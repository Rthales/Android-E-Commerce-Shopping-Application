package com.example.weshopapplication;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Pattern;

// Author of Validators Unit Testing Class: Sabin Constantin Lungu
// Purpose of Validators Unit Testing Class: To provide helper methods that will be used in the UnitTests class to test certain aspects of the software.
// Date of Last Modification: 08/03/2020
// Any bugs? No

public class Validators implements TextWatcher { // Validator class implements the Android Text Watcher methods

    public static final Pattern PATTERN = Pattern.compile( // Regex patterns to use.
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    private boolean isValid = false; // Valid field is false by default

    protected static boolean isValidUsername(CharSequence usernameField) { // Routine to determine if the username is valid

        return usernameField != null && !PATTERN.matcher(usernameField).matches() && !(usernameField.length() > 20); // Is valid when the username field is not empty and does not have regex characters and also the length is not bigger than 20
    }

    protected static boolean isValidEmailAddress(CharSequence emailAddress) { // Helper method that determines if the E-mail Address input is valid or not.
        assert emailAddress != null;
        return PATTERN.matcher(emailAddress).matches() && !(emailAddress.length() > 30);
    }

    protected static boolean isValidPassword(CharSequence passwordEntryField) { // Determines if the password is valid.
        return passwordEntryField != null && !PATTERN.matcher(passwordEntryField).matches() && Character.isUpperCase(passwordEntryField.charAt(0));
    }

    protected static boolean isValidCardNumber(CharSequence cardNumberEntryField) { // Determines if the card number entry is valid.
        return cardNumberEntryField != null && !(cardNumberEntryField.length() > 20);
    }

    protected static boolean isValidCardCVV(CharSequence cardCVVEntry) {
        return cardCVVEntry != null && !(cardCVVEntry.length() > 3);
    }

    protected static boolean isValidCardHolderName(CharSequence cardHolderEntryField) {
        return cardHolderEntryField != null && PATTERN.matcher(cardHolderEntryField).matches();
    }

    boolean isValid() {
        return isValid;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable textEntry) {
        isValid = isValidEmailAddress(textEntry);
        isValid = isValidUsername(textEntry);
        isValid = isValidPassword(textEntry);
    }
}
