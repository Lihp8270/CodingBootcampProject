package model;

import java.util.ArrayList;

public class Concert extends ShowType {
    private ArrayList<Performer> performers;
    
    /**
     * Constructor for Concert Types
     * @param type
     */
    public Concert(String type) {
        super(type);
        performers = new ArrayList<Performer>();
    }
    
    public void addPerformer(Performer performer) {
        performers.add(performer);
    }

    /**
     * Accessor for Performers
     * @return returns an array list of performers
     */
    public ArrayList<Performer> getPerformers() {
        return performers;
    }

    public String getLanguage() {
        return "";
    }
}
