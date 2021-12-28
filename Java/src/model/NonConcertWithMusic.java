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

    /**
     * Accessor for show performers
     * @return returns an ArrayList of performers
     */
    public ArrayList<Performer> getPerformers() {
        return performers;
    }

    /**
     * Accessor for the language
     * @return returns the language of the show as a String
     */
    public String getLanguage() {
        return language;
    }
    
}
