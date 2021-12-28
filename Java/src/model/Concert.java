package model;

import java.util.ArrayList;

public class Concert extends ShowType {
    private ArrayList<Performer> performers;
    
    // Not figured out how to access this list of performers yet
    public Concert(String type, Performer performer) {
        super(type);
        performers = new ArrayList<Performer>();
    }
    
    public void addPerformer(Performer performer) {
        performers.add(performer);
    }

    public ArrayList<Performer> getPerformers() {
        return performers;
    }

    public String getLanguage() {
        return "";
    }
}
