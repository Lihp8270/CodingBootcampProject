package util;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        // Build switch statement for each of the search Field types
        String query = "SELECT * FROM performance JOIN showtable ON performance.show_id = showtable.id WHERE title = \"" + searchTerm + "\"" + ";";
        
        dbConnector.connect();
        results = dbConnector.runQuery(query);
        dbConnector.close();

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
        
        dbConnector.connect();
        if (dbConnector.runQuery(query) != null) {
            dbConnector.close();
            return true;
        } else {
            dbConnector.close();
            return false;
        }
        
    }

    public boolean getBasket(int userID) {
        
        //Update with actual query
        String query = "";
        
        dbConnector.connect();
        if (dbConnector.runQuery(query) != null) {
            dbConnector.close();
            return true;
        } else {
            dbConnector.close();
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
        
        dbConnector.connect();
        if (dbConnector.runQuery(query) != null) {
            dbConnector.close();
            return true;
        } else {
            dbConnector.close();
            return false;
        }
    }

    /**
     * Creates a temporary user for the session, if no purchase is made the temp user is auto deleted from the database
     * When a purchase is made the user record with this ID is updated
     * @return Return result set containing a single result with the userID which was created
     */
    public int createTempUser() {
        // Update with actual query
        // Insert new record into customer table then get the most recent result and return the resultset
        ResultSet results = null;
        int userID = 0;
        String query = "INSERT INTO customer (id) VALUES (default);";
        
        dbConnector.connect();

        dbConnector.runQuery(query);
        query = "SELECT MAX(id) FROM customer;";
        results = dbConnector.runQuery(query);
        
        if (results != null) {

            try {
                while (results.next()) {
                    userID = results.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbConnector.close();

            return userID;
        } else {
            dbConnector.close();

            return 0;
        }
    }

    public ResultSet getAllShows() {
        // Show table may need updating when table names are finalised
        ResultSet results = null;
        String query = "SELECT * FROM performance JOIN showtable ON performance.show_id = showtable.id;";
        
        dbConnector.connect();
        results = dbConnector.runQuery(query);
        dbConnector.close();
        if (results != null) {
            return results;
        } else {
            return null;
        }
    }
}
