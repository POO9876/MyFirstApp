package com.picon.agbsn;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Capital {

    public static String toSentenceCase(String inputString) {
        String result = "";
        if (inputString.length() == 0) {
            return result;
        }
        char firstChar = inputString.charAt(0);
        char firstCharToUpperCase = Character.toUpperCase(firstChar);
        result = result + firstCharToUpperCase;
        boolean terminalCharacterEncountered = false;
        char[] terminalCharacters = {'.', '?', '!'};
        for (int i = 1; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            if (terminalCharacterEncountered) {
                if (currentChar == ' ') {
                    result = result + currentChar;
                } else {
                    char currentCharToUpperCase = Character.toUpperCase(currentChar);
                    result = result + currentCharToUpperCase;
                    terminalCharacterEncountered = false;
                }
            } else {
                char currentCharToLowerCase = Character.toLowerCase(currentChar);
                result = result + currentCharToLowerCase;
            }
            for (int j = 0; j < terminalCharacters.length; j++) {
                if (currentChar == terminalCharacters[j]) {
                    terminalCharacterEncountered = true;
                    break;
                }
            }
        }
        return result;
    }

    public static String ageCalc(String dob) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date parse = sdf.parse(dob);
        Calendar c = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        c.setTime(parse);

        int a = c.get(Calendar.YEAR);
        int b =  cal.get(Calendar.YEAR);

        System.out.println(c.get(Calendar.MONTH) + c.get(Calendar.DATE) + c.get(Calendar.YEAR));

        int newYear = b-a;

        return String.valueOf(newYear);

    }

}


