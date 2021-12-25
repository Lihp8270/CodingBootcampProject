package util;

import model.User;

import java.sql.ResultSet;

public class BackendController {
    private QueryController qController;
    private User user;
    
    public BackendController() {
        qController = new QueryController();
        user = new User(createNewUser());
    }

    private int createNewUser() {
        ResultSet results = null;
        int userID;

        results = qController.createTempUser();

        if (results != null) {
            // Process result set to get the userID and assign to userID variable

            return userID;
        } else {
            return 0;
        }
    }
}
