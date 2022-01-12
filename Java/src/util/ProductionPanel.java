package util;

import java.util.ArrayList;

import model.Performance;
import model.Performer;
import model.Price;
/**
 * A PageElement object.
 * Displays the general information for a Performance, such as the description and performers.
 * @author JS
 *
 */
public class ProductionPanel extends PageElement{
	
	private Performance p;
	private ArrayList<Performer> productionPerformers;
	private ArrayList<Performer> musicPerformers;
	private ArrayList<Performer> allPerformers;
	private String title;
	private String description;
	private String showType;
	private String language;
	private String duration;
	private String adultPrice;
	private String childPrice;
	public ProductionPanel() {
		super();
	}
	
	public void setPerformance(Performance p) {
		this.p = p;
		
		title = p.getTitle();
		productionPerformers = p.getType().getProductionPerformers();
		musicPerformers = p.getType().getMusicPerformers();
		description = p.getDescription();
		showType = p.getType().getType();
		language = p.getType().getLanguage();
		duration = ""+p.getDuration();
		ArrayList<Price>prices = p.getPrices();
		
		adultPrice = prices.get(0).getPriceAsString();
		childPrice = prices.get(1).getPriceAsString();

		
	
		//update();
	}
	
	public int getShowID() {
		return p.getProductionID();
	}
	
	private void displayDescription(ConsoleSurface s) {
		int px, py;
		px = 10;
		py = 5;
		
				
	}
	
	private void displayMusicPerformers(ConsoleSurface s) {
		int px, py;
		px = 30;
		py = 8;
		for(Performer musician:musicPerformers) {
			String musicianRole = musician.getName() + " as " + musician.getProductionRoles().get(0);
			s.putStringAt(x+px, y+py, musicianRole);
			py++;
		}
	}
	
	
	
	
	private void displayPerformers(ConsoleSurface s) {
		int px, py;
		px = 10;
		py = 8;
		for(Performer actor:productionPerformers) {
			String actorRole = actor.getName() + " as " + actor.getProductionRoles().get(0);
			s.putStringAt(x+px, y+py, actorRole);
			py++;
		}
	}
	
	
	@Override
	public void draw(ConsoleSurface s) {
		// TODO Auto-generated method stub
		//s.putStringBoxAt(x+10, y+7, p.getTitle());
		//s.putStringBoxAt(x+10, y+3, "Show info, descriptSWion and performers");
		
		displayPerformers(s);
		displayMusicPerformers(s);

		
	}

}
