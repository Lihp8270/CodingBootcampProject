package util;


import model.User;

public class BasketWidget extends PageElement{

	private String label;
	//private ShoppingBasket basket;
	private int basketSize;
	private BackendController bc;
	private User user;
	

	//public BasketWidget(ShoppingBasket basket) {
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
		label = "Basket (" + basketSize +")";
		setWidth(label.length()+2);
		setHeight(3);
	}
}
