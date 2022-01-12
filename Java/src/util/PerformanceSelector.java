package util;

import java.util.ArrayList;
import java.util.HashMap;
import model.Performance;

/**
 * A PageElement object.
 * Displays a list of Performance objects.
 * Allows a Performance to be selected by the letter printed next to it
 * @author JS
 *
 */
public class PerformanceSelector extends PageElement{
	
	private char startChar = 'A';
	private char currentChar;
	private Performance selected;
	
	protected SelectorColumn title = new SelectorColumn("Show");
	protected SelectorColumn type = new SelectorColumn("Show Type");
	protected SelectorColumn date= new SelectorColumn("Date");
	protected SelectorColumn time = new SelectorColumn("Time");
	protected SelectorColumn duration = new SelectorColumn("Length");
	protected SelectorColumn language = new SelectorColumn("Language ");
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
		resetWidths();
		for (Performance p : plist) {
			addItem(p);
		}
	}
	
	private void resetWidths() {
		title.resetWidth();
		type.resetWidth();
		date.resetWidth();
		time.resetWidth();
		duration.resetWidth();
		language.resetWidth();
		seats.resetWidth();
		price.resetWidth();
		
	}
	
	private void setColumnWidths(Performance p) {
		title.setMinWidth(p.getTitle().length());
		type.setMinWidth(p.getType().getType().length());
		date.setMinWidth(p.getDateString().length());
		time.setMinWidth(p.getTime().length());
		duration.setMinWidth(6);
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
		s.drawBoxAt(x, y, s.getWidth()-5, 14);
		s.drawBoxAt(x, y, s.getWidth()-5, 2);
		int xOff = 3;
		
		s.drawBoxAt(x+xOff,y,title.getWidth()+1,2);
		s.putStringAt(x+xOff+1, y+1, title.getLabel());
		xOff+= title.getWidth()+1;
		
		s.drawBoxAt(x+xOff,y,type.getWidth()+1,2);
		s.putStringAt(x+xOff+1, y+1, type.getLabel());
		xOff+= type.getWidth()+1;
		
		s.drawBoxAt(x+xOff,y,time.getWidth(),2);
		s.putStringAt(x+xOff+1, y+1, time.getLabel());
		xOff+= time.getWidth();
		
		s.drawBoxAt(x+xOff,y,date.getWidth(),2);
		s.putStringAt(x+xOff+1, y+1, date.getLabel());
		xOff+= date.getWidth();
		
		s.drawBoxAt(x+xOff,y,duration.getWidth(),2);
		s.putStringAt(x+xOff+1, y+1, duration.getLabel());
		xOff+= duration.getWidth();
		
		s.drawBoxAt(x+xOff,y,language.getWidth(),2);
		s.putStringAt(x+xOff+1, y+1, language.getLabel());
		xOff+= language.getWidth();
		
		s.drawBoxAt(x+xOff,y,seats.getWidth()+1,2);
		s.putStringAt(x+xOff+1, y+1, seats.getLabel());
		xOff+= seats.getWidth()+1;

		//s.putStringAt(x+5, y+1, title.getLabel());
		
		
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
			
			//putStringAt(x+xOffset,y+yOffset,sm.get(k).);
			//xOffset+= sel.price.getWidth();
			
			
			yOffset ++; 
			   
		}
		
	}

}
