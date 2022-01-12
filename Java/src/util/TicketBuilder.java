package util;

import java.util.Scanner;

import model.Performance;
import model.User;

public class TicketBuilder extends PageElement{
	
	private Performance p;
	private int ticketQty;
	private String seatType;
	private int concession;
	private User user;
	private BackendController bc;
	private int availStalls;
	private int availCircle;
	private Scanner sc;
	private String showInfo;
	
	
	
	
	public TicketBuilder(BackendController bc, User user) {
		
		this.bc = bc;
		this.user = user;
		this.ticketQty = 1;
		setXY(2,4);
		availStalls = 0; //p.getStallsAvailable();
		availCircle = 0; //p.getCircleAvailable();
		seatType = "stalls";
		concession = 1;
	}
	
	public void setTicketQty() {
		sc = new Scanner(System.in);
		System.out.println("Number of tickets: ");
		int t = 0;
		if (sc.hasNextInt()) {
			t = sc.nextInt();
			if (seatType.equals("stalls")) {
				if (t<= availStalls) {
					ticketQty = t;
				}
			}else {
				if (t<= availCircle) {
					ticketQty = t;
				}
			}
		}
		update();
	}
	
	public void switchSeatType() {
		if (seatType.equals("stalls")) {
			seatType = "circle";
		}else {
			seatType = "stalls";
		}
		update();
	}
	
	public void switchConcessionType() {
		if (concession==1) {
			concession=0;
			
		}else {
			concession=1;
		}
		update();
	}
	
	public void sendToBasket() {
		//TODO
		System.out.println(" Conc: " + concession + " pID: "+ p.getPerformanceID()+" uID: " + user.getUserID() + " Qty: " + ticketQty+ " Seat: " + seatType);
		bc.addToBasket(concession, p.getPerformanceID(), user, ticketQty, seatType);
		update();
		this.ticketQty = 1;
		
	}
	
	public void setPerformance(Performance p) {
		this.p = p;
		update();
	}
	
	public void update() {
		// TODO
		// need to get performance by performance id from bc to get available ticket counts after adding to basket
		//int pid = p.getPerformanceID();
		// setPerformance(bc....get);
		
		availStalls = p.getStallsAvailable();
		availCircle = p.getCircleAvailable();
		showInfo = p.getTitle() + " " + p.getTime() + " " + p.getDateString();
		
		
	}
	

	@Override
	public void draw(ConsoleSurface s) {
		// TODO Auto-generated method stub
		int xOff = 32;
		update();
		s.putStringBoxAt((s.getWidth()/2) - showInfo.length()/2, y+0, showInfo);
		
		
		s.putStringBoxAt(x, y, "Available Seats");
		s.drawBoxAt(x, y+2,16, 2);
		s.drawBoxAt(x, y+4,16, 2);
		s.putStringAt(x+1, y+3, "Stalls: "+ availStalls);
		s.putStringAt(x+1, y+5, "circle: "+ availCircle);
		
		
		s.drawBoxAt((s.getWidth()/2) - 35/2, y+9 ,35 , 6);
		
		s.putStringAt(xOff+x +10, y+10, "Your Selection");
		s.putStringBoxAt(xOff+x, y+12, "Conc: "+ concession);
		s.putStringBoxAt(xOff+x+10, y+12, "Seat: "+ seatType);
		s.putStringBoxAt(xOff+x+25, y+12, "Qty: "+ ticketQty);
		
		
	}

}
