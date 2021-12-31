package util;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

import model.Performance;
import model.Concert;
import model.NonConcertWithMusic;
import model.Performer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class BackendController {
    private DatabaseConnector dbConnector;
    private StatementBuilder sBuilder;
    private PreparedStatement pStatement;
    
    public BackendController() {
        dbConnector = new DatabaseConnector();
        sBuilder = new StatementBuilder();
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
        
        dateString = createDateString(year, month, date);

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

        searchTerm = createLikeSearchString(searchTerm);
        
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

        searchTerm = createLikeSearchString(searchTerm);
        
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
        searchTerm = createLikeSearchString(searchTerm);

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

    /**
     * Find number of tickets sold for a performance
     * @param location Only available options are Circle or Stalls
     * @param performanceID performance ID to search for
     * @return returns number of available tickets or 9999 on error
     */
    private int getAvailableTickets(String location, int performanceID) {
        int maxStalls = 80;
        int maxCircle = 120;
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

        if (location == "Stalls") {
            return maxStalls - ticketsFound;
        } else {
            return maxCircle - ticketsFound;
        }
    }

    /**
     * Creates a usable string for searching "Like"
     * @param searchTerm Search term to modify
     * @return returns searchable String
     */
    private String createLikeSearchString(String searchTerm) {
        return "%" + searchTerm + "%";
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

                    // If these return 9999 stop search process
                    stallsAvailable = getAvailableTickets("Stalls", performanceID);
                    circleAvailable = getAvailableTickets("Circle", performanceID);

                    Performance newPerformance = new Performance(performanceID, title, description, time, date, duration, price, stallsAvailable, circleAvailable);

                    switch (rsSearch.getString(3)) {
                        case "Theatre":
                            nonConcertType = new NonConcertWithMusic("Theatre", productionLanguage);

                            // Get Performers list
                            ArrayList<String> theatrePerformers = findPerformers(performanceID);
                            for (int i = 0; i < theatrePerformers.size(); i++) {
                                Performer performer = new Performer(theatrePerformers.get(i));
                                nonConcertType.addPerformer(performer);
                            }

                            // Add Show Type to the performance
                            newPerformance.addShowType(nonConcertType);

                            break;
                        case "Musical":
                            nonConcertType = new NonConcertWithMusic("Musical", productionLanguage);

                            // Get Performers list
                            ArrayList<String> musicalPerformers = findPerformers(performanceID);
                            for (int i = 0; i < musicalPerformers.size(); i++) {
                                Performer performer = new Performer(musicalPerformers.get(i));
                                nonConcertType.addPerformer(performer);
                            }

                            // Add Show Type to the performance
                            newPerformance.addShowType(nonConcertType);

                            break;
                        case "Opera":
                            nonConcertType = new NonConcertWithMusic("Opera", productionLanguage);

                            // Get Performers list
                            ArrayList<String> operaPerformers = findPerformers(performanceID);
                            for (int i = 0; i < operaPerformers.size(); i++) {
                                Performer performer = new Performer(operaPerformers.get(i));
                                nonConcertType.addPerformer(performer);
                            }

                            // Add Show Type to the performance
                            newPerformance.addShowType(nonConcertType);
                            
                            break;
                        case "Concert":
                            concertType = new Concert("Concert");

                            // Get Performers list
                            ArrayList<String> concertPerformers = findPerformers(performanceID);
                            for (int i = 0; i < concertPerformers.size(); i++) {
                                Performer performer = new Performer(concertPerformers.get(i));
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

    /**
     * Builds a date string to use in SQL Search
     * @param year YYYY
     * @param month MM
     * @param date DD
     * @return Returns a usable date string
     */
    private String createDateString(int year, int month, int date) {
        String dateString;

        dateString = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(date);

        return dateString;
    }

}
