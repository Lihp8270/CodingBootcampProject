package model;

public class Performance {
    private int performanceID;
    private String title;
    private ShowType type;
    private String description;
    private String time;
    private String date;
    private int duration;
    // TO DO
    
    // Prices Class
    // ArrayList<String>prices returned as String £x.xx
    // ArrayList<String>concessionName;
    private double price;
    //
    //
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
     * @param price
     * @param stallsAvailable
     * @param circleAvailable
     */
    public Performance(int performanceID, String title, String description, String time, String date,
            int duration, double price, int stallsAvailable, int circleAvailable) {
        this.performanceID = performanceID;
        this.title = title;
        this.description = description;
        this.time = time;
        this.date = date;
        this.duration = duration;
        this.price = price;
        this.stallsAvailable = stallsAvailable;
        this.circleAvailable = circleAvailable;
    }

    /**
     * Add show
     * @param type
     */
    public void addShowType(ShowType type) {
        this.type = type;
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
    public String getDate() {
        return date;
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
     * @return Full price of the performance as a double
     */
    public double getPrice() {
        return price;
    }

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
