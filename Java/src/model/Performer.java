package model;

import java.util.ArrayList;

public class Performer {
    private int performerID;
    private String name;
    private Boolean isMusicPerformer;
    private Boolean isProductionPerformer;
    private ArrayList<String> musicRoles;
    private ArrayList<String> productionRoles;
    
    public Performer(String name, int performerID) {
        productionRoles = new ArrayList<String>();
        musicRoles = new ArrayList<String>();
        
        this.performerID = performerID;
        this.name = name;
    }

    /**
     * Accessor for the performer name
     * @return returns the performer name as a String
     */
    public String getName() {
        return name;
    }

    public int getPerformerID() {
        return performerID;
    }

    /**
     * Acccessor for music Roles
     * @return ArrayList of strings
     */
    public ArrayList<String> getMusicRoles() {
        return musicRoles;
    }

    /**
     * Accessor for show roles
     * @return Arraylist of strings
     */
    public ArrayList<String> getProductionRoles() {
        return productionRoles;
    }

    public Boolean isMusicPerformer() {
        return isMusicPerformer;
    }

    public Boolean isProductionPerformer() {
        return isProductionPerformer;
    }

    public void performerIsMusic(Boolean set) {
        isMusicPerformer = set;
    }

    public void performerIsProduction(Boolean set) {
        isProductionPerformer = set;
    }

    public void addProductionRole(String role) {
        productionRoles.add(role);
    }
    
    public void addMusicRole(String role) {
        musicRoles.add(role);
    }

    public int numOfProductionRoles() {
        return productionRoles.size();
    }

    public int numOfMusicRoles() {
        return musicRoles.size();
    }
}
