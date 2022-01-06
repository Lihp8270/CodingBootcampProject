package util;

import java.util.ArrayList;
import java.util.Collections;

import java.time.LocalDate;
import java.time.LocalTime;

import model.Performance;
import model.Concert;
import model.NonConcertWithMusic;
import model.Performer;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class BackendController {
    private DatabaseConnector dbConnector;
    private StatementBuilder sBuilder;
    private PreparedStatement pStatement;
    private StringFormatter sFormatter;
    
    public BackendController() {
        dbConnector = new DatabaseConnector();
        sBuilder = new StatementBuilder();
        sFormatter = new StringFormatter();
        pStatement = null;
    }

    /**
     * Gets all unique productions that are currently scheduled with at least 1 performance
     * NOTE: This will return a full performance object.  Date and Time etc should not be used
     * For full accurate data, search by Title or other specific type for show information
     * @return Returns an ArrayList of Performances, details can be accessed using Performance, and relevant ShowType accessors
     */
    public ArrayList<Performance> getAllShows() {
        ArrayList<Performance> results = new ArrayList<Performance>();
        ResultSet rsSearch = null;

        dbConnector.connect();
        pStatement = sBuilder.buildSearchAllStatement(dbConnector.getConn());
        rsSearch = dbConnector.runQuery(pStatement);

        results = buildPerformanceReturn(rsSearch, true);
        
        dbConnector.close();

        return results;
    }

    /**
     * Returns all scheduled performances
     * @return Returns an array list of performance objects
     */
    public ArrayList<Performance> getAllPerformances() {
        String searchTerm = "";
        ArrayList<Performance> results = new ArrayList<Performance>();
        ResultSet rsSearch = null;
        PreparedStatement pStatement = null;
        searchTerm = sFormatter.createLikeSearchString(searchTerm);

        dbConnector.connect();
        pStatement = sBuilder.buildStringSearchFieldStatement(dbConnector.getConn(), "title", searchTerm);
        rsSearch = dbConnector.runQuery(pStatement);

        results = buildPerformanceReturn(rsSearch, false);

        dbConnector.close();

        return results;
    }

    /**
     * Search shows by max duration
     * @param maxDuration Maximum show duration in minutes as an integer
     * @return Returns an ArrayList of Performance
     */
    public ArrayList<Performance> getShowsFromMaxDuration(int maxDuration) {
        ArrayList<Performance> results = new ArrayList<Performance>();
        ResultSet rsSearch = null;
        
        dbConnector.connect();
        pStatement = sBuilder.buildDurationSearchFieldStatement(dbConnector.getConn(), maxDuration);
        rsSearch = dbConnector.runQuery(pStatement);
        results = buildPerformanceReturn(rsSearch, false);
        dbConnector.close();

        return results;
    }

    /**
     * Search shows by Date
     * @param year year as an integer 4 digits YYYY
     * @param month Month as a 2 digit integer MM
     * @param date Date as a 2 digit integer DD
     * @return Returns an ArrayList as a Performance
     */
    public ArrayList<Performance> getShowsFromDate(int year, int month, int date) {
        ArrayList<Performance> results = new ArrayList<Performance>();
        ResultSet rsSearch = null;
        String dateString;
        
        dateString = sFormatter.createDateString(year, month, date);

        dbConnector.connect();
        pStatement = sBuilder.buildDateSearchFieldStatement(dbConnector.getConn(), dateString);
        rsSearch = dbConnector.runQuery(pStatement);
        results = buildPerformanceReturn(rsSearch, false);
        dbConnector.close();

        return results;
    }

    /**
     * Search by Show Type
     * @param searchTerm Search term to search type from
     * @return Returns an ArrayList of Performance Objects
     */
    public ArrayList<Performance> getShowsFromType(String searchTerm) {
        ArrayList<Performance> results = new ArrayList<Performance>();
        ResultSet rsSearch = null;

        searchTerm = sFormatter.createLikeSearchString(searchTerm);
        
        dbConnector.connect();
        pStatement = sBuilder.buildStringSearchFieldStatement(dbConnector.getConn(), "type", searchTerm);
        rsSearch = dbConnector.runQuery(pStatement);
        results = buildPerformanceReturn(rsSearch, false);
        dbConnector.close();

        return results;
    }

    /**
     * Search by show time
     * @param searchTerm evening or matinee
     * @return Returns an ArrayList of Performance Objects
     */
    public ArrayList<Performance> getShowsFromTime(String searchTerm) {
        ArrayList<Performance> results = new ArrayList<Performance>();
        ResultSet rsSearch = null;

        searchTerm = sFormatter.createLikeSearchString(searchTerm);
        
        dbConnector.connect();
        pStatement = sBuilder.buildStringSearchFieldStatement(dbConnector.getConn(), "time", searchTerm);
        rsSearch = dbConnector.runQuery(pStatement);
        results = buildPerformanceReturn(rsSearch, false);
        dbConnector.close();

        return results;
    }

    /**
     * Search shows by title, using "Like"  Does not require a complete title name
     * @param searchTerm Search term to search the title for 
     * @return Returns an ArrayList of Performance Objects
     */
    public ArrayList<Performance> getShowsFromTitle(String searchTerm) {
        ArrayList<Performance> results = new ArrayList<Performance>();
        ResultSet rsSearch = null;
        PreparedStatement pStatement = null;
        searchTerm = sFormatter.createLikeSearchString(searchTerm);

        dbConnector.connect();
        pStatement = sBuilder.buildStringSearchFieldStatement(dbConnector.getConn(), "title", searchTerm);
        rsSearch = dbConnector.runQuery(pStatement);

        results = buildPerformanceReturn(rsSearch, false);

        dbConnector.close();

        return results;
    }

    /**
     * Creates a temporary new user
     * @return Returns the new userID
     */
    public int createNewUser() {
        ResultSet results = null;
        int userID = 0;
        
        dbConnector.connect();
        pStatement = sBuilder.buildInsertTempUserStatement(dbConnector.getConn());
        dbConnector.runQuery(pStatement);

        pStatement = sBuilder.buildGetRecentUserID(dbConnector.getConn());
        results = dbConnector.runQuery(pStatement);
        
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

    /**
     * Finds list of performers from given performanceID
     * @param performanceID
     * @return returns ArrayList of performer names
     */
    private ArrayList<String> findPerformers(int performanceID) {
        ArrayList<String> performers = new ArrayList<String>();

        ResultSet pResultSet = null;
        
        pStatement = sBuilder.buildGetPerformersStatement(dbConnector.getConn(), performanceID);
        pResultSet = dbConnector.runQuery(pStatement);

        

        if (pResultSet != null) {
            try {
                while (pResultSet.next()) {
                    performers.add(pResultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return performers;
    }

    private ArrayList<Integer> findPerformersID(int performanceID) {
        ArrayList<Integer> performers = new ArrayList<Integer>();

        ResultSet pResultSet = null;
        
        pStatement = sBuilder.buildGetPerformersIDStatement(dbConnector.getConn(), performanceID);
        pResultSet = dbConnector.runQuery(pStatement);

        

        if (pResultSet != null) {
            try {
                while (pResultSet.next()) {
                    performers.add(pResultSet.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return performers;
    }

    /**
     * Find number of tickets sold for a performance
     * @param location Only available options are Circle or Stalls
     * @param performanceID performance ID to search for
     * @return returns number of available tickets or 9999 on error
     */
    private int getAvailableTickets(String location, int performanceID) {
        PreparedStatement maxTicketsStatement;
        ResultSet maxTicketsRS;
        int maxTickets = 0;

        int ticketsFound = 0;
        boolean validEntry = false;
        ResultSet ticketsRS;
        
        // Check location is valid and return 9999 if not
        if (location.equals("Stalls") || location.equals("Circle")) {
            validEntry = true;
        }

        if (validEntry == false) {
            return 9999;
        }

        maxTicketsStatement = sBuilder.getMaxTicketsStatement(dbConnector.getConn(), location);
        maxTicketsRS = dbConnector.runQuery(maxTicketsStatement);

        if (maxTicketsRS != null) {
            try {
                while (maxTicketsRS.next()) {
                    maxTickets = maxTicketsRS.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        pStatement = sBuilder.buildGetTicketsStatement(dbConnector.getConn(), location, performanceID);
        ticketsRS = dbConnector.runQuery(pStatement);

        if (ticketsRS != null) {
            try {
                while (ticketsRS.next()) {
                    ticketsFound = ticketsRS.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return maxTickets - ticketsFound;

        // if (location == "Stalls") {
        //     return maxStalls - ticketsFound;
        // } else {
        //     return maxCircle - ticketsFound;
        // }
    }


    /**
     * Check if performer is a production performer in a given performance
     * @param performerID performer ID to search
     * @param performanceID performance ID to search
     * @return returns true or false
     */
    private Boolean isPerformerProduction(int performerID, int performanceID) {
        ResultSet assignedShowsRS;
        PreparedStatement pStatement;
        Boolean returnResult = false;

        pStatement = sBuilder.buildGetCountOfProductionStatement(dbConnector.getConn(), performerID, performanceID);

        assignedShowsRS = dbConnector.runQuery(pStatement);

        if (assignedShowsRS != null) {
            try {
                while (assignedShowsRS.next()) {
                    if (assignedShowsRS.getInt(1) > 0){
                        returnResult = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return returnResult;
    }

    /**
     * Check if performer is a music performer in a given performance
     * @param performerID performer ID to search
     * @param performanceID performance ID to search
     * @return returns true or false
     */
    private Boolean isPerformerMusic(int performerID, int performanceID) {
        ResultSet assignedShowsRS;
        PreparedStatement pStatement;
        Boolean returnResult = false;

        pStatement = sBuilder.buildGetCountOfMusicStatement(dbConnector.getConn(), performerID, performanceID);

        assignedShowsRS = dbConnector.runQuery(pStatement);

        if (assignedShowsRS != null) {
            try {
                while (assignedShowsRS.next()) {
                    if (assignedShowsRS.getInt(1) > 0){
                        returnResult = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return returnResult;
    }

    /**
     * Adds production roles to a given performer
     * @param performer performer object to add roles to
     * @param performanceID performance ID to get roles from
     */
    private void addProductionRoles(Performer performer, int performanceID) {
        ResultSet productionRolesRS;
        PreparedStatement pStatement;

        pStatement = sBuilder.buildGetProductionRolesStatement(dbConnector.getConn(), performer.getPerformerID(), performanceID);

        productionRolesRS = dbConnector.runQuery(pStatement);

        if (productionRolesRS != null) {
            try {
                while (productionRolesRS.next()) {
                    performer.addProductionRole(productionRolesRS.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Adds music roles to a given performer
     * @param performer performer object to add roles to
     * @param performanceID performance ID to get roles from
     */
    private void addMusicRoles(Performer performer, int performanceID) {
        ResultSet musicRolesRS;
        PreparedStatement pStatement;

        pStatement = sBuilder.buildGetMusicRolesStatement(dbConnector.getConn(), performer.getPerformerID(), performanceID);

        musicRolesRS = dbConnector.runQuery(pStatement);

        if (musicRolesRS != null) {
            try {
                while (musicRolesRS.next()) {
                    performer.addMusicRole(musicRolesRS.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Used to build performance search terms.  This method will Only include results which are AFTER today, or are evening shows on the day of showing
     * @param rsSearch Pass a result set
     * @param searchAll When set to true, this bypasses checks on time to allow for all unique scheduled shows to be added to performance
     * @return
     */
    private ArrayList<Performance> buildPerformanceReturn(ResultSet rsSearch, boolean searchAll) {
        int performanceID;
        String title;
        String description;
        String time;
        String date;
        int duration;
        double price;
        int stallsAvailable;
        int circleAvailable;
        String productionLanguage;
        Concert concertType = null;
        NonConcertWithMusic nonConcertType = null;
        int productionID;

        ArrayList<Performance> results = new ArrayList<Performance>();

        if (rsSearch != null) {
            try {
                while (rsSearch.next()) {
                    performanceID = rsSearch.getInt(1);
                    title = rsSearch.getString(2);

                    // Build ShowType
                    description = rsSearch.getString(4);
                    time = rsSearch.getString(5);
                    date = rsSearch.getString(6);
                    duration = rsSearch.getInt(7);
                    price = rsSearch.getDouble(8);
                    productionLanguage = rsSearch.getString(9);
                    productionID = rsSearch.getInt(10);

                    // If these return 9999 stop search process
                    stallsAvailable = getAvailableTickets("Stalls", performanceID);
                    circleAvailable = getAvailableTickets("Circle", performanceID);

                    Performance newPerformance = new Performance(performanceID, title, description, time, date, duration, price, stallsAvailable, circleAvailable, productionID);

                    switch (rsSearch.getString(3)) {
                        case "Theatre":
                            nonConcertType = new NonConcertWithMusic("Theatre", productionLanguage);
                            ArrayList<Integer> theatrePerformersID = findPerformersID(performanceID);
                            ArrayList<String> theatrePerformersNames = findPerformers(performanceID);

                            // Get Performers list
                            // ArrayList<String> theatrePerformers = findPerformers(performanceID);

                            for (int i = 0; i < theatrePerformersID.size(); i++) {
                                Performer performer = new Performer(theatrePerformersNames.get(i), theatrePerformersID.get(i));

                                if (isPerformerProduction(theatrePerformersID.get(i), performanceID)) {
                                    performer.performerIsProduction(true);
                                    addProductionRoles(performer, performanceID);
                                }

                                if (isPerformerMusic(theatrePerformersID.get(i), performanceID)) {
                                    performer.performerIsMusic(true);
                                    addMusicRoles(performer, performanceID);
                                }

                                nonConcertType.addPerformer(performer);
                            }

                            // Add Show Type to the performance
                            newPerformance.addShowType(nonConcertType);

                            break;
                        case "Musical":
                            nonConcertType = new NonConcertWithMusic("Musical", productionLanguage);

                            // Get list of performers IDs and Names
                            ArrayList<Integer> musicalPerformersID = findPerformersID(performanceID);
                            ArrayList<String> musicalPerformersNames = findPerformers(performanceID);

                            for (int i = 0; i < musicalPerformersID.size(); i++) {
                                Performer performer = new Performer(musicalPerformersNames.get(i), musicalPerformersID.get(i));

                                // Check if performer is show role
                                    // If TRUE, set flag in performer, and populate ArrayList
                                if (isPerformerProduction(musicalPerformersID.get(i), performanceID)) {
                                    performer.performerIsProduction(true);                               
                                    addProductionRoles(performer, performanceID);
                                } else {
                                    performer.performerIsProduction(false);
                                }

                                // Check if performer is music role
                                    // If TRUE, set flag in performer and populate ArrayList
                                if (isPerformerMusic(musicalPerformersID.get(i), performanceID)) {
                                    // Set flag and populate arraylist
                                    performer.performerIsMusic(true);
                                    addMusicRoles(performer, performanceID);
                                } else {
                                    performer.performerIsMusic(false);
                                }

                                nonConcertType.addPerformer(performer);
                            }

                            // Add Show Type to the performance
                            newPerformance.addShowType(nonConcertType);

                            break;
                        case "Opera":
                            nonConcertType = new NonConcertWithMusic("Opera", productionLanguage);
                            ArrayList<Integer> operaPerformersID = findPerformersID(performanceID);
                            ArrayList<String> operaPerformersNames = findPerformers(performanceID);

                            // Get Performers list

                            for (int i = 0; i < operaPerformersID.size(); i++) {
                                Performer performer = new Performer(operaPerformersNames.get(i), operaPerformersID.get(i));

                                if (isPerformerProduction(operaPerformersID.get(i), performanceID)) {
                                    performer.performerIsProduction(true);
                                    addProductionRoles(performer, performanceID);
                                } else {
                                    performer.performerIsProduction(false);
                                }

                                if (isPerformerMusic(operaPerformersID.get(i), performanceID)) {
                                    performer.performerIsMusic(true);
                                    addMusicRoles(performer, performanceID);
                                } else {
                                    performer.performerIsMusic(false);
                                }

                                nonConcertType.addPerformer(performer);
                            }

                            // Add Show Type to the performance
                            newPerformance.addShowType(nonConcertType);

                            
                            break;
                        case "Concert":
                            concertType = new Concert("Concert");
                            ArrayList<Integer> concertPerformersID = findPerformersID(performanceID);
                            ArrayList<String> concertPerformersNames = findPerformers(performanceID);

                            // Get Performers list

                            for (int i = 0; i < concertPerformersID.size(); i++) {
                                Performer performer = new Performer(concertPerformersNames.get(i), concertPerformersID.get(i));

                                if (isPerformerProduction(concertPerformersID.get(i), performanceID)) {
                                    performer.performerIsProduction(true);
                                    addProductionRoles(performer, performanceID);
                                } else {
                                    performer.performerIsProduction(false);
                                }

                                if (isPerformerMusic(concertPerformersID.get(i), performanceID)) {
                                    performer.performerIsMusic(true);
                                    addMusicRoles(performer, performanceID);
                                } else {
                                    performer.performerIsMusic(false);
                                }

                                concertType.addPerformer(performer);
                            }

                            // Add Show Type to the performance
                            newPerformance.addShowType(concertType);

                            
                            break;
                        default:
                        
                            break;
                    }

                    switch (time) {
                        case "matinee":
                            // If the time now is before 1300, or the Current date is before the show date, add to the list or if searching all
                            if (LocalTime.now().isBefore(LocalTime.of(13,0)) || LocalDate.now().isBefore(rsSearch.getDate(6).toLocalDate()) || searchAll == true) {
                                results.add(newPerformance);
                            }

                            break;
                        case "evening":
                        // If the time now is before 1800, or the Current date is before the show date, add to the list or if searching all
                            if (LocalTime.now().isBefore(LocalTime.of(18,0)) || LocalDate.now().isBefore(rsSearch.getDate(6).toLocalDate()) || searchAll == true) {
                                results.add(newPerformance);
                            }

                            break;
                        default:
                            break;
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        return results;
    }

    // TO DO REMOVE IF StringFormatter works
    // /**
    //  * Builds a date string to use in SQL Search
    //  * @param year YYYY
    //  * @param month MM
    //  * @param date DD
    //  * @return Returns a usable date string
    //  */
    // private String createDateString(int year, int month, int date) {
    //     String dateString;

    //     dateString = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(date);

    //     return dateString;
    // }

    // TODO
    // Return shopping basket object
    public void getBasket(User user) {

    }

    /**
     * Add a ticket to a users basket
     * @param concessionID 1 for Standard 2 for child
     * @param performanceID Performance ID the ticket is for
     * @param user Completed user object for the purchaser (this should include name, etc)
     * @param qty Quantity of tickets requested
     * @param location Location of tickets, circle or stalls
     */

     // TODO
     // Return error value, ENUM
    public Boolean addToBasket(int concessionID, int performanceID, User user, int qty, String location) {
        PreparedStatement pStatementInsert;
        PreparedStatement pStatementSeatLocation;
        ResultSet seatResults;
        ArrayList<Integer> seatNumbers;
        int seatID = 0;
        Boolean seatFound = false;

        int seatAvailability = 0;
        dbConnector.connect();    

        // Check Ticket availability
        // Remove case sensitivity
        switch (location) {
            case "stalls":
                seatAvailability = getAvailableTickets("Stalls", performanceID);
                seatID = 0;
                break;
            case "circle":
                seatAvailability = getAvailableTickets("Circle", performanceID);
                seatID = 80;
                break;
            default:
                break;
        }

        // Only add the tickets if there's seats available
        if (seatAvailability - qty >= 0) {
            for (int j = 0; j < qty; j++) {
                pStatementSeatLocation = sBuilder.buildSeatNumberStatement(dbConnector.getConn(), performanceID, location);
                seatResults = dbConnector.runQuery(pStatementSeatLocation);
                seatNumbers = new ArrayList<Integer>();

                // Build Array List of seat numbers sold or reserved
                if (seatResults != null) {
                    try {
                        while (seatResults.next()) {
                            seatNumbers.add(seatResults.getInt(1));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                } else {
                    seatID = 1;
                }

                for (int i = 0; i < seatNumbers.size(); i++) {
                    if ((i + 1) < seatNumbers.size()) {
                        if ((seatNumbers.get(i) + 1 != seatNumbers.get(i + 1)) && seatFound == false) {
                            seatID = seatNumbers.get(i) + 1;
                            seatFound = true;
                        }
                    }
                }

                if (seatNumbers.size() == 0) {
                    seatID++;
                    seatNumbers.add(seatID);
                    Collections.sort(seatNumbers);
                    seatFound = true;
                }

                if (seatFound == false) {
                    seatID = seatNumbers.get(seatNumbers.size() - 1) + 1;
                }

                pStatementInsert = sBuilder.buildAddToBasketStatement(dbConnector.getConn(), concessionID, performanceID, seatID, user.getUserID());

                dbConnector.runQuery(pStatementInsert);
            }

            dbConnector.close();
            return true;
        } else {
            dbConnector.close();
            return false;
        }
    }

    // TODO
    // Format price as String £x.xx

}
