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
    private String name;
    private double weeklyCost;

    public Supplement(String name, double weeklyCost) {
        this.name = name;
        this.weeklyCost = weeklyCost;
    }

    public String getName() {
        return name;
    }

    public double getWeeklyCost() {
        return weeklyCost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeeklyCost(double weeklyCost) {
        this.weeklyCost = weeklyCost;
    }
}
