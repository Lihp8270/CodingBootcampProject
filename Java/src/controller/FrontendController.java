package controller;

import model.User;
import model.Performance;
import model.Performer;
import util.BackendController;

import java.util.ArrayList;

public class FrontendController {
	
	BackendController bController;
	ArrayList<Performance> allShows;
	//ArrayList<Performance> allPerfs;
	
	public FrontendController() {
		BackendController bController = new BackendController();
        User user = new User(bController.createNewUser());
        //allShows = new ArrayList<Performance>();
        allShows = bController.getAllShows();
        	
	}
    
    public static void main(String[] args) {
    	FrontendController browser = new FrontendController();
		browser.run();
    }
	
	public void run() {
		// display menu
		//displayShows();
		displayPerformances();
		
	}
	
	public void displayShows() {
		for (Performance p : allShows) {
			System.out.println(
       					p.getTitle() + " "
       					+ p.getType()
					);				
      }
	}
	
	public void displayPerformances() {
		//ArrayList<Performance> allPerfs;// = new ArrayList<Performance>();
		ArrayList<Performance> allPerfs = bController.getAllPerformances();
		
		for (Performance perf : allPerfs) {
			System.out.print(
    			
    			perf.getPerformanceID() +" "
    					+ perf.getDate() + " "
    					+ perf.getTime() + " "
    					+ perf.getTitle() + " "
    					+ perf.getType() + " "
    					+ perf.getStallsAvailable() + " "
    					+ perf.getCircleAvailable() + " "
    					+ perf.getPrice()
    					+  "\n");
		}
	}
	

    	
    	
    	
        // TEMP FOR TESTING
        //BackendController bController = new BackendController();
        //User user = new User(bController.createNewUser());
        //ArrayList<Performance> results = new ArrayList<Performance>();
        //ArrayList<Performance> allShows = new ArrayList<Performance>();
        //ArrayList<Performance> allPerfs = new ArrayList<Performance>();
        //results = bController.getShowsFromTitle("Oliver!");
        //allShows = bController.getAllShows();
        //allPerfs = bController.getAllPerformances();
        // results = bController.getShowsFromType("Theat");
        // results = bController.getShowsFromTime("evening");
        // results = bController.getShowsFromMaxDuration(140);

       


        // Get All Performances ADD THIS SEARCH

        //results = bController.getShowsFromDate(2022, 01, 06);
//
//        System.out.print("USER ID:");
//        System.out.println(user.getUserID());
//        System.out.println("---------");
//
//        for (Performance p : allShows) {
//        	System.out.println(
//        					p.getTitle());
//        }
//        System.out.println("========================");
//        
//        
//        //System.out.println(results);
//        for (Performance p : results) {
//     
//        	System.out.print(
//        					p.getPerformanceID() +" "
//        					+ p.getDate() + " "
//        					+ p.getTime() + " "
//        					+ p.getTitle() + " "
//        					+ p.getType() + " "
//        					+ p.getStallsAvailable() + " "
//        					+ p.getCircleAvailable() + " "
//        					+ p.getPrice()
//        					+  "\n");
//        	for (Performer actor : p.getType().getPerformers()) {
//        		System.out.println(actor.getName());
//        	}
//        	System.out.println();
//        }
//        
//        for (Performance perf : allPerfs) {
//        	System.out.print(
//        			
//        			perf.getPerformanceID() +" "
//        					+ perf.getDate() + " "
//        					+ perf.getTime() + " "
//        					+ perf.getTitle() + " "
//        					+ perf.getType() + " "
//        					+ perf.getStallsAvailable() + " "
//        					+ perf.getCircleAvailable() + " "
//        					+ perf.getPrice()
//        					+  "\n");
//        }
        
        
        
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
//    }
}
