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

    /**
     * Accessor for performer ID
     * @return returns ID of the performer as an integer
     */
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

    /**
     * Checks whether given performer is a music performer in any capacity
     * @return true or false
     */
    public Boolean isMusicPerformer() {
        return isMusicPerformer;
    }

    /**
     * Checks whether given performer is a production performer in any capacity
     * @return true or false
     */
    public Boolean isProductionPerformer() {
        return isProductionPerformer;
    }

    /**
     * Set the music performer flag
     * @param set true or false
     */
    public void performerIsMusic(Boolean set) {
        isMusicPerformer = set;
    }

    /**
     * Set the production performer flag
     * @param set true or false
     */
    public void performerIsProduction(Boolean set) {
        isProductionPerformer = set;
    }

    /**
     * Adds a production role to the performer
     * @param role Role as a string to add
     */
    public void addProductionRole(String role) {
        productionRoles.add(role);
    }
    
    /**
     * Adds a music role to the performer
     * @param role Role as a string to add
     */
    public void addMusicRole(String role) {
        musicRoles.add(role);
    }

    /**
     * Returns number of production roles the performer has
     * @return size of array list as an integer
     */
    public int numOfProductionRoles() {
        return productionRoles.size();
    }

    /**
     * Returns number of music roles the performer has
     * @return size of array list as an integer
     */
    public int numOfMusicRoles() {
        return musicRoles.size();
    }
}
