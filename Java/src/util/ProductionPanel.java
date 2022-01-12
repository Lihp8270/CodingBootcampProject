package util;

import model.Performance;

public class ProductionPanel extends PageElement{
	
	private Performance p;

	public ProductionPanel() {
		super();
	}
	
	public void setPerformance(Performance p) {
		this.p = p;
		//update();
	}
	
	@Override
	public void draw(ConsoleSurface s) {
		// TODO Auto-generated method stub
		s.putStringBoxAt(x+10, y+7, p.getTitle());
		s.putStringBoxAt(x+10, y+3, "Show info, description and performers");
		
	}

}
