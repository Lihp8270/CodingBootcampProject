package util;

import java.util.ArrayList;

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
     * @return Returns an ArrayList of Performances, details can be accessed using Performance, and relevant ShowType accessors
     */
    public ArrayList<Performance> getAllShows() {
        // Temp holders for building Performance Object
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
        ResultSet rsSearch = null;

        dbConnector.connect();
        pStatement = sBuilder.buildSearchAllStatement(dbConnector.getConn());
        rsSearch = dbConnector.runQuery(pStatement);

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

                    results.add(newPerformance);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        dbConnector.close();

        return results;
    }

    /**
     * Search shows by title
     * @param searchTerm Search term to search the title for 
     * @return Returns an ArrayList of Performance Objects
     */
    public ArrayList<Performance> getShowsFromTitle(String searchTerm) {
        // Temp holders for building Performance Object
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
        ResultSet rsSearch = null;
        PreparedStatement pStatement = null;

        dbConnector.connect();
        pStatement = sBuilder.buildTitleSearchStatement(dbConnector.getConn(), searchTerm);
        rsSearch = dbConnector.runQuery(pStatement);

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

                    results.add(newPerformance);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        dbConnector.close();

        return results;
    }

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
}
