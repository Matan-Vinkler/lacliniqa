package org.project.lacliniqa.globals.utils;

import static org.project.lacliniqa.globals.constants.ValidatorConstants.*;

public class Validators {
    private static boolean validate(String word) {
        return (word != null) && (!word.isEmpty()) && (!word.contains(FORBIDDEN_SPACECHAR));
    }

    public static boolean validateEmail(String email) {
        if(!validate(email)) {
            return false;
        }

        return email.contains(VALID_EMAIL_SYMBOL_1) && email.contains(VALID_EMAIL_SYMBOL_2);
    }

    public static boolean validateName(String name) {
        if(!validate(name)) {
            return false;
        }

        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean validatePhone(String phone) {
        if(!validate(phone)) {
            return false;
        }

        return phone.matches(VALID_PHONE_REGEX);
    }

    public static boolean validateId(String id) {
        if(!validate(id)) {
            return false;
        }

        if(id.length() != VALID_ID_LENGTH) {
            return false;
        }

        for(int i = 0; i < id.length(); i++) {
            if(!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean validatePassword(String pass1, String pass2) {
        if(!validate(pass1)) {
            return false;
        }

        if(!pass1.equals(pass2)) {
            return false;
        }

        if(pass1.length() < VALID_PASSWORD_MIN_LEN) {
            return false;
        }

        boolean hasLetter = false, hasDigit = false, hasSpecialChar = false;

        for (int i = 0; i < pass1.length(); i++) {
            char ch = pass1.charAt(i);
            if (Character.isLetter(ch)) {
                hasLetter = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecialChar = true;
            }
        }

        return hasLetter && hasDigit && hasSpecialChar;
    }
}
