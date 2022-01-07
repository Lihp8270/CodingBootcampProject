package model;

import java.util.ArrayList;

import java.time.LocalDate;

public class Performance {
    private int performanceID;
    private String title;
    private ShowType type;
    private String description;
    private String time;
    private String date;
    private int duration;
    private int productionID;
    private ArrayList<Price> prices;
    private int stallsAvailable;
    private int circleAvailable;

    /**
     * Performance object containing all required information to display and add performances to the cart
     * @param performanceID
     * @param title
     * @param description
     * @param time
     * @param date
     * @param duration
     * @param stallsAvailable
     * @param circleAvailable
     * @param productionID
     */
    public Performance(int performanceID, String title, String description, String time, String date,
        int duration, int stallsAvailable, int circleAvailable, int productionID, ArrayList<Price> prices) {
        this.performanceID = performanceID;
        this.title = title;
        this.description = description;
        this.time = time;
        this.date = date;
        this.duration = duration;
        this.stallsAvailable = stallsAvailable;
        this.circleAvailable = circleAvailable;
        this.productionID = productionID;
        this.prices = prices;
    }

    /**
     * Accessor for price objects
     * @return array list of price objects
     */
    public ArrayList<Price> getPrices() {
        return prices;
    }

    /**
     * Add show
     * @param type
     */
    public void addShowType(ShowType type) {
        this.type = type;
    }

    /**
     * Accessor for production ID
     * @return returns productionID as an integer
     */
    public int getProductionID() {
        return productionID;
    }

    /**
     * Accessor for Performance ID
     * @return returns performanceID as integer
     */
    public int getPerformanceID() {
        return performanceID;
    }

    /**
     * Accessor for Title
     * @return returns performance title as a String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Accessor for show type
     * @return returns the show type as an object.  Depending on the show type, accessor methods can be used to get details
     */
    public ShowType getType() {
        return type;
    }

    /**
     * Accessor for performance description
     * @return Returns description as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Accessor for show time
     * @return returns show time as string
     */
    public String getTime() {
        return time;
    }

    /**
     * Accessor for performance Date
     * @return returns date as a String
     */
    public String getDateString() {
        return date;
    }

    /**
     * Accessor for show Date
     * @return returns date as LocalDate object
     */
    public LocalDate getDate() {
        LocalDate ld = LocalDate.parse(getDateString());

        return ld;
    }

    /**
     * Accessor for show duration in minutes
     * @return returns duration in minutes as an integer
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Accessor for performance price
     * @return Full price of the performance as a formatted String
     */
    // public String getPrice() {
    //     return price;
    // }

    /**
     * Accessor for stalls tickets
     * @return Returns the number of tickets currently available in the stalls as an integer
     */
    public int getStallsAvailable() {
        return stallsAvailable;
    }

    /**
     * Accessor for circle tickets
     * @return Returns the number of itkcets currently availabine in the circle as an integer
     */
    public int getCircleAvailable() {
        return circleAvailable;
    }    

}
