package util;


import java.util.HashMap;
import java.util.Map;


public class Menu extends PageElement{
	
	private String name;
	private HashMap <String, MenuItem> menuItems;
	
	/**
	 * 
	 * @param name
	 */
	public Menu(String name) {
		super();
		this.name = name;
		menuItems = new HashMap<String, MenuItem>();
		update();
	}
	
	public void addMenuItem (String itemCode, MenuItem item) {
		menuItems.put(itemCode, item);
		update();
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

	public void update() {
		setHeight(3);
	}
	@Override
	public void draw(ConsoleSurface s) {
		// TODO Auto-generated method stub
		String menuLine;
		int xOffset = 0;
		y = s.getHeight()-getHeight();
		HashMap<String, String> menumap = toMap();
		for (String k : menumap.keySet()) {
			menuLine = "";
			menuLine += k + ": "  + menumap.get(k)+ " ";
			s.putStringBoxAt(x + xOffset, y, menuLine);
			xOffset += menuLine.length() +1;  
		}
		
	}

}
