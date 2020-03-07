package com.example.weshopapplication;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Pattern;

public class RegisterValidators implements TextWatcher {

    public static final Pattern PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    private boolean isValid = false;

    protected static boolean isValidUsername(CharSequence usernameField) {

        return usernameField != null && !PATTERN.matcher(usernameField).matches() && !(usernameField.length() > 20);
    }

    protected static boolean isValidEmailAddress(CharSequence emailAddress) { // Helper method that determines if the E-mail Address input is valid or not.
        assert emailAddress != null;
        return PATTERN.matcher(emailAddress).matches() && !(emailAddress.length() > 30);
    }

    protected static boolean isValidPassword(CharSequence passwordEntryField) {
        return passwordEntryField != null && !PATTERN.matcher(passwordEntryField).matches() && Character.isUpperCase(passwordEntryField.charAt(0));
    }

    protected static boolean isValidCardNumber(CharSequence cardNumberEntryField) {
        return cardNumberEntryField != null && !(cardNumberEntryField.length() > 20);
    }

    protected static boolean isValidCardCVV(CharSequence cardCVVEntry) {
        return cardCVVEntry != null && cardCVVEntry.length() > 3 && !PATTERN.matcher(cardCVVEntry).matches();
    }

    protected static boolean isValidCardHolderName(CharSequence cardHolderNameEntry) {
        return cardHolderNameEntry != null && cardHolderNameEntry.equals("[0-9]+");
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
