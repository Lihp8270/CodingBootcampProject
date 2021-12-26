package util;

import model.User;
import java.sql.ResultSet;

public class BackendController {
    private QueryController qController;
    
    public BackendController() {
        qController = new QueryController();
    }

    // This needs to return an ArrayList of shows,  ResultSet Cannot be used outside of a DB connection
    public ResultSet getShowsFromTitle(String searchTerm) {
        // Temp for testing
        ResultSet results = null;
        results = qController.buildSearchQuery("Title", searchTerm);

        return results;
    }

    public int createNewUser() {
        int userID = qController.createTempUser();
        
        return userID;
    }
}
