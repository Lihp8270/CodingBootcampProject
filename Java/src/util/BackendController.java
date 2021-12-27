package util;

import model.User;
import model.Performance;
import model.ShowType;
import model.Concert;
import model.NonConcertWithMusic;
import model.Performer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BackendController {
    private DatabaseConnector dbConnector;
    
    public BackendController() {
        dbConnector = new DatabaseConnector();
    }

    // To Do: Join necessary tables to populate Performance model.
    public ArrayList<Performance> getShowsFromTitle(String searchTerm) {
        ArrayList<Performance> results = new ArrayList<Performance>();
        ResultSet rs = null;

        String query = "SELECT * FROM performance JOIN showtable ON performance.show_id = showtable.id WHERE title = \"" + searchTerm + "\"" + ";";

        dbConnector.connect();
        rs = dbConnector.runQuery(query);
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

}
