package util;

import java.text.DecimalFormat;

public class StringFormatter {
    DecimalFormat df;

    public StringFormatter() {
        df = new DecimalFormat("0.00");
    }
    
    /**
     * Get date integers from a string in format ddmmyyyy
     * @param mode date, month, year
     * @param ddmmyyyy input string
     * @return returns selected integer
     */
    public Integer getDateInts(String mode, String ddmmyyyy) {
        switch (mode.toLowerCase()) {
            case ("date"):
                return Integer.parseInt(ddmmyyyy.substring(0, 2));
            case ("month"):
                return Integer.parseInt(ddmmyyyy.substring(2, 4));    
            case ("year"):
                return Integer.parseInt(ddmmyyyy.substring(4, 8));
            default:
                return 0;
        }
    }

    /**
     * Builds a date string to use in SQL Search
     * @param year YYYY
     * @param month MM
     * @param date DD
     * @return Returns a usable date string
     */
    public String createDateString(int year, int month, int date) {
        String dateString;

        dateString = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(date);

        return dateString;
    }

    /**
     * Creates a usable string for searching "Like"
     * @param searchTerm Search term to modify
     * @return returns searchable String
     */
    public String createLikeSearchString(String searchTerm) {
        return "%" + searchTerm + "%";
    }

    /**
     * Formats price from double to string with £
     * @param price Double to convert
     * @return Returns formatted string
     */
    public String formatPrice(Double price) {
        return "£" + df.format(price);
    }

    /**
     * Add leading zero to date values
     * @param value 
     * @return returns value with leading zero
     */
    public String doubleDigitDate(String value) {
        if (value.length() == 1) {
            return "0" + value;
        } else {
            return value;
        }
    }
    
}
