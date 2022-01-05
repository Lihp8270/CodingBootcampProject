package util;

public class ConsoleSurface {
	int width = 80;
	int height = 25;
	String default_char = " ";
	String grid [][];
	public ConsoleSurface() {
		grid = new String[height][width];
		init(default_char);
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
	
	

}

