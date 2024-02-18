/**
 * @Title: ICT 373 A1
 * @Author: Lim Wen Chao
 * @Date: 2/2/2024
 * @File: Customer.java
 * @Purpose: An abstract class that represents a customer
 * A customer can be a paying customer or an associate customer
 * A customer contains a name, email address, and a list of supplements they are interested in
 * @Assumptions:
 * @Limitations:
 */

package customer;

import java.util.ArrayList;

import magazine.Magazine;
import magazine.Supplement;

public abstract class Customer {
    /**
     * A customer contains a name, email address, and a list of supplements they are
     * interested in
     */
    private String name;
    private String email;
    private ArrayList<Supplement> supplements;

    /**
     * Constructor for the Customer class
     * 
     * @param name        The name of the customer
     * @param email       The email of the customer
     * @param supplements The list of supplements the customer is interested in
     */
    public Customer(String name, String email, ArrayList<Supplement> supplements) {
        this.name = name;
        this.email = email;
        this.supplements = supplements;
    }

    /**
     * Constructor for the Customer class without supplements
     * 
     * @param name
     * @param email
     */
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.supplements = new ArrayList<Supplement>();
    }

    /**
     * Getter for the name of the customer
     * 
     * @return The name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the email of the customer
     * 
     * @return The email of the customer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for the list of supplements the customer is interested in
     * 
     * @return The list of supplements the customer is interested in
     */
    public ArrayList<Supplement> getSupplements() {
        return supplements;
    }

    /**
     * Setter for the name of the customer
     * 
     * @param name The name of the customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the email of the customer
     * 
     * @param email The email of the customer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter for the list of supplements the customer is interested in
     * 
     * @param supplements The list of supplements the customer is interested in
     */
    public void setSupplements(ArrayList<Supplement> supplements) {
        this.supplements = supplements;
    }

    public abstract String getWeeklyEmail();

    public abstract String getMonthlyEmail(Magazine magazine);
}
