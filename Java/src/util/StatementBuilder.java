// 05-01-22 1703
package util;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;

import java.time.LocalDate;

public class StatementBuilder {
    private PreparedStatement pStatement;

    public StatementBuilder() {
        pStatement = null;

    }


    /**
     * Build prepared statement to get a list of seat numbers for a given performance and seat location
     * @param conn Pass Connection after db connect
     * @param performanceID Integer of the performance ID
     * @param location circle or stalls
     * @return Returns a Prepared Statement ready for execution
     */
    public PreparedStatement buildSeatNumberStatement(Connection conn, int performanceID, String location) {
        String searchQuery = "SELECT seat_id FROM ticket JOIN seat ON ticket.seat_id = seat.id WHERE performance_id = ? AND location = ? ORDER BY seat_id ASC";

        try {
            pStatement = conn.prepareStatement(searchQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, performanceID);
            pStatement.setString(2, location);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    /**
     * Checkout and set tickets to purchased for a given user
     * @param conn Pass connection after DB Connects
     * @param userID User ID whose tickets to set as purchased
     * @return Returns a prepared Statement
     */
    public PreparedStatement buildCheckoutStatement(Connection conn, int userID) {
        String checkoutQuery = "UPDATE ticket SET ticket_status = 'purchased' WHERE customer_id = ?";

        try {
            pStatement = conn.prepareStatement(checkoutQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, userID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return pStatement;
    }

    /**
     * Set user as permanent
     * @param conn Pass connection after db connects
     * @param userID User ID to set as permanent
     * @return returns a prepared statement
     */
    public PreparedStatement buildPermanentUserStatement(Connection conn, int userID) {
        String permanentUserQuery = "UPDATE customer SET customer.temp_status = false WHERE customer.id = ?";

        try {
            pStatement = conn.prepareStatement(permanentUserQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, userID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return pStatement;
    }

    /**
     * Retrive shopping basket
     * @param conn Pass connection after db connect
     * @param userID Uesr ID for basket retrieval
     * @return PreparedStatement
     */
    public PreparedStatement buildBasketRetrieveStatement(Connection conn, int userID) {
       String basketSearch = "SELECT title, production_description, sale_price, location, concession_name, performance_date, time_slot, seat.id, performance_id FROM ticket JOIN performance ON ticket.performance_id = performance.id JOIN production ON performance.production_id = production.id JOIN seat ON ticket.seat_id = seat.id JOIN concession ON ticket.concession_id = concession.id WHERE customer_id = ? and ticket_status = 'basket'";

       try {
        pStatement = conn.prepareStatement(basketSearch,
            ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
            ResultSet.CONCUR_UPDATABLE);
        pStatement.setInt(1, userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    return pStatement;
   }

   /**
    * Remove a ticket from the users basket
    * @param conn Pass connection after DB Connects
    * @param userID User ID to remove from basket
    * @param performanceID performance ID to remove
    * @param seatID seat ID number to remove
    * @return returns a prepared statement
    */
    public PreparedStatement buildRemoveFromBasketStatement(Connection conn, int userID, int performanceID, int seatID) {
        String removeQuery = "DELETE FROM ticket WHERE customer_id = ? AND performance_id = ? AND seat_id = ? AND ticket_status = 'basket'";

        try {
            pStatement = conn.prepareStatement(removeQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, userID);
            pStatement.setInt(2, performanceID);
            pStatement.setInt(3, seatID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    
        return pStatement;
    }

    /**
    * Remove all tickets from a users basket
    * @param conn Pass connection after DB Connects
    * @param userID User ID to remove from basket
    * @return returns a prepared statement
    */
    public PreparedStatement buildRemoveAllFromBasketStatement(Connection conn, int userID) {
        String removeQuery = "DELETE FROM ticket WHERE customer_id = ? AND ticket_status = 'basket'";

        try {
            pStatement = conn.prepareStatement(removeQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, userID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    
        return pStatement;
    }

    /**
     * Build Add to basket prepared statement
     * @param conn Pass Connection after database connects
     * @param concessionID 1 for full price, 2 for child
     * @param performanceID Performance ID for basket
     * @param seatID Seat number to use
     * @param customerID Customer ID as an integer 
     * @return Prepared Statement
     */
    public PreparedStatement buildAddToBasketStatement(Connection conn, int concessionID, int performanceID, int seatID, int customerID, double salePrice) {

        String insertQuery = "INSERT INTO ticket (concession_id, performance_id, seat_id, customer_id, ticket_status, sale_price) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            pStatement = conn.prepareStatement(insertQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, concessionID);
            pStatement.setInt(2, performanceID);
            pStatement.setInt(3, seatID);
            pStatement.setInt(4, customerID);
            pStatement.setString(5, "basket");
            pStatement.setDouble(6, salePrice);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    /**
     * Build prepared statement to count the number of production roles
     * @param conn pass Connection after DB COnnect
     * @param performerID Performer ID to search
     * @param performanceID performane ID to search 
     * @return returns a PreparedStatement ready for execution
     */
    public PreparedStatement buildGetCountOfProductionStatement(Connection conn, int performerID, int performanceID) {
        String searchQuery = "SELECT COUNT(performer.id) FROM production_performers JOIN performer ON production_performers.performer_id = performer.id JOIN production ON production_performers.production_id = production.id JOIN performance ON production.id = performance.production_id WHERE performer.id = ? AND performance.id = ?";

        try {
            pStatement = conn.prepareStatement(searchQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, performerID);
            pStatement.setInt(2, performanceID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    /**
     * Build prepared statement to count the number of music roles
     * @param conn pass Connection after DB COnnect
     * @param performerID Performer ID to search
     * @param performanceID performane ID to search 
     * @return returns a PreparedStatement ready for execution
     */
    public PreparedStatement buildGetCountOfMusicStatement(Connection conn, int performerID, int performanceID) {
        String searchQuery = "SELECT COUNT(performer.id) FROM music_performers JOIN performer ON music_performers.performer_id = performer.id JOIN production ON music_performers.production_id = production.id JOIN performance ON production.id = performance.production_id WHERE performer.id = ? AND performance.id = ?";

        try {
            pStatement = conn.prepareStatement(searchQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, performerID);
            pStatement.setInt(2, performanceID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    /**
     * Build prepared statement to get the production roles of a given performer
     * @param conn pass Connection after DB COnnect
     * @param performerID Performer ID to search
     * @param performanceID performane ID to search 
     * @return returns a PreparedStatement ready for execution
     */
    public PreparedStatement buildGetProductionRolesStatement(Connection conn, int performerID, int performanceID) {
        String searchQuery = "SELECT production_role FROM production_performers JOIN performer ON production_performers.performer_id = performer.id JOIN production ON production_performers.production_id = production.id JOIN performance ON production.id = performance.production_id WHERE performer.id = ? AND performance.id = ?";

        try {
            pStatement = conn.prepareStatement(searchQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, performerID);
            pStatement.setInt(2, performanceID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    /**
     * Build prepared statement to get the music roles of a given performer
     * @param conn pass Connection after DB COnnect
     * @param performerID Performer ID to search
     * @param performanceID performane ID to search 
     * @return returns a PreparedStatement ready for execution
     */
    public PreparedStatement buildGetMusicRolesStatement(Connection conn, int performerID, int performanceID) {
        String searchQuery = "SELECT music_role FROM music_performers JOIN performer ON music_performers.performer_id = performer.id JOIN production ON music_performers.production_id = production.id JOIN performance ON production.id = performance.production_id WHERE performer.id = ? AND performance.id = ?";

        try {
            pStatement = conn.prepareStatement(searchQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, performerID);
            pStatement.setInt(2, performanceID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    /**
     * Search by maximum duration
     * @param conn Pass Connection after database connects
     * @param searchField Field to search (currently only duration)
     * @param intValue Max duration in minutes
     * @return Returns a prepared setatement
     */
    public PreparedStatement buildDurationSearchFieldStatement(Connection conn, int maxDuration) {
        String searchQuery;

        searchQuery = "SELECT performance.id, title, category_name, production_description, time_slot, performance_date, duration, price, production_language, production.id FROM performance JOIN production ON performance.production_id = production.id JOIN production_category ON production.category_id = production_category.id WHERE duration <= ? AND performance_date >= ? GROUP BY performance.id";

        try {
            pStatement = conn.prepareStatement(searchQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, maxDuration);
            pStatement.setDate(2, Date.valueOf(LocalDate.now()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    /**
     * Search by Date
     * @param conn Pass Connection after DB connects
     * @param dateString Date String in format YYYY-MM-DD
     * @return Returns prepared Statement
     */
    public PreparedStatement buildDateSearchFieldStatement(Connection conn, String dateString) {
        String searchQuery;

        searchQuery = "SELECT performance.id, title, category_name, production_description, time_slot, performance_date, duration, price, production_language, production.id FROM performance JOIN production ON performance.production_id = production.id JOIN production_category ON production.category_id = production_category.id WHERE performance_date = ? GROUP BY performance.id";

        try {
            pStatement = conn.prepareStatement(searchQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setDate(1, Date.valueOf(dateString));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;

    }

    /**
     * Search by field (String Types)
     * @param conn Pass connection after database connects
     * @param searchField Field to search (title, type, time (matinee/evening))
     * @param searchTerm Search term
     * @return Returns a prepared Statement
     */
    public PreparedStatement buildStringSearchFieldStatement(Connection conn, String searchField, String searchTerm) {
        String searchQuery;

        switch (searchField) {
            case "title":
                searchQuery = "SELECT performance.id, title, category_name, production_description, time_slot, performance_date, duration, price, production_language, production.id FROM performance JOIN production ON performance.production_id = production.id JOIN production_category ON production.category_id = production_category.id WHERE title LIKE ? AND performance_date >= ? GROUP BY performance.id";
                break;
            case "type":
                searchQuery = "SELECT performance.id, title, category_name, production_description, time_slot, performance_date, duration, price, production_language, production.id FROM performance JOIN production ON performance.production_id = production.id JOIN production_category ON production.category_id = production_category.id WHERE category_name LIKE ? AND performance_date >= ? GROUP BY performance.id";
                break;
            case "time":
                searchQuery = "SELECT performance.id, title, category_name, production_description, time_slot, performance_date, duration, price, production_language, production.id FROM performance JOIN production ON performance.production_id = production.id JOIN production_category ON production.category_id = production_category.id WHERE time_slot LIKE ? AND performance_date >= ? GROUP BY performance.id";
                break;
        
            default:
                return null;
        };

        try {
            pStatement = conn.prepareStatement(searchQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setString(1, searchTerm);
            pStatement.setDate(2, Date.valueOf(LocalDate.now()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    /**
     * Builds GetPerformers Statement
     * @param conn Pass connection after Database Connect
     * @param performanceID Performance ID to search
     * @return Returns prepared statement
     */
    public PreparedStatement buildGetPerformersStatement(Connection conn, int performanceID) {
        String getPerformers = "SELECT performer_name FROM performer JOIN production_performers ON production_performers.performer_id = performer.id JOIN production ON production_performers.production_id = production.id JOIN performance ON production.id = performance.production_id WHERE performance.id = ? UNION SELECT performer_name FROM performer JOIN music_performers ON music_performers.performer_id = performer.id JOIN production ON music_performers.production_id = production.id JOIN performance ON production.id = performance.production_id WHERE performance.id = ?";
        
        try {
            pStatement = conn.prepareStatement(getPerformers,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, performanceID);
            pStatement.setInt(2, performanceID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    public PreparedStatement buildSearchProdIDStatement(Connection conn, int productionID) {
        String prodSearch = "SELECT performance.id, title, category_name, production_description, time_slot, performance_date, duration, price, production_language, production.id FROM performance JOIN production ON performance.production_id = production.id JOIN production_category ON production.category_id = production_category.id WHERE production.id = ? AND performance_date >= ? GROUP BY performance.id";

        try {
            pStatement = conn.prepareStatement(prodSearch,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, productionID);
            pStatement.setDate(2, Date.valueOf(LocalDate.now()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    /**
     * Builds get performers ID Statement
     * @param conn Pass connection after database connect
     * @param performanceID performance ID to search
     * @return returns prepared statement
     */
    public PreparedStatement buildGetPerformersIDStatement(Connection conn, int performanceID) {
        String getPerformersID = "SELECT performer.id FROM performer JOIN production_performers ON production_performers.performer_id = performer.id JOIN production ON production_performers.production_id = production.id JOIN performance ON production.id = performance.production_id WHERE performance.id = ? UNION SELECT performer.id FROM performer JOIN music_performers ON music_performers.performer_id = performer.id JOIN production ON music_performers.production_id = production.id JOIN performance ON production.id = performance.production_id WHERE performance.id = ?";

        try {
            pStatement = conn.prepareStatement(getPerformersID,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, performanceID);
            pStatement.setInt(2, performanceID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    /**
     * Find all shows
     * @param conn Pass connection after database connection
     * @return Returns prepared statement
     */
    public PreparedStatement buildSearchAllStatement(Connection conn) {
        String searchAll = "SELECT performance.id, title, category_name, production_description, time_slot, performance_date, duration, price, production_language, production.id FROM performance JOIN production ON performance.production_id = production.id  JOIN production_category ON production.category_id = production_category.id WHERE performance_date >= ? GROUP BY production.id";

        try {
            pStatement = conn.prepareStatement(searchAll,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setDate(1, Date.valueOf(LocalDate.now()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    /**
     * Build search Tickets
     * @param conn Pass Connection after database connect
     * @param location Seat location, Stalls or Circle
     * @param performanceID Performance ID to search for
     * @return Returns a prepared statement
     */
    public PreparedStatement buildGetTicketsStatement(Connection conn, String location, int performanceID) {
        String searchTickets = "SELECT COUNT(location) FROM performance JOIN production ON performance.production_id = production.id JOIN ticket ON performance.id = ticket.performance_id JOIN seat ON ticket.seat_id = seat.id WHERE seat.location = ? AND performance.id = ?";

        try {
            pStatement = conn.prepareStatement(searchTickets,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setString(1, location);
            pStatement.setInt(2, performanceID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    /**
     * Insert temporary user
     * @param conn Pass connection after database has connected
     * @return Returns prepared statement
     */
    public PreparedStatement buildInsertTempUserStatement(Connection conn) {
        String insertTempUser = "INSERT INTO customer (id) VALUES (default)";

        try {
            pStatement = conn.prepareStatement(insertTempUser,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    /**
     * Returns the most recent user
     * @param conn Pass connection after database has connected
     * @return Returns prepared statement
     */
    public PreparedStatement buildGetRecentUserID(Connection conn) {
        String getNewestUser = "SELECT MAX(id) FROM customer";

        try {
            pStatement = conn.prepareStatement(getNewestUser,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }
    
    public PreparedStatement getMaxTicketsStatement(Connection conn, String location) {
        String maxTicketsString = "SELECT COUNT(id) FROM seat WHERE location = ?";

        try {
            pStatement = conn.prepareStatement(maxTicketsString,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
                pStatement.setString(1, location);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

    public PreparedStatement buildGetBasePriceStatement(Connection conn, int performanceID) {
        String basePriceSearch = "SELECT price FROM performance JOIN production ON performance.production_id = production.id JOIN production_category ON production.category_id = production_category.id WHERE performance.id = ?";

        try {
            pStatement = conn.prepareStatement(basePriceSearch,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
                pStatement.setInt(1, performanceID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;        
    }

    /**
     * Returns a single result containing concession multiplier
     * @param conn Pass Connection after DB Connect
     * @param concessionID Concencession ID to search
     * @return
     */
    public PreparedStatement buildGetConcessionMultiplierStatement(Connection conn, int concessionID) {
        String concessionSearch = "SELECT discount FROM concession WHERE id = ?";

        try {
            pStatement = conn.prepareStatement(concessionSearch,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
                pStatement.setInt(1, concessionID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement; 
    }

    public PreparedStatement buildConcessionStatement(Connection conn) {
        String concessionIDsSearch = "SELECT * FROM theatre.concession";

        try {
            pStatement = conn.prepareStatement(concessionIDsSearch,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }

}
