package util;

/**
 * A PageElement object.
 * A TextBox object that can be drawn on a Page object, at a given position.
 * @author JS
 *
 */
public class TextBox extends PageElement {
	
	String textLabel;
	
	public TextBox(String label) {
		super();
		this.textLabel = label;
		this.update();
	}
	
	public void setTextLabel(String label) {
		this.textLabel=label;
		update();
	}

	@Override
	public void draw(ConsoleSurface s) {
		update();
		s.putStringBoxAt(getX(), getY(), textLabel);

	}
	
	public void update() {
		setWidth(textLabel.length()+2);
		setHeight(3);
	}

}
