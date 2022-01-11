package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateWidget extends PageElement {
	
	String dateLabel;
	
	public DateWidget() {
		super();
		this.update();
	}

	@Override
	public void draw(ConsoleSurface s) {
		update();
		s.putStringBoxAt(getX(), getY(), dateLabel);

	}
	
	public void update() {
		dateLabel =  LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		setWidth(dateLabel.length()+2);
		setHeight(3);
	}

}
