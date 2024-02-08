/**
 * @Project: Magazine Service
 * @File: Supplements.java
 * @Author: Lim Wen Chao
 * @Date: 2024/2/2
 * @Version: 1.0
 * @Revision: none
 * @Usage: A class that represents a supplement
 * @Description: A supplement has a name and a weekly cost
 */
package src.Magazine;

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
