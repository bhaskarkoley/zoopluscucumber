package net.thucydides.showcase.cucumber;

import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

public class SeleniumConfig {
    /**
     * @return a LinkedList containing String arrays representing the browser combinations the test should be run against. The values
     * in the String array are used as part of the invocation of the test constructor
     */
    public static LinkedList browsersStrings() {
        //create Marker class to distinguish between local and cloud testing
        //set up class
        //create Browser class which contains browser/os/device combos
        //set up class
        //return LinkedList with browser/os/device combos
        return null;
    }

    //-------------------------------- DISTINGUISH BETWEEN LOCAL AND REMOTE TESTING ------------------------------//
    public static void runWhere() {
        // read the local.json to figure out where to run the tests
    }

    //-------------------------------- Handle different langauage sets ------------------------------//
    public static ResourceBundle locale_lang(String lang) {
        ResourceBundle mybundle = ResourceBundle.getBundle("labels");

        if (lang.equals(lang)) {
            Locale.setDefault(new Locale("nl", "NL"));
        }
        System.out.println("Current Locale: " + Locale.getDefault());
        mybundle = ResourceBundle.getBundle("labels");
        return mybundle;

    }
}
