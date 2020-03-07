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

    public static boolean isValidUsername(CharSequence usernameField) {

        return usernameField != null && PATTERN.matcher(usernameField).matches() && !(usernameField.length() > 20);
    }

    public static boolean isValidEmailAddress(CharSequence emailAddress) {
        assert emailAddress != null;
        return PATTERN.matcher(emailAddress).matches() && !(emailAddress.length() > 30);
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
    }
}
