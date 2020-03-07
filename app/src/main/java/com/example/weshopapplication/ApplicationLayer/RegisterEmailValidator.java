package com.example.weshopapplication.ApplicationLayer;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Pattern;

public class RegisterEmailValidator implements TextWatcher {
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );


    private boolean isValid = false;

    public static boolean isValidEmailAddress(CharSequence emailAddress) {
        return emailAddress != null && !EMAIL_PATTERN.matcher(emailAddress).matches();
    }

    public static boolean isValidPassword(CharSequence pass) {
        return isValidPassword(pass);
    }

    private boolean isValidPassword(String pass) {
        return pass != null && pass.length() > 20;
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
    public void afterTextChanged(Editable s) {
        isValid = isValidEmailAddress(s);
        isValid = isValidPassword(s);
    }
}
