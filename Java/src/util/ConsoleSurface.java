package util;

import java.util.HashMap;

import model.Performance;

public class ConsoleSurface {
	private int width = 100;
	private int height = 23;
	String default_char = " ";
	String grid [][];
	public ConsoleSurface() {
		grid = new String[height][width];
		init(default_char);
	}
	
	
	public int getWidth() {
		return width;
	}


	


	public int getHeight() {
		return height;
	}





	/**
	 * Place a string at position x,y
	 * @param x
	 * @param y
	 * @param txt
	 */
	public void putStringAt(int x, int y, String txt) {
		
		for (int i = 0; i < txt.length(); i++) {
			grid[y][x+i] = ""+ txt.charAt(i);
		}
	}
	
	/**
	 * Places a string surrounded by a box at position x,y
	 * @param x
	 * @param y
	 * @param txt
	 */
	public void putStringBoxAt(int x, int y, String txt) {
		
		int l = txt.length();
		drawBoxAt(x, y, l+1 , 2);
		
		putStringAt(x+1, y+1, txt);
	}
	
	
	/**
	 * Draw a box with top left at x,y and a width and height
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawBoxAt(int x, int y, int w, int h) {
		String corner = "+";
		
		putCharAt(x, y, corner);
		drawHLine(x+1, y, w-1);
		
		putCharAt(x+w, y, corner);
		drawVLine(x+w, y+1, h-1);
		
		putCharAt(x, y+h, corner);
		drawHLine(x+1, y+h, w-1);
		
		putCharAt(x+w, y+h, corner);
		drawVLine(x, y+1, h-1);
		
	}
	
	public void drawLine(int x1, int y1, int x2, int y2) {
		
	}
	
	/**
	 * Draw a horizontal line from left to right
	 * @param x
	 * @param y
	 * @param length
	 */
	public void drawHLine(int x, int y, int length) {
		String linechar = "-";
		for (int i = 0; i < length && x+i < width; i++) {
			putCharAt(x + i, y, linechar);
		}
	}
	
	/**
	 * Draw a vertical line of given length from top to bottom starting at x,y
	 * @param x
	 * @param y
	 * @param length
	 */
	public void drawVLine(int x, int y, int length) {
		String linechar = "|";
		for (int i = 0; i < length && y+i < height; i++) {
			putCharAt(x, y+i, linechar);
		}
	}
	
	/**
	 * print the surface to the console
	 */
	
	public void print() {
		for (String[] row: grid) {
			for (String c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
		
	}
	
	/**
	 * Place a character at an x,y position on the surface
	 * @param x
	 * @param y
	 * @param c
	 */
	public void putCharAt(int x, int y, String c) {
		if (c!=null) {
		grid[y][x] = ""+ c.charAt(0);
		}
	}
	
	public void putHMenuAt(int x, int y, Menu menu) {
		String menuLine;
		int xOffset = 0;
		HashMap<String, String> menumap = menu.toMap();
		for (String k : menumap.keySet()) {
			menuLine = "";
			menuLine += k + ": "  + menumap.get(k)+ " ";
			putStringBoxAt(x + xOffset, y, menuLine);
			xOffset += menuLine.length() +1;  
		}
	}
	
	public void putSelectionAt(int x, int y, PerformanceSelector sel) {
		drawBoxAt(x, y, getWidth()-5, 14);
		drawBoxAt(x, y, getWidth()-5, 2);
		drawBoxAt(x+3,y,sel.title.getWidth()+1,2);
		HashMap<String, Performance> sm = sel.toMap();
		String line = "";
		int yOffset = 3;
		
		for (String k : sm.keySet()) {
			int xOffset = 1;
			line = "";
			String a = " ";
			if(sel.getSelected()!=null) {
			if(sm.get(k).getPerformanceID()==sel.getSelected().getPerformanceID()) {
				a="*";
			}}
			line += k + ": "+a  + sm.get(k).getTitle();
			putStringAt(x+xOffset ,y + yOffset, line);
			xOffset+= 5 + sel.title.getWidth();
			putStringAt(x+xOffset,y+yOffset,sm.get(k).getType().getType());
			xOffset+= sel.type.getWidth();
			
			putStringAt(x+xOffset,y+yOffset,sm.get(k).getTime());
			xOffset+= sel.time.getWidth();
			
			putStringAt(x+xOffset,y+yOffset,sm.get(k).getDateString());
			xOffset+= sel.date.getWidth();
			
			putStringAt(x+xOffset,y+yOffset,sm.get(k).getDuration()+"");
			xOffset+= sel.duration.getWidth();
			
			putStringAt(x+xOffset,y+yOffset,sm.get(k).getType().getLanguage());
			xOffset+= sel.language.getWidth();
			
			String totSeats = "" + (sm.get(k).getStallsAvailable() + sm.get(k).getCircleAvailable());
			putStringAt(x+xOffset,y+yOffset,totSeats);
			xOffset+= sel.seats.getWidth();
			
//			putStringAt(x+xOffset,y+yOffset,sm.get(k).getpr);
//			xOffset+= sel.price.getWidth();
			
			
			yOffset ++; 
			   
		}
	}
	
	/**
	 * fill surface with spaces
	 * @param default_char
	 */
	
	private void init(String default_char) {
		for (int row = 0; row < height; row++) {
			for (int c = 0; c < width; c++) {
				grid[row][c]= default_char;
			}
		}
		
	}
	
	public void clear() {
		init(default_char);
	}
	
	

}

