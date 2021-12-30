package util;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;

import java.time.LocalDate;

public class StatementBuilder {
    private String searchTitle;
    private String searchAll;
    private String searchTickets;
    private String getPerformers;
    private String insertTempUser;
    private String getNewestUser;
    private String searchType;
    private PreparedStatement pStatement;

    public StatementBuilder() {
        pStatement = null;
        searchAll = "SELECT performance.id, title, category_name, production_description, time_slot, performance_date, duration, price, production_language, production.id FROM performance JOIN production ON performance.production_id = production.id  JOIN production_category ON production.category_id = production_category.id WHERE performance_date >= ? GROUP BY production.id";
        
        searchTitle = "SELECT performance.id, title, category_name, production_description, time_slot, performance_date, duration, price, production_language FROM performance JOIN production ON performance.production_id = production.id JOIN production_category ON production.category_id = production_category.id WHERE title LIKE ? AND performance_date >= ? GROUP BY performance.id";
        searchType = "SELECT performance.id, title, category_name, production_description, time_slot, performance_date, duration, price, production_language FROM performance JOIN production ON performance.production_id = production.id JOIN production_category ON production.category_id = production_category.id WHERE category_name LIKE ? AND performance_date >= ? GROUP BY performance.id";
        
        searchTickets = "SELECT COUNT(location) FROM performance JOIN production ON performance.production_id = production.id JOIN ticket ON performance.id = ticket.performance_id JOIN seat ON ticket.seat_id = seat.id WHERE seat.location = ? AND performance.id = ?";
        getPerformers = "SELECT performer_name FROM performer JOIN production_performers ON production_performers.performer_id = performer.id JOIN production ON production_performers.production_id = production.id JOIN performance ON production.id = performance.production_id WHERE performance.id = ?";
        insertTempUser = "INSERT INTO customer (id) VALUES (default)";
        getNewestUser = "SELECT MAX(id) FROM customer";
    }

    /**
     * Search by title
     * @param conn Pass Connection through after database connect
     * @param searchField field to search such as title
     * @param searchTerm search term to search
     * @return Returns a Prepared Statement
     */
    public PreparedStatement buildTitleSearchStatement(Connection conn, String searchTerm) {
        try {
            pStatement = conn.prepareStatement(searchTitle,
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
     * Search by type
     * @param conn Pass Connection through after database Connect
     * @param searchTerm Search term to search
     * @return Returns a prepared Statement
     */
    public PreparedStatement buildTypeSearchStatement(Connection conn, String searchTerm) {
        try {
            pStatement = conn.prepareStatement(searchType,
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
        try {
            pStatement = conn.prepareStatement(getPerformers,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
            pStatement.setInt(1, performanceID);
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
     * Build Tickets
     * @param conn Pass Connection after database connect
     * @param location Seat location, Stalls or Circle
     * @param performanceID Performance ID to search for
     * @return Returns a prepared statement
     */
    public PreparedStatement buildGetTicketsStatement(Connection conn, String location, int performanceID) {
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
        try {
            pStatement = conn.prepareStatement(getNewestUser,
                ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pStatement;
    }
    
}
