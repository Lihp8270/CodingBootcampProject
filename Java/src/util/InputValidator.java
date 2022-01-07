package util;

public class InputValidator {
    
    public InputValidator() {

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

}
