package model;

import java.util.ArrayList;

public abstract class ShowType {
    private String type;

    public ShowType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public abstract String getLanguage();
    public abstract ArrayList<Performer> getPerformers();

    @Override
    public String toString() {
        return type;
    }    
}
