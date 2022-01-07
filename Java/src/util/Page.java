package util;

public class Page {
	private ConsoleSurface screen;
	private Menu menu;
	private String pageName;
	
	public Page(String pageName) {
		this.pageName = pageName;
		screen = new ConsoleSurface();
		menu = new Menu(pageName);
		
	}
	
	public ConsoleSurface getScreen() {
		return screen;
	}


	public Menu getMenu() {
		return menu;
	}


	public String getPageName() {
		return pageName;
	}
	
	public void show() {
		screen.print();
	}


}
