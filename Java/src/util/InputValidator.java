package util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class InputValidator {
    private StringFormatter sFormatter;
    
    public InputValidator() {
        sFormatter = new StringFormatter();
    }

    /**
     * Check valid input of credit card
     * @param creditCard credit card number as a string
     * @return true for valid, false invalid
     */
    public Boolean checkValidCreditCard(String creditCard) {
        creditCard = creditCard.replaceAll("\\s", "");

        if (creditCard.matches("[0-9]+") && creditCard.length() == 16) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkValidDate(String ddmmyyyy) {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");

        if (ddmmyyyy.length() != 8) {
            return false;
        } else {
            try {
                LocalDate.parse(ddmmyyyy, dtFormatter);
            } catch (DateTimeException e) {
                return false;
            }
            return true;
        }
        
    }

}
