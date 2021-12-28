package model;

public class Performance {
    private int performanceID;
    private String title;
    private ShowType type;
    private String description;
    private String time;
    private String date;
    private int duration;
    private double price;
    private int stallsAvailable;
    private int circleAvailable;

    
    public Performance(int performanceID, String title, ShowType type, String description, String time, String date,
            int duration, double price, int stallsAvailable, int circleAvailable) {
        this.performanceID = performanceID;
        this.title = title;
        this.type = type;
        this.description = description;
        this.time = time;
        this.date = date;
        this.duration = duration;
        this.price = price;
        this.stallsAvailable = stallsAvailable;
        this.circleAvailable = circleAvailable;
    }


    public int getPerformanceID() {
        return performanceID;
    }


    public String getTitle() {
        return title;
    }

    public ShowType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public int getStallsAvailable() {
        return stallsAvailable;
    }

    public int getCircleAvailable() {
        return circleAvailable;
    }    

}
