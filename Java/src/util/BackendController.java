package util;

import util.DatabaseConnector;

public class BackendController {
    private QueryController qController;
    
    public BackendController() {
        qController = new QueryController();
    }
}
