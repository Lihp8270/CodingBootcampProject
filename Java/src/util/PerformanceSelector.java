package util;

import java.util.ArrayList;
import java.util.HashMap;
import model.Performance;
// Creates a hashmap of performances and assigns a letter
public class PerformanceSelector {
	
	private char startChar = 'A';
	private char currentChar;
	
	private HashMap <String, Performance> items;
	
	public PerformanceSelector() {
		currentChar=startChar;
		items = new HashMap<String, Performance>();
	}
	
	public void addItem (Performance p) {
		items.put(""+currentChar, p);
		currentChar++;
	}
	
	public int selectItem(String i) {
		int id = -1;
		if (items.containsKey(i)) {
		id = items.get(i).getPerformanceID();
		}
		return id;
	}
	
	public HashMap<String, Performance> toMap() {
		return items;
		
	}
	
	public void addPerformances(ArrayList<Performance> plist) {
		for (Performance p : plist) {
			addItem(p);
		}
	}
	
	public void clear() {
		items.clear();
	}
	
	

}
