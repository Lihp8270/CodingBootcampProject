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

    public ArrayList<Performer> getMusicPerformers() {
        ArrayList<Performer> musicList = new ArrayList<Performer>();
        for (int i = 0; i < performers.size(); i++) {
            if (performers.get(i).isMusicPerformer()) {
                musicList.add(performers.get(i));
            }
        }
        
        return musicList;
    }

    public ArrayList<Performer> getProductionPerformers() {
        ArrayList<Performer> productionList = new ArrayList<Performer>();
        for (int i = 0; i < performers.size(); i++) {
            if (performers.get(i).isProductionPerformer()) {
                productionList.add(performers.get(i));
            }
        }
        
        return productionList;
    }
}
