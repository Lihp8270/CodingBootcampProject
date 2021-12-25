package model;

import java.util.ArrayList;

public class NonConcertWithMusic extends ShowType {
    private ArrayList<Performer> performers;
    private String language;

    public NonConcertWithMusic(String type, String language) {
        super(type);
        
        performers = new ArrayList<Performer>();
        this.language = language;
    }

    public void addPerformer(Performer performer) {
        performers.add(performer);
    }

    public ArrayList<Performer> getPerformers() {
        return performers;
    }

    public String getLanguage() {
        return language;
    }
    
}
