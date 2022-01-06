package util;

import java.text.DecimalFormat;

public class StringFormatter {
    DecimalFormat df;

    public StringFormatter() {
        df = new DecimalFormat("0.00");
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
     * Formates price from double to string with £
     * @param price Double to convert
     * @return Returns formatted string
     */
    public String formatPrice(Double price) {
        return "£" + df.format(price);
    }
    
}
