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
		
		
	}
	

	@Override
	public void draw(ConsoleSurface s) {
		// TODO Auto-generated method stub
		update();
		s.putStringBoxAt(x, y+0, "Show: "+p.getTitle());
		s.putStringBoxAt(x, y+2, "Stalls: "+ availStalls);
		s.putStringBoxAt(x, y+4, "circle: "+ availCircle);
		s.putStringBoxAt(x, y+6, "Qty: "+ ticketQty);
		s.putStringBoxAt(x, y+8, "Seat: "+ seatType);
		s.putStringBoxAt(x, y+10, "Conc: "+ concession);
		
		
		
	}

}
