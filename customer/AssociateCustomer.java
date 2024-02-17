/**
 * @Title: ICT373 Assignment 1
 * @Author: Lim Wen Chao
 * @Date: 2/2/2024
 * @File: AssociateCustomer.java
 * @Purpose: A child class of Customer that represents an associate customer
 * An Associate Customer is a customer whose subscription is paid for by a specified Paying Customer.
 * @Assumptions:
 * @Limitations:
 */

package customer;

import java.util.ArrayList;
import magazine.*;

public class AssociateCustomer extends Customer {
    // The paying customer for the associate customer
    private PayingCustomer payingCustomer;

    /**
     * Constructor for the AssociateCustomer class
     * 
     * @param name           The name of the associate customer
     * @param email          The email of the associate customer
     * @param supplements    The list of supplements the associate customer is
     *                       interested in
     * @param payingCustomer The paying customer for the associate customer
     */
    public AssociateCustomer(String name, String email, ArrayList<Supplement> supplements, Customer payingCustomer) {
        super(name, email, supplements);
        this.payingCustomer = (PayingCustomer) payingCustomer;
    }

    /**
     * Constructor for the AssociateCustomer class without supplements
     * 
     * @param name           The name of the associate customer
     * @param email          The email of the associate customer
     * @param payingCustomer The paying customer for the associate customer
     */
    public AssociateCustomer(String name, String email, Customer payingCustomer) {
        super(name, email);
        this.payingCustomer = (PayingCustomer) payingCustomer;
    }

    /**
     * Getter for the paying customer
     * 
     * @return The paying customer for the associate customer
     */
    public Customer getPayingCustomer() {
        return payingCustomer;
    }

    /**
     * Setter for the paying customer
     * 
     * @param payingCustomer The paying customer for the associate customer
     */
    public void setPayingCustomer(Customer payingCustomer) {
        this.payingCustomer = (PayingCustomer) payingCustomer;
    }

    /*
     * Calculate the weekly payment due for the associate customer
     * 
     * @return The weekly payment due for the associate customer
     */
    public double calculateWeeklyPayment(Magazine magazine) {
        double weeklyPayment = magazine.getWeeklyCost();
        for (Supplement supplement : getSupplements()) {
            weeklyPayment += supplement.getWeeklyCost();
        }
        return weeklyPayment;
    }

    /*
     * Calculate the monthly payment due for the associate customer
     * 
     * @return The monthly payment due for the associate customer
     */
    public double calculateMonthlyPayment(Magazine magazine) {
        double monthlyPayment = magazine.getWeeklyCost() * 4;
        for (Supplement supplement : getSupplements()) {
            monthlyPayment += supplement.getWeeklyCost() * 4;
        }
        return monthlyPayment;
    }

    /*
     * Returns the weekly email content for the associate customer
     * 
     * @return The weekly email content for the associate customer
     */
    public String getWeeklyEmail() {
        StringBuilder email = new StringBuilder();
        email.append("To: " + getEmail() + "\n");
        email.append("Dear " + getName() + ",\n");
        email.append("Your magazine is ready to look at. You are currently subscribed to the following supplements:\n");
        for (Supplement supplement : getSupplements()) {
            email.append(supplement.getName() + "\n");
        }
        return email.toString();
    }

    /*
     * Returns the monthly email content for the associate customer
     * 
     * @return The monthly email content for the associate customer
     */
    public String getMonthlyEmail(Magazine magazine) {
        return "";
    }

}
