package util;

import java.util.ArrayList;
import java.util.HashMap;
import model.Performance;
// Creates a hashmap of performance objects and assigns a letter
public class PerformanceSelector extends PageElement{
	
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
		super();
		currentChar=startChar;
		items = new HashMap<String, Performance>();
		setXY(2,4);
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
	public Performance selectItem(String letter) {
		
		selected = null;
		if (items.containsKey(letter)) {
		selected = items.get(letter);
		}
		clear();
		return selected;
		
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

	@Override
	public void draw(ConsoleSurface s) {
		// TODO Auto-generated method stub
		s.drawBoxAt(x, y, s.getWidth()-5, 14);
		s.drawBoxAt(x, y, s.getWidth()-5, 2);
		s.drawBoxAt(x+3,y,title.getWidth()+1,2);
		HashMap<String, Performance> sm = toMap();
		String line = "";
		int yOffset = 3;
		
		for (String k : sm.keySet()) {
			int xOffset = 1;
			line = "";
			String a = " ";
			if(getSelected()!=null) {
			if(sm.get(k).getPerformanceID()==getSelected().getPerformanceID()) {
				a="*";
			}}
			line += k + ": "+a  + sm.get(k).getTitle();
			s.putStringAt(x+xOffset ,y + yOffset, line);
			xOffset+= 5 + title.getWidth();
			s.putStringAt(x+xOffset,y+yOffset,sm.get(k).getType().getType());
			xOffset+= type.getWidth();
			
			s.putStringAt(x+xOffset,y+yOffset,sm.get(k).getTime());
			xOffset+= time.getWidth();
			
			s.putStringAt(x+xOffset,y+yOffset,sm.get(k).getDateString());
			xOffset+= date.getWidth();
			
			s.putStringAt(x+xOffset,y+yOffset,sm.get(k).getDuration()+"");
			xOffset+= duration.getWidth();
			
			s.putStringAt(x+xOffset,y+yOffset,sm.get(k).getType().getLanguage());
			xOffset+= language.getWidth();
			
			String totSeats = "" + (sm.get(k).getStallsAvailable() + sm.get(k).getCircleAvailable());
			s.putStringAt(x+xOffset,y+yOffset,totSeats);
			xOffset+= seats.getWidth();
			
//			putStringAt(x+xOffset,y+yOffset,sm.get(k).getpr);
//			xOffset+= sel.price.getWidth();
			
			
			yOffset ++; 
			   
		}
		
	}

}
