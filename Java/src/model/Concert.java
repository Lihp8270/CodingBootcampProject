package model;

import java.util.ArrayList;

public class Concert extends ShowType {
    private ArrayList<Performer> performers;
    
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
}
