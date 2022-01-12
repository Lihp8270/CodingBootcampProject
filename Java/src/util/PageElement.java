package util;

/**
 * A PageElement can be arranged and displayed on a Page object's ConsoleSurface.
 * @author JS
 *	
 */
public abstract class PageElement {
	
	protected int x,y;
	private int width, height;
	
	public PageElement() {
		this.setXY(0, 0);
		
	}
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}

	public void setXY(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}

	public int getWidth() {
		return width;
	}

	

	public int getHeight() {
		return height;
	}

	


	public abstract void draw(ConsoleSurface s);
	
}
