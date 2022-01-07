package model;

import java.util.ArrayList;

import java.time.LocalDate;

public class ShoppingBasket {
    private ArrayList<String> showName;
    private ArrayList<String> showDesc;
    private ArrayList<Double> salePriceDouble;
    private ArrayList<String> salePriceString;
    private ArrayList<String> location;
    private ArrayList<String> concessionName;
    private ArrayList<LocalDate> showDate;
    private ArrayList<String> showTime;
    private ArrayList<Integer> seatNumber;

    public ShoppingBasket() {
        showName = new ArrayList<String>();
        showDesc = new ArrayList<String>();
        salePriceDouble = new ArrayList<Double>();
        salePriceString = new ArrayList<String>();
        location = new ArrayList<String>();
        concessionName = new ArrayList<String>();
        showDate = new ArrayList<LocalDate>();
        showTime = new ArrayList<String>();
        seatNumber = new ArrayList<Integer>();
    }

    /**
     * Insert item into basket from database
     * @param showName
     * @param showDesc
     * @param salePriceDouble
     * @param salePriceString
     * @param location
     * @param concessionName
     * @param showDate
     * @param showTime
     * @param seatNumber
     */
    public void insertIntoBasket(String showName, String showDesc, Double salePriceDouble, String salePriceString, String location,
    String concessionName, LocalDate showDate, String showTime, int seatNumber) {

        this.showName.add(showName);
        this.showDesc.add(showDesc);
        this.salePriceDouble.add(salePriceDouble);
        this.salePriceString.add(salePriceString);
        this.location.add(location);
        this.concessionName.add(concessionName);
        this.showDate.add(showDate);
        this.showTime.add(showTime);
        this.seatNumber.add(seatNumber);
    }

    /**
     * Returns the size of the basket
     * @return Size of basket measured by ArrayList.size()
     */
    public int getSizeOfBasket() {
        return showName.size();
    }

    /**
     * Get name of the show of the ticket
     * @return Returns show name as String
     */
    public ArrayList<String> getShowName() {
        return showName;
    }

    /**
     * Show Description
     * @return Returns show description as a String
     */
    public ArrayList<String> getShowDesc() {
        return showDesc;
    }

    /**
     * Sale Price as a Double
     * @return returns sale price as a double for sums
     */
    public ArrayList<Double> getSalePriceDouble() {
        return salePriceDouble;
    }

    /**
     * Sale Price as a formatted String
     * @return returns sale price as £0.00 formatted string
     */
    public ArrayList<String> getSalePriceString() {
        return salePriceString;
    }

    /**
     * Seat Location
     * @return returns seat location as a String
     */
    public ArrayList<String> getLocation() {
        return location;
    }

    /**
     * Get the name of the ticket type 
     * @return returns concession name as a String
     */
    public ArrayList<String> getConcessionName() {
        return concessionName;
    }

    /**
     * Returns the date of the show
     * @return Returns the show date as a LocalDate type
     */
    public ArrayList<LocalDate> getShowDate() {
        return showDate;
    }

    /**
     * Time slot of show
     * @return returns either matinee or evening
     */
    public ArrayList<String> getShowTime() {
        return showTime;
    }

    /**
     * Returns seat number 
     * @return returns the seat number of the ticket
     */
    public ArrayList<Integer> getSeatNumber() {
        return seatNumber;
    }
   
}
