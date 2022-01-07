package util;

public class InputValidator {
    
    public InputValidator() {

    }

    public Boolean checkValidCreditCard(String creditCard) {
        creditCard = creditCard.replaceAll("\\s", "");

        if (creditCard.matches("[0-9]+") && creditCard.length() == 16) {
            return true;
        } else {
            return false;
        }
    }

}
