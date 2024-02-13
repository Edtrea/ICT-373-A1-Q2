/**
 * @Title: ICT 373 A1
 * @Author: Lim Wen Chao
 * @Date: 2/2/2024
 * @File: Magazine.java
 * @Purpose: A class that represents a magazine object.
 * A magazine has a weekly cost
 * @Assumptions:
 * @Limitations:
 */
package magazine;

public class Magazine {
    /**
     * The weekly cost of the magazine
     */
    private double weeklyCost;

    /**
     * Constructor for the Magazine class
     * 
     * @param weeklyCost the weekly cost of the magazine
     */
    public Magazine(double weeklyCost) {
        this.weeklyCost = weeklyCost;
    }

    /**
     * Getter for the weekly cost of the magazine
     * 
     * @return the weekly cost of the magazine
     */
    public double getWeeklyCost() {
        return weeklyCost;
    }

    /**
     * Setter for the weekly cost of the magazine
     * 
     * @param weeklyCost the weekly cost of the magazine
     */
    public void setWeeklyCost(double weeklyCost) {
        this.weeklyCost = weeklyCost;
    }
}
