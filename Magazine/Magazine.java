/**
 * @Project: Magazine Service
 * @File: Magazine.java
 * @Author: Lim Wen Chao
 * @Date: 2024/2/2
 * @Version: 1.0
 * @Revision: none
 * @Usage: Magazine class is a class that represents a magazine object.
 * @Description: A magazine has a weekly cost
 */
package src.Magazine;

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
