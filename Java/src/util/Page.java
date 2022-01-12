package util;

import java.util.ArrayList;

public class Page {
	private ConsoleSurface screen;
	private Menu menu;
	private String pageName;
	private ArrayList<PageElement> elements;
	
	private PerformanceSelector pSelector;
	private BasketContents bContents; // dont need this?
	private TicketBuilder tBuilder;
	
	public Page(String pageName) {
		this.pageName = pageName;
		screen = new ConsoleSurface();
		menu = new Menu(pageName);
		elements = new ArrayList<>();
		
	}
	
	public void addElement(PageElement element) {
		elements.add(element);
	}
	
	public ConsoleSurface getScreen() {
		return screen;
	}


	public Menu getMenu() {
		return menu;
	}


	public String getPageName() {
		return pageName;
	}
	
	public void show() {
		draw();
		screen.print();
		
	}
	
	public void draw() {
		screen.clear();
		for (PageElement e: elements) {
			e.draw(screen);
		}
	}

	public PerformanceSelector getpSelector() {
		return pSelector;
	}

	public void setpSelector(PerformanceSelector pSelector) {
		this.pSelector = pSelector;
	}
	
	public void setTicketBuilder(TicketBuilder tBuilder) {
		this.tBuilder = tBuilder;
	}
	
	public TicketBuilder getTicketBuilder() {
		return tBuilder;
	}
	
	


}
