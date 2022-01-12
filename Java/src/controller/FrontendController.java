package controller;

import model.User;
import model.Performance;
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
import util.ProductionPanel;
import util.TextBox;
import util.TextLabel;
import util.TicketBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FrontendController {
	
	Page currentPage;
	
	Page welcomePage;
	Page basketPage;
	Page browseShowsPage;
	Page searchPage;
	Page productionPage;
	Page buyTicketsPage;

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
    	
		browser.run(); // comment out to run test
		
		// browser.test(); // uncomment to run test
		
    }
	
	public void test() {
		System.out.println(bController.addToBasket(1, 4, user, 10, "stalls"));
		System.out.println(bController.getBasket(user).getSizeOfBasket());
		
		System.out.println(bController.addToBasket(1, 4, user, 10, "stalls"));
		System.out.println(bController.getBasket(user).getSizeOfBasket());
		
		bController.removeAllFromBasket(user);

	}
	
    private void setup() {
    	
    	welcomePage = new Page("Welcome");
    	basketPage = new Page("Basket");
    	browseShowsPage = new Page("Browse Shows");
    	searchPage = new Page("Search");
    	buyTicketsPage = new Page("Buy Tickets");
    	productionPage = new Page("Show Page");
    	

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
		
		MenuItem addToBasket = new MenuItem("Add to Basket", ()-> buyTicketsPage.getTicketBuilder().sendToBasket() );
		MenuItem switchSeat = new MenuItem("Seat", ()-> buyTicketsPage.getTicketBuilder().switchSeatType() );
		MenuItem switchConcession = new MenuItem("Conc", ()-> buyTicketsPage.getTicketBuilder().switchConcessionType() );
		MenuItem setTicketQty = new MenuItem("Set Qty", ()-> buyTicketsPage.getTicketBuilder().setTicketQty());
		
		MenuItem productionInfo = new MenuItem("Show Info", ()-> getProdutionInfo());
		MenuItem getPerformancesFromShow = new MenuItem("Find Tickets for this Show", ()-> getPerformancesFromShow());
		
		
		
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
		brm.addMenuItem("3", productionInfo);
		brm.addMenuItem("4", gotoSearchPage);
		
		setDefaultElements(browseShowsPage);
		
		PerformanceSelector browseSelector = new PerformanceSelector();
		browseShowsPage.setpSelector(browseSelector);
		browseShowsPage.addElement(browseSelector);
		
	
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
		
		// production info page
		Menu pm = productionPage.getMenu();
		pm.addMenuItem("1", gotoWelcomePage);
		pm.addMenuItem("2", viewBasket);
		pm.addMenuItem("3", gotoBrowsePage);
		pm.addMenuItem("4", gotoSearchPage);
		pm.addMenuItem("5", getPerformancesFromShow);
		
		setDefaultElements(productionPage);
		ProductionPanel pPanel = new ProductionPanel();
		productionPage.setProductionPanel(pPanel);
		productionPage.addElement(pPanel);

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
			clearScreen();
			currentPage.show();
			userInput = sc.nextLine();
			currentPage.getMenu().runItem(userInput);
			
		}
	}
	

//	public void exitFrontEnd() {
//		System.out.println("Bye!");
//		System.exit(0);
//	}
	
	public void browseScreen() {
		//always browse all shows
		currentPage = browseShowsPage;
		currentPage.show();
		ArrayList<Performance> allShows = bController.getAllShows();

		browseShowsPage.getpSelector().clear();
		if (allShows.size()>0) {
			browseShowsPage.getpSelector().addPerformances(allShows);
			browseShowsPage.show();
		}

	}
	
	public void searchTitle() {
		currentPage = searchPage;
		
		currentPage.show();
		ConsoleSurface s = searchPage.getScreen();
		System.out.print("Please enter your search: ");
		String searchTerm = sc.nextLine();
		ArrayList<Performance> results = bController.getShowsFromTitle(searchTerm);

		searchPage.getpSelector().clear();
		if (results.size()>0) {
			searchPage.getpSelector().addPerformances(results);
			searchPage.show();
			
		} else {
			//TODO
			s.putStringBoxAt(35, 10, "No Shows found.");
		}				
      }
	
	
	public void getPerformancesFromShow() {
		currentPage = productionPage;
		int showID = productionPage.getProductionPanel().getShowID();
		ArrayList<Performance> results = bController.getShowsFromProductionID(showID);
		searchPage.getpSelector().clear();
		//System.out.println("Showid " +showID);
		if (results.size()>0) {
			currentPage = searchPage;
			searchPage.getpSelector().addPerformances(results);
			searchPage.show();
			
		} else {
			//no performances?
			System.out.println("No shows?");
		}
		
	}
	
	
	public void makeSelection() {
		Performance selectedP;
		ConsoleSurface s = searchPage.getScreen();
		System.out.print("Enter Letter of Show to Select: ");
		String l = sc.nextLine();
		selectedP = searchPage.getpSelector().selectItem(l.toUpperCase());
		if (selectedP!=null) {
			buyTicketsPage.getTicketBuilder().setPerformance(selectedP);
			currentPage = buyTicketsPage;
		}

	}
	
	public void getProdutionInfo() {
		Performance selectedShow;
		System.out.print("Enter Letter of Show to Select: ");
		String l = sc.nextLine();
		selectedShow = browseShowsPage.getpSelector().selectItem(l.toUpperCase());
		if (selectedShow!=null) {
			productionPage.getProductionPanel().setPerformance(selectedShow);
			currentPage = productionPage;
		}
		
	}
	
	public void checkout() {
		if(bController.getBasket(user).getSizeOfBasket()>0) {
		System.out.print("Enter your Card Number: ");
		String cc = sc.nextLine();
		if (bController.checkoutBasket(user, cc)) {
			System.out.println("OK!");
		}
		}
	}
	
	public void searchDate() {
		//TODO
		currentPage = searchPage;
		ConsoleSurface s = searchPage.getScreen();

		System.out.print("Enter a date (ddmmyyyy) or press return for Today: ");
		String searchTerm = sc.nextLine();

		
		ArrayList<Performance> results = bController.getShowsFromDate(searchTerm);
		searchPage.getpSelector().clear();
		if (results.size()>0) {
			searchPage.getpSelector().addPerformances(results);

		} else {
			//searchPage.show();
			// TODO
			s.putStringBoxAt(35, 10, "No Shows found.");
		}	

	}
	
	public static void clearScreen(){
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
		
	}
	
	public void basketScreen() {
		currentPage = basketPage;

	}
	
	public void searchScreen() {
		currentPage = searchPage;
		currentPage.show();
	}
	
	
	private void productionScreen() {
		currentPage = productionPage;
	}
	

}
	


