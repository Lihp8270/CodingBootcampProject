package controller;

import model.User;
import model.Performance;
import model.Performer;
import util.BackendController;
import util.BasketContents;
import util.BasketWidget;
import util.ConsoleSurface;
import util.DateWidget;
import util.Menu;
import util.MenuItem;
import util.Page;
import util.PageBackground;
import util.PerformanceSelector;
import util.TextBox;
import util.TextLabel;
import util.TicketBuilder;

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
	Page buyTicketsPage;
	
	//PerformanceSelector selector;
	
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
    	buyTicketsPage = new Page("Buy Tickets");
    	Page productionPage = new Page("Show Page");
    	

    	MenuItem exit = new MenuItem("Exit", () -> exitFrontend());
		MenuItem viewBasket = new MenuItem("View Basket", () -> basketScreen() );
		MenuItem gotoWelcomePage = new MenuItem("Return to Start", () -> welcomeScreen() );
		MenuItem gotoBrowsePage = new MenuItem("Browse Shows", () -> browseScreen() );
		MenuItem SearchShowTitle = new MenuItem("Search Shows",() -> searchTitle() );
		MenuItem SearchShowDate = new MenuItem("Search Dates",()-> searchDate());
		MenuItem gotoSearchPage = new MenuItem("Search", ()-> searchScreen());
		MenuItem selectPerformance = new MenuItem("(letter) Select", () -> makeSelection() );
		MenuItem gotoCheckout = new MenuItem("Checkout", () -> checkout() );
		MenuItem emptyBasket = new MenuItem("Empty Basket", ()-> bController.removeAllFromBasket(user) );
		
		MenuItem addToBasket = new MenuItem("Add", ()-> buyTicketsPage.getTicketBuilder().sendToBasket() );
		MenuItem switchSeat = new MenuItem("Seat", ()-> buyTicketsPage.getTicketBuilder().switchSeatType() );
		MenuItem switchConcession = new MenuItem("Conc", ()-> buyTicketsPage.getTicketBuilder().switchConcessionType() );
		MenuItem setTicketQty = new MenuItem("Qty", ()-> buyTicketsPage.getTicketBuilder().setTicketQty());
		
		//selector = new PerformanceSelector();
		
		// welcome page
		Menu wm = welcomePage.getMenu();
		ConsoleSurface ws = welcomePage.getScreen();
		
		wm.addMenuItem("1", exit);
		wm.addMenuItem("2", viewBasket);
		wm.addMenuItem("3", gotoBrowsePage);
		wm.addMenuItem("4", gotoSearchPage);
		
		setDefaultElements(welcomePage);
		
		String t1 = "_____  _     ____   __   _____  ___   ____      ___   ___   _      __    _    ";
		String t2 = " | |  | |_| | |_   / /\\   | |  | |_) | |_      | |_) / / \\ \\ \\_/  / /\\  | |  ";
		String t3 = " |_|  |_| | |_|__ /_/--\\  |_|  |_| \\ |_|__     |_| \\ \\_\\_/  |_|  /_/--\\ |_|__ ";
		int cx = (ws.getWidth()/2) -t1.length()/2;
		int cy = (ws.getHeight()/2)-2;
		
		TextLabel banner1 = new TextLabel(t1);
		TextLabel banner2 = new TextLabel(t2);
		TextLabel banner3 = new TextLabel(t3);
		
		banner1.setXY(cx, cy+1);
		banner2.setXY(cx, cy+2);
		banner3.setXY(cx, cy+3);
		
		welcomePage.addElement(banner1);
		welcomePage.addElement(banner2);
		welcomePage.addElement(banner3);

		
		// basket page
		Menu bm = basketPage.getMenu();
		
		bm.addMenuItem("1",gotoWelcomePage);
		bm.addMenuItem("2", gotoCheckout);
		bm.addMenuItem("3", emptyBasket);
		
		setDefaultElements(basketPage);
		BasketContents bc = new BasketContents(bController, user);
		basketPage.addElement(bc);
		

		// Browse shows page
		Menu brm = browseShowsPage.getMenu();
		
		
		brm.addMenuItem("1",gotoWelcomePage);
		brm.addMenuItem("2", viewBasket);
		setDefaultElements(browseShowsPage);
		
	
		// Search page
		Menu sm = searchPage.getMenu();
		
		sm.addMenuItem("1", gotoWelcomePage);
		sm.addMenuItem("2", viewBasket);
		sm.addMenuItem("3", SearchShowTitle);
		sm.addMenuItem("4", SearchShowDate);
		sm.addMenuItem("5", selectPerformance);
		
		setDefaultElements(searchPage);
		
		PerformanceSelector selector = new PerformanceSelector();
		searchPage.setpSelector(selector);
		searchPage.addElement(selector);
		
		//buy Ticket page
		Menu tm = buyTicketsPage.getMenu();
		tm.addMenuItem("1", gotoWelcomePage);
		tm.addMenuItem("2", viewBasket);
		tm.addMenuItem("3", addToBasket);
		tm.addMenuItem("4", setTicketQty);
		tm.addMenuItem("5", switchSeat);
		tm.addMenuItem("6", switchConcession);
		tm.addMenuItem("4", setTicketQty);
		
		setDefaultElements(buyTicketsPage);
		TicketBuilder tBuilder = new TicketBuilder(bController, user);
		buyTicketsPage.setTicketBuilder(tBuilder);
		buyTicketsPage.addElement(tBuilder);
		
		

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
			
			//currentPage.draw();
			currentPage.show();
			userInput = sc.nextLine();
			currentPage.getMenu().runItem(userInput);
			
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
		//initPage(currentPage);
		currentPage.show();
		ConsoleSurface s = searchPage.getScreen();
		System.out.print("Please enter your search: ");
		String searchTerm = sc.nextLine();
		ArrayList<Performance> results = bController.getShowsFromTitle(searchTerm);
		
		//PerformanceSelector selection = new PerformanceSelector();
		
		searchPage.getpSelector().clear();
		if (results.size()>0) {
			searchPage.getpSelector().addPerformances(results);
			searchPage.show();
			
		} else {
			//TODO
			s.putStringBoxAt(35, 10, "No Shows found.");
		}				
      }
	
	public void makeSelection() {
		Performance selectedP;
		ConsoleSurface s = searchPage.getScreen();
		System.out.print("Enter Letter of Show to Select: ");
		String l = sc.nextLine();
		selectedP = searchPage.getpSelector().selectItem(l);
		if (selectedP!=null) {
			buyTicketsPage.getTicketBuilder().setPerformance(selectedP);
			currentPage = buyTicketsPage;
		}
		//bController.addToBasket(1, searchPage.getpSelector().getSelected().getPerformanceID(), user, 1, "Stalls");

	}
	
	public void checkout() {
		System.out.print("Enter your Card Number: ");
		String cc = sc.nextLine();
		if (bController.checkoutBasket(user, cc)) {
			System.out.println("OK!");
		}
		
	}
	
	public void searchDate() {
		//TODO
		currentPage = searchPage;
		//initPage(currentPage);
		ConsoleSurface s = searchPage.getScreen();

		System.out.print("Enter a date (ddmmyyyy) or press return for Today: ");
		String searchTerm = sc.nextLine();

		
		ArrayList<Performance> results = bController.getShowsFromDate(searchTerm);
		searchPage.getpSelector().clear();
		if (results.size()>0) {
			searchPage.getpSelector().addPerformances(results);
			
			//s.putSelectionAt(2, 4, selector);
			
		} else {
			//searchPage.show();
			// TODO
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
	
	

	
	private void setDefaultElements(Page p) {
		Menu pm = p.getMenu();
		ConsoleSurface ps = p.getScreen();
		
		//ps.clear();
		// add background to each page
		PageBackground pb = new PageBackground();
		p.addElement(pb);
		//ps.drawBoxAt(0, 0, ps.getWidth()-1, ps.getHeight()-1);
		
		// add basket widget to each page
		
		BasketWidget b = new BasketWidget(bController, user);
		b.setXY(ps.getWidth()-b.getWidth(), 0);
		p.addElement(b);
		
		// add Title textbox to each page
		TextBox tb = new TextBox("    -~=@  Theatre Royal  @=~-    ");
		tb.setXY((ps.getWidth()/2) - tb.getWidth()/2, 0);
		p.addElement(tb);
		
		
		// add date widget to each page
		DateWidget d = new DateWidget();
		d.setXY(0, 0);
		p.addElement(d);
		
		p.addElement(pm);
		//ps.putHMenuAt(0, 20, pm);
		
	}
	
	private void welcomeScreen() {
		currentPage = welcomePage;
		//initPage(welcomePage);
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
	
	public void basketScreen() {
		currentPage = basketPage;
		//initPage(basketPage);
	}
	
	public void searchScreen() {
		currentPage = searchPage;
		//initPage(searchPage);
		currentPage.show();
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
	


