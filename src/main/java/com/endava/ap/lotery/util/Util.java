package com.endava.ap.lotery.util;

import java.util.regex.Pattern;

public class Util {

    public static boolean validateEmail(String emailStr) {
        return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(emailStr).find();
    }
}
