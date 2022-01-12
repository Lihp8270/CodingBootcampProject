package util;


import model.User;

/**
 * A PageElement object.
 * Displays the User's ShoppingBasket item count.
 * @author JS
 *
 */
public class BasketWidget extends PageElement{

	private String label;
	//private ShoppingBasket basket;
	private int basketSize;
	private BackendController bc;
	private User user;
	
	
	public BasketWidget(BackendController bc, User user) {
		super();
		this.bc = bc;
		this.user = user;
		this.update();

	}
	
	@Override
	public void draw(ConsoleSurface s) {
	
		update();
		s.putStringBoxAt(getX(), getY(), label);
		
	}
	
	public void update() {
		basketSize = bc.getBasket(user).getSizeOfBasket();
		
		String pad= "";
		
		if (basketSize < 1000)  pad = "";
		if (basketSize < 100)   pad = " ";
		if (basketSize < 10)   pad = "  ";
		
		label = "Basket (" + basketSize +")" + pad;
		setWidth(label.length()+2);
		setHeight(3);
	}
}
