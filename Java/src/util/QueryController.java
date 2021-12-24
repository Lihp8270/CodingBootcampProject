package util;

import java.util.ArrayList;
import java.sql.ResultSet;

public class QueryController {
    private DatabaseConnector dbConnector;

    public QueryController() {
        dbConnector = new DatabaseConnector();
    }

    /**
     * Used to build a search query
     * @param searchField Title, Type, Time, Date, Duration
     * @param searchTerm Given string to search for of the given field
     * @return returns result set object
     */
    public ResultSet buildSearchQuery(String searchField, String searchTerm) {
        ResultSet results = null;

        //Update with actual query
        String query = "";
        
        results = dbConnector.runQuery(query);

        return results;
    }

    /**
     * Used to build query to add to basket
     * @param performanceID performanceID to be added to the basket
     * @param customerID the customerID of the user adding the item
     * @param seatLocation Stalls or Circle
     * @return returns true if query is a success or false if there's an error
     */
    public boolean addToBasket(int performanceID, int customerID, String seatLocation) {
        
        //Update with actual query
        String query = "";
        
        if (dbConnector.runQuery(query) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Builds query to purchase a ticket
     * @param ticketIDs ArrayList of integers containing ticketID's to be purchased
     * @param CustomerID CustomerID making the purchase
     * @param address1 customer Address line 1
     * @param address2 customer address line 2
     * @param postcode customer postcode
     * @param creditCard cusetomer credit card
     * @return returns true if success, or false if there's an error
     */
    public boolean buildPurchaseTicket(ArrayList<Integer> ticketIDs, int CustomerID, String address1,
                                        String address2, String postcode, String creditCard) {

        //Update with actual query
        String query = "";
        
        if (dbConnector.runQuery(query) != null) {
            return true;
        } else {
            return false;
        }
    }
}
