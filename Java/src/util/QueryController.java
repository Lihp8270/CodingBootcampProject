package util;

import util.BackendController;
import java.sql.ResultSet;

import com.mysql.cj.xdevapi.Result;

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
        String query = "SELECT * FROM shows WHERE title = " + searchTerm;
        
        results = dbConnector.runQuery(query);

        return results;
    }
}
