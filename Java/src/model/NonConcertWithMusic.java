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
     * Accessor for music performers
     * @return Returns an Array List of performer objects
     */
    public ArrayList<Performer> getMusicPerformers() {
        ArrayList<Performer> musicList = new ArrayList<Performer>();
        for (int i = 0; i < performers.size(); i++) {
            if (performers.get(i).isMusicPerformer()) {
                musicList.add(performers.get(i));
            }
        }

        return musicList;
    }

    /**
     * Accessor for production performers
     * @return Returns an Array List of performer objects
     */
    public ArrayList<Performer> getProductionPerformers() {
        ArrayList<Performer> productionList = new ArrayList<Performer>();
        for (int i = 0; i < performers.size(); i++) {
            if (performers.get(i).isProductionPerformer()) {
                productionList.add(performers.get(i));
            }
        }
        
        return productionList;
    }

    /**
     * Accessor for all show performers
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
