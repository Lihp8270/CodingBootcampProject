package util;


/**
 * A PageElement object.
 * A simple background with a border for a Page.
 * @author JS
 *
 */
public class PageBackground extends PageElement {
	
	public PageBackground() {
		super();
		setWidth(0);
		setHeight(0);
	}
	
	@Override
	public void draw(ConsoleSurface s) {
		s.drawBoxAt(0, 0, s.getWidth()-1, s.getHeight()-1);

	}
}
