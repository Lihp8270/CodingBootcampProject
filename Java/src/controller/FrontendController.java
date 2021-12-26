package controller;

import util.BackendController;
import model.User;

public class FrontendController {
    
    public static void main(String[] args) {
        BackendController bController = new BackendController();
        User user = new User(bController.createNewUser());

        bController.getShowsFromTitle("Oliver!");
    }
}
