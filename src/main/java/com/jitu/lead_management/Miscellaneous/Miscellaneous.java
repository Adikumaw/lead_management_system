package com.jitu.lead_management.Miscellaneous;

import java.util.regex.Pattern;

public class Miscellaneous {
    public static boolean isValidEmail(String email) {
        // Regular expression to match any character that is not a digit
        Pattern pattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");

        return pattern.matcher(email).find();
    }

    public static boolean isStrongPassword(String password) {
        int minLength = 8; // Minimum length requirement

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isWhitespace(c)) { // Exclude whitespace characters
                hasSpecialChar = true;
            }
        }

        return password.length() >= minLength &&
                hasUpperCase &&
                hasLowerCase &&
                hasDigit &&
                hasSpecialChar;
    }
}
