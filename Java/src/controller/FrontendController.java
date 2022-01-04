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
        
        //results = bController.getShowsFromTitle("Oliver!");
        //results = bController.getAllShows();
        // results = bController.getShowsFromType("Theat");
        // results = bController.getShowsFromTime("evening");
        // results = bController.getShowsFromMaxDuration(140);

        results = bController.getShowsFromDate(2022, 01, 01);


        // Get All Performances ADD THIS SEARCH

        results = bController.getShowsFromDate(2022, 01, 03);

        System.out.println("USER ID");
        System.out.println(user.getUserID());
        System.out.println("---------");

        
        System.out.println(results);
        for (Performance p : results) {
     
        	System.out.print(
        					p.getPerformanceID() +" "
        					+ p.getDate() + " "
        					+ p.getTime() + " "
        					+ p.getTitle() + " "
        					+ p.getType() + " "
        					+ p.getStallsAvailable()
        					+  "\n");
        }
        
        
        
//        for (int i = 0; i < results.size(); i++) {
//            System.out.println("Title");
//            System.out.println(results.get(i).getTitle());
//            System.out.println("---------");
//            System.out.println("Type");
//            System.out.println(results.get(i).getType());
//            System.out.println("---------");
//            System.out.println("Language");
//            System.out.println(results.get(i).getType().getLanguage());
//            System.out.println("---------");
//            System.out.println("Date");
//            System.out.println(results.get(i).getDate());
//            System.out.println("---------");
//            System.out.println("Time");
//            System.out.println(results.get(i).getTime());
//            System.out.println("---------");
//            System.out.println("Performers");
//            for (int j = 0; j < results.get(i).getType().getPerformers().size(); j++) {
//                System.out.println(results.get(i).getType().getPerformers().get(j).getName());
//            }
//            System.out.println("---------");
//            System.out.println("---------");
//            System.out.println("---------");
//        }
    }
}
