package util;

public class StringFormatter {


    public StringFormatter() {

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
    
}
