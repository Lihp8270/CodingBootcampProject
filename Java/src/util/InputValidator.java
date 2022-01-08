package util;

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
        creditCard = sFormatter.removeWhiteSpace(creditCard);

        if (creditCard.matches("[0-9]+") && (creditCard.length() >= 13 && creditCard.length() <= 19)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if a given year is a leap year
     * @param year year as an integer 4 digits
     * @return true for leap year, false if not
     */
    public Boolean isLeapYear(int year) {
        // If year is not divisible by 4 then it's not a leap year
        if ((year % 4) == 0) {
            if ((year % 400) == 0) {
                return true;
            } else {
                if ((year % 100) == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Check if date is valid
     * @param year as integer
     * @param month as integer
     * @param date as integer 
     * @return true / false
     */
    public Boolean checkValidDate(int year, int month, int date) {
        Integer checkDate = date;
        Integer checkMonth = month;
        Integer checkYear = year;

        String checkDateString = sFormatter.doubleDigitDate(checkDate.toString()) + sFormatter.doubleDigitDate(checkMonth.toString()) + sFormatter.doubleDigitDate(checkYear.toString());

        return checkValidDate(checkDateString);
    }

    /**
     * Check if date string is valid
     * @param ddmmyyyy ddmmyyyy
     * @return Returns true or false
     */
    public Boolean checkValidDate(String ddmmyyyy) {       
        // Check string is correct length and digits only
        if (ddmmyyyy.length() != 8 && ddmmyyyy.matches("[0-9]+")) {
            return false;
        } else {
            int year = sFormatter.getDateInts("year", ddmmyyyy);
            int month = sFormatter.getDateInts("month", ddmmyyyy);
            int date = sFormatter.getDateInts("date", ddmmyyyy);
            
            // Check year is valid
            if (year >= 2000 && year <= 2100) {
                // Check month is valid
                if (month >= 1 && month<= 12) {
                    // Check Date is greater than 0
                    if (date <= 0) {
                        return false;
                    }

                    switch (month) {
                        case 1:
                        if (date <= 31) {
                            return true;
                        } else {
                            return false;
                        }
                        case 2:
                            if (isLeapYear(year)) {
                                if (date <= 29) {
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                if (date <= 28) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        case 3:
                            if (date <= 31) {
                                return true;
                            } else {
                                return false;
                            }
                        case 4:
                            if (date <= 30) {
                                return true;
                            } else {
                                return false;
                            }
                        case 5:
                            if (date <= 31) {
                                return true;
                            } else {
                                return false;
                            }
                        case 6:
                            if (date <= 30) {
                                return true;
                            } else {
                                return false;
                            }
                        case 7:
                            if (date <= 31) {
                                return true;
                            } else {
                                return false;
                            }
                        case 8:
                            if (date <= 31) {
                                return true;
                            } else {
                                return false;
                            }
                        case 9:
                            if (date <= 30) {
                                return true;
                            } else {
                                return false;
                            }
                        case 10:
                            if (date <= 31) {
                                return true;
                            } else {
                                return false;
                            }
                        case 11:
                            if (date <= 30) {
                                return true;
                            } else {
                                return false;
                            }
                        case 12:
                            if (date <= 30) {
                                return true;
                            } else {
                                return false;
                            }
                        default:
                            return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        
    }

}
