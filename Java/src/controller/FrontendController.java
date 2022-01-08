package controller;

import model.User;
import model.Performance;
import model.Performer;
import util.BackendController;
import util.ConsoleSurface;
import util.Menu;
import util.MenuItem;
import util.Page;
import util.PerformanceSelector;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FrontendController {
	
	Page currentPage;
	Page welcomePage;
	Page basketPage;
	Page browseShowsPage;
	Page searchPage;
	Page productionPage;
	
	BackendController bController;
	User user;
	Scanner sc;
	
	public FrontendController() {
		bController = new BackendController();
        user = new User(bController.createNewUser());
        sc = new Scanner(System.in);
        setup();
        	
	}
    
	public static void main(String[] args) {
    	FrontendController browser = new FrontendController();
		browser.run();
    }
	
    private void setup() {
    	
    	welcomePage = new Page("Welcome");
    	basketPage = new Page("Basket");
    	browseShowsPage = new Page("Browse Shows");
    	searchPage = new Page("Search");
    	Page productionPage = new Page("Show Page");
    	

    	MenuItem exit = new MenuItem("Exit", () -> exitFrontend());
		MenuItem viewBasket = new MenuItem("View your Basket", () -> currentPage = basketPage );
		MenuItem gotoWelcomePage = new MenuItem("Return to Start", () -> welcomeScreen() );
		MenuItem gotoBrowsePage = new MenuItem("Browse Shows", () -> browseScreen() );
		MenuItem SearchShowTitle = new MenuItem("Search for Show",() -> searchTitle() );
		MenuItem SearchShowDate = new MenuItem("Search by Date",()-> searchDate());
		MenuItem gotoSearchPage = new MenuItem("Search", ()-> currentPage = searchPage);
		
		
		// welcome page
		Menu wm = welcomePage.getMenu();
		
		wm.addMenuItem("1", exit);
		wm.addMenuItem("2", viewBasket);
		wm.addMenuItem("3", gotoBrowsePage);
		wm.addMenuItem("4", gotoSearchPage);
		
		initPage(welcomePage);

		
		// basket page
		Menu bm = basketPage.getMenu();
		
		bm.addMenuItem("1",gotoWelcomePage);
		
		initPage(basketPage);
		//bs.drawBoxAt(0, 0, bs.getWidth()-1, bs.getHeight()-1);
		//bs.putStringBoxAt(30, 10, "Basket");
		//bs.putHMenuAt(0, 20, bm);
		
		// Browse shows page
		Menu brm = browseShowsPage.getMenu();
		
		
		brm.addMenuItem("1",gotoWelcomePage);
		brm.addMenuItem("2", viewBasket);
		initPage(browseShowsPage);
		
	
		// Search page
		Menu sm = searchPage.getMenu();
		
		sm.addMenuItem("1", gotoWelcomePage);
		sm.addMenuItem("2", viewBasket);
		sm.addMenuItem("3", SearchShowTitle);
		sm.addMenuItem("4", SearchShowDate);
		
		initPage(searchPage);

		// finish and set current page
		//currentPage = welcomePage;
		welcomeScreen();
		
	}
	
	
	private void exitFrontend() {
		// TODO Auto-generated method stub
		System.out.println("Bye!");
		System.exit(0);
	}

	public void run() {
	
		String userInput="";
		while (true) {
			//currentPage.show();
			
			//initPage(currentPage);
			currentPage.show();
			userInput = sc.nextLine();
			currentPage.getMenu().runItem(userInput);
			
		}
	}
	
	public void displayShows() {
		//delete
		ArrayList<Performance> allShows = bController.getAllShows();
		for (Performance show : allShows) {
			System.out.println(
						  
       					  show.getTitle() + " "
       					+ show.getType() + " "
       					+ show.getProductionID()
					);				
      }
	}
	
	public void displayPerformanceList(ArrayList<Performance> perfs) {
		//delete
		
		//ArrayList<Performance> allPerfs = bController.getAllPerformances();
		for (Performance perf : perfs) {
			System.out.print(
    			
    			perf.getPerformanceID() +" "
    					+ perf.getDate() + " "
    					+ perf.getTime() + " "
    					+ perf.getTitle() + " "
    					+ perf.getType() + " "
    					+ perf.getStallsAvailable() + " "
    					+ perf.getCircleAvailable() + " "
    					
    					+  "\n");
		}
	}
	

	
	public void exitFrontEnd() {
		System.out.println("Bye!");
		System.exit(0);
	}
	
	public void browseScreen() {
		currentPage = browseShowsPage;
		ArrayList<Performance> allShows = bController.getAllShows();
		ConsoleSurface s = browseShowsPage.getScreen();
		s.drawBoxAt(5, 5, 40, 12);
		
		for (int i=0; i < allShows.size(); i++) {
			s.putStringAt(6, 6+i, allShows.get(i).getTitle());
		}
		
	}
	
	public void searchTitle() {
		currentPage = searchPage;
		initPage(currentPage);
		ConsoleSurface s = searchPage.getScreen();
		System.out.print("Please enter your search: ");
		String searchTerm = sc.nextLine();
		ArrayList<Performance> results = bController.getShowsFromTitle(searchTerm);
		
		PerformanceSelector selection = new PerformanceSelector();
		
		if (results.size()>0) {
			selection.addPerformances(results);
			
			s.putSelectionAt(2, 4, selection);
			
		} else {
			s.putStringBoxAt(35, 10, "No Shows found.");
		}				
      }
	
	public void searchDate() {
		//TODO
		currentPage = searchPage;
		initPage(currentPage);
		ConsoleSurface s = searchPage.getScreen();

		System.out.print("Enter a date (ddmmyyyy) or press return for Today: ");
		String searchTerm = sc.nextLine();

		
		ArrayList<Performance> results = bController.getShowsFromDate(searchTerm);
		s.drawBoxAt(5, 5, 40, 12);
		
		if (results.size()>0) {
			
			for (int i=0; i < results.size(); i++) {
				s.putStringAt(6, 6+i, results.get(i).getTitle());
			}
		} else {
			s.putStringBoxAt(35, 10, "No Shows found.");
		}	
		
	}
	
	public static void clrscr(){
	    // Clears Screen in java
		// Works in Windows (and Linux?)
		// https://stackoverflow.com/questions/2979383/how-to-clear-the-console
	    try {
	        if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	    } catch (IOException | InterruptedException ex) {}
	}
	
	

	
	private void initPage(Page p) {
		Menu pm = p.getMenu();
		ConsoleSurface ps = p.getScreen();
		String date =  LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		ps.clear();
		ps.drawBoxAt(0, 0, ps.getWidth()-1, ps.getHeight()-1);
		String basket = "Basket (" + bController.getBasket(user).getSizeOfBasket() +")";
		
		ps.putStringBoxAt((ps.getWidth()/2)-16, 0, "    -~=@  Theatre Royal  @=~-    ");
		ps.putStringBoxAt(0, 0, date);
		ps.putStringBoxAt(ps.getWidth()-basket.length()-2, 0, basket);
		ps.putHMenuAt(0, 20, pm);
		
	}
	
	private void welcomeScreen() {
		currentPage = welcomePage;
		initPage(welcomePage);
		ConsoleSurface s = welcomePage.getScreen();
		String t1 = "_____  _     ____   __   _____  ___   ____      ___   ___   _      __    _    ";
		String t2 = " | |  | |_| | |_   / /\\   | |  | |_) | |_      | |_) / / \\ \\ \\_/  / /\\  | |  ";
		String t3 = " |_|  |_| | |_|__ /_/--\\  |_|  |_| \\ |_|__     |_| \\ \\_\\_/  |_|  /_/--\\ |_|__ ";
		int cx = (s.getWidth()/2) -t1.length()/2;
		int cy = (s.getHeight()/2)-2;
		s.putStringAt(cx, cy+1, t1);
		s.putStringAt(cx, cy+2, t2);
		s.putStringAt(cx, cy+3, t3);
		//welcomePage.show();
	}
	
	
	private void initBrowseShowsPage() {
		
	}
	
	private void initProductionPage() {
		
	}
	
	private void initSearchPage() {
		
	}
	
	private void initBasketPage() {
		
	}
}
	


