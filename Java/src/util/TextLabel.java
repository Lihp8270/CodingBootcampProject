package util;


public class TextLabel extends PageElement {
	
	private String text;
	
	public TextLabel(String text) {
		super();
		setText(text);
		update();
		
	}
	
	public void setText(String text) {
		this.text = text;
		update();
	}
	
	@Override
	public void draw(ConsoleSurface s) {
		s.putStringAt(getX(), getY(), text);

	}
	
	public void update() {
		setWidth(text.length());
		setHeight(1);
		
		
	}


}
