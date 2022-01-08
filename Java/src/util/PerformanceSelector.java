package util;

import java.util.ArrayList;
import java.util.HashMap;
import model.Performance;
// Creates a hashmap of performance objects and assigns a letter
public class PerformanceSelector {
	
	private char startChar = 'A';
	private char currentChar;
	private Performance selected;
	
	protected SelectorColumn title = new SelectorColumn("Show");
	protected SelectorColumn type = new SelectorColumn("Show Type");
	protected SelectorColumn date= new SelectorColumn("Date");
	protected SelectorColumn time = new SelectorColumn("Time");
	protected SelectorColumn duration = new SelectorColumn("Length");
	protected SelectorColumn language = new SelectorColumn("Lang.");
	protected SelectorColumn seats = new SelectorColumn("Avail. Seats");
	protected SelectorColumn price = new SelectorColumn("Ticket Price");
	

	private HashMap <String, Performance> items;
	
	public PerformanceSelector() {
		currentChar=startChar;
		items = new HashMap<String, Performance>();
	}
	
	public void addItem (Performance p) {
		items.put(""+currentChar, p);
		setColumnWidths(p);
		currentChar++;
	}
	
	/**
	 * 
	 * @param letter letter id of chosen performance
	 * 
	 */
	public void selectItem(String letter) {
		
		if (items.containsKey(letter)) {
		selected = items.get(letter);
		}
		
	}
	
	public HashMap<String, Performance> toMap() {
		return items;
		
	}
	
	public void addPerformances(ArrayList<Performance> plist) {
		for (Performance p : plist) {
			addItem(p);
		}
	}
	
	private void setColumnWidths(Performance p) {
		title.setMinWidth(p.getTitle().length());
		type.setMinWidth(p.getType().getType().length());
		date.setMinWidth(p.getDateString().length());
		time.setMinWidth(p.getTime().length());
		duration.setMinWidth(4);
		language.setMinWidth(p.getType().getLanguage().length());
		seats.setMinWidth(4);
		price.setMinWidth(4);
		
	}
	

	public void clear() {
		items.clear();
		currentChar=startChar;
	}

	public Performance getSelected() {
		return selected;
	}
	
	

}
