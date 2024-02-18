/**
 * @Title: ICT 373 A1
 * @Author: Lim Wen Chao
 * @Date: 2/2/2024
 * @File: Supplements.java
 * @Purpose: A class that represents a supplement
 * A supplement has a name and a weekly cost
 * @Assumptions:
 * @Limitations:
 */
package magazine;

public class Supplement {
    /**
     * A supplement has a name and a weekly cost
     */
    private String name;
    private double weeklyCost;

    /**
     * Constructor
     * 
     * @param name
     * @param weeklyCost
     */
    public Supplement(String name, double weeklyCost) {
        this.name = name;
        this.weeklyCost = weeklyCost;
    }

    /**
     * Getter for name
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for weekly cost
     * 
     * @return
     */
    public double getWeeklyCost() {
        return weeklyCost;
    }

    /**
     * Setter for name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for weekly cost
     * 
     * @param weeklyCost
     */
    public void setWeeklyCost(double weeklyCost) {
        this.weeklyCost = weeklyCost;
    }
}
