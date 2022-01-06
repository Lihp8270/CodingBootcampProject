package model;

public class Price {
    private int concessionID;
    private String concessionName;
    private Double priceAsDouble;
    private String priceAsString;

    public Price(int concessionID, String concessionName, Double priceAsDouble, String priceAsString) {
        this.concessionID = concessionID;
        this.concessionName = concessionName;
        this.priceAsDouble = priceAsDouble;
        this.priceAsString = priceAsString;
    }

    /**
     * Accessor for concessionID
     * @return Returns concessionID as an Integer
     */
    public int getConcessionID() {
        return concessionID;
    }

    /**
     * Accessor for concession name
     * @return Returns concession name as a String
     */
    public String getConcessionName() {
        return concessionName;
    }

    /**
     * Accessor for price as a double
     * @return Returns price as a double
     */
    public Double getPriceAsDouble() {
        return priceAsDouble;
    }

    /**
     * Accessor for price as a string
     * @return Returns price as a formatted String
     */
    public String getPriceAsString() {
        return priceAsString;
    }    
}
