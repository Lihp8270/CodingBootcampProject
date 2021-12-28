package model;

public class Performer {
    private String name;

    public Performer(String name) {
        this.name = name;
    }

    /**
     * Accessor for the performer name
     * @return returns the performer name as a String
     */
    public String getName() {
        return name;
    }
}
