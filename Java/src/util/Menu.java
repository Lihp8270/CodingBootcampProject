package util;


import java.util.HashMap;
import java.util.Map;


public class Menu {
	
	private String name;
	private HashMap <String, MenuItem> menuItems;
	
	/**
	 * 
	 * @param name
	 */
	public Menu(String name) {
		this.name = name;
		menuItems = new HashMap<String, MenuItem>();
	}
	
	public void addMenuItem (String itemCode, MenuItem item) {
		menuItems.put(itemCode, item);
	}
	
	public void runItem(String i) {
		if (menuItems.containsKey(i)) {
		menuItems.get(i).run();}
	}
	
	public void printMenu() {
		System.out.println(this.toString());
	}
	@Override
	public String toString() {
		
		String stringOut = "" + name;
		stringOut += "\n";
		
		for (Map.Entry<String, MenuItem> item : menuItems.entrySet()) {
			stringOut += item.getKey()+" "+  item.getValue().getDescription();
			stringOut += "\n";
		}
		
		return stringOut;
	}
	
	public HashMap<String, String> toMap() {
		HashMap<String, String> menuMap = new HashMap<>();
		
		for (Map.Entry<String, MenuItem> item : menuItems.entrySet()) {
			menuMap.put(item.getKey(), item.getValue().getDescription()) ;
		}
		return menuMap;
	}

}
