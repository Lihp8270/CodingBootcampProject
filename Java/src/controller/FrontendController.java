package controller;

import model.User;
import model.Performance;

import util.BackendController;

import java.util.ArrayList;

public class FrontendController {
    
    public static void main(String[] args) {
        // TEMP FOR TESTING
        BackendController bController = new BackendController();
        User user = new User(bController.createNewUser());
        ArrayList<Performance> results = new ArrayList<Performance>();
        
        bController.getShowsFromTitle("Oliver!");
    }
}
