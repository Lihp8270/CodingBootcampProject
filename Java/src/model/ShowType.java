package model;

import java.util.ArrayList;

public abstract class ShowType {
    private String type;

    public ShowType(String type) {
        this.type = type;
    }

    // TODO
    // PRIORITY 1
    // getProductionPerformers();
    // getMusicPerformers();

    /**
     * Accessor for the show type
     * @return Returns the show type as a String
     */
    public String getType() {
        return this.type;
    }

    public abstract String getLanguage();
    public abstract ArrayList<Performer> getPerformers();
    public abstract ArrayList<Performer> getMusicPerformers();
    public abstract ArrayList<Performer> getProductionPerformers();

    @Override
    public String toString() {
        return type;
    }    
}
