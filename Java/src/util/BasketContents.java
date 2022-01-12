package util;


import model.User;

/**
 * A PageElement object.
 * Displays the User's ShoppingBasket contents
 * @author JS
 *
 */
public class BasketContents extends PageElement{

	private int ticketCount;
	private String basketPrice;
	private BackendController bc;
	private User user;
	

	public BasketContents(BackendController bc, User user) {
		super();
		this.bc = bc;
		this.user = user;
		setXY(40,5);
		this.update();

	}
	
	@Override
	public void draw(ConsoleSurface s) {
	
		update();
		s.putStringBoxAt(getX(), getY(), "Tickets");
		s.putStringBoxAt(getX()+8, getY(), "Total    ");
		
		s.drawBoxAt(x, y+2,18,2);
		
		s.putStringAt(getX()+1, getY()+3, ""+ticketCount);
		s.putStringAt(getX()+9, getY()+3, ""+basketPrice);
		
		
	}
	
	public void update() {
		ticketCount = bc.getBasket(user).getSizeOfBasket();
		basketPrice = bc.getBasket(user).getTotalPrice();
		
	}
}
