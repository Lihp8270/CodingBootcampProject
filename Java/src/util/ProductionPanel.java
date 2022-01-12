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
	private boolean hasMusic;
	public ProductionPanel() {
		super();
	}
	
	public void setPerformance(Performance p) {
		this.p = p;
		
		title = p.getTitle();
		productionPerformers = p.getType().getProductionPerformers();
		musicPerformers = p.getType().getMusicPerformers();
		hasMusic = musicPerformers.size()>0;
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
		px = 15;
		py = 5;
		s.putStringAt((s.getWidth()/2)-title.length()/2, py, title);
		s.putStringAt((s.getWidth()/2)-description.length()/2, py+1, description);
		
				
	}
	
	private void displayMusicPerformers(ConsoleSurface s) {
		int px, py;
		px = 60;
		py = 8;
		for(Performer musician:musicPerformers) {
			String musicianRole = musician.getName() + " as " + musician.getMusicRoles().get(0);
			s.putStringAt(x+px, y+py, musicianRole);
			py++;
		}
	}
	
	
	
	
	private void displayPerformers(ConsoleSurface s) {
		int px, py;
		px = 10;
		py = 8;
		if (!hasMusic) {
			px = (s.getWidth()/2)-13;
		}
		
		s.putStringAt(px+7, py, "Performers");
		py++;
		for(Performer actor:productionPerformers) {
			String actorRole = actor.getName() + " as " + actor.getProductionRoles().get(0);
			s.putStringAt(x+px, y+py, actorRole);
			py++;
		}
	}
	
	public void displayInfo(ConsoleSurface s) {
		int px, py;
		px= 10;
		py = 18;
		String info = "Show Type: " + showType + "  "
					+ "Length of Show: " + duration + " minutes "
					+ "Standard Seats: " + adultPrice + " "
					+ "Childrens Seats:" + childPrice + " "
				;
		s.putStringAt((s.getWidth()/2)-info.length()/2, py, info);
		
	}
	
	public void displayLanguage(ConsoleSurface s) {
		int py = 19;
		
		
		if(!showType.equals("concert")) {
			String l = "The Show will be performed in " + language;
			s.putStringAt((s.getWidth()/2)-l.length()/2, py, l);
		}
	}
	
	@Override
	public void draw(ConsoleSurface s) {
		// TODO Auto-generated method stub
		//s.putStringBoxAt(x+10, y+7, p.getTitle());
		//s.putStringBoxAt(x+10, y+3, "Show info, descriptSWion and performers");
		
		
		displayDescription(s);
		displayPerformers(s);
		displayMusicPerformers(s);
		displayInfo(s);
		displayLanguage(s);

		
	}

}
