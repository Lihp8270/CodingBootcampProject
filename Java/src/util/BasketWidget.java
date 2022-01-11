package util;

import model.ShoppingBasket;

public class BasketWidget extends PageElement{

	private String label;
	private ShoppingBasket basket;
	private int basketSize;
	

	public BasketWidget(ShoppingBasket basket) {
		super();
		this.basket = basket;
		//this.setXY(0,0);
		this.update();

	}
	
	@Override
	public void draw(ConsoleSurface s) {
	
		update();
		s.putStringBoxAt(getX(), getY(), label);
		
	}
	
	public void update() {
		basketSize = basket.getSizeOfBasket();
		//System.out.println(basketSize);
		label = "Basket (" + basketSize +")";
		setWidth(label.length()+2);
		setHeight(3);
	}
}
