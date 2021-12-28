package util;

import java.util.ArrayList;

import model.Performance;
import model.ShowType;
import model.Concert;
import model.NonConcertWithMusic;
import model.Performer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BackendController {
    private DatabaseConnector dbConnector;
    
    public BackendController() {
        dbConnector = new DatabaseConnector();
    }

    // To Do: Join necessary tables to populate Performance model.
    // Test psuh
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
        ShowType sType = null;


        ArrayList<Performance> results = new ArrayList<Performance>();
        ResultSet rsSearch = null;

        // Pass Prepared Statement Object
        String querySearch = "SELECT performance.id, title, category_name, production_description, time_slot, performance_date, duration, price, production_language FROM performance JOIN production ON performance.production_id = production.id JOIN production_category ON production.category_id = production_category.id WHERE title = \"" + searchTerm + "\"" + " GROUP BY performance.id;";
        
        dbConnector.connect();
        rsSearch = dbConnector.runQuery(querySearch);

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
                            
                            break;
                        case "Concert": 

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

    private ArrayList<String> findPerformers(int performanceID) {
        ArrayList<String> performers = new ArrayList<String>();
        String performerSearch = "SELECT performer_name FROM performer JOIN production_performers ON production_performers.performer_id = performer.id JOIN production ON production_performers.production_id = production.id JOIN performance ON production.id = performance.production_id WHERE performance.id = " + performanceID + ";"; 
        ResultSet pResultSet = dbConnector.runQuery(performerSearch);

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
        String query = "SELECT COUNT(\"" + location + "\"" + ") FROM performance JOIN production ON performance.production_id = production.id JOIN ticket ON performance.id = ticket.performance_id JOIN seat ON ticket.seat_id = seat.id WHERE seat.location = 'Stalls' AND performance.id = " + performanceID + ";";
        ResultSet ticketsRS;
        
        // Check location is valid and return 9999 if not
        if (location.equals("Stalls") || location.equals("Circle")) {
            validEntry = true;
        }

        if (validEntry == false) {
            return 9999;
        }

        ticketsRS = dbConnector.runQuery(query);

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
