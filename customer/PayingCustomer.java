/**
 * @Title: ICT 373 A1
 * @Author: Lim Wen Chao
 * @Date: 9/2/2024
 * @File: PayingCustomer.java
 * @Purpose: A child class of Customer that represents a paying customer
 * A paying customer has a payment method, payment detail and a list of associate customers
 * A payment method/detail could only be a specified credit card or a bank account
 * @Assumptions: 
 * @Limitations: 
 */
package customer;

import java.util.ArrayList;

import magazine.Magazine;
import magazine.Supplement;

public class PayingCustomer extends Customer {
    private String paymentMethod;
    private Integer paymentDetail;
    private ArrayList<AssociateCustomer> associateCustomers;

    /**
     * Constructor for the PayingCustomer class
     * 
     * @param name               The name of the paying customer
     * @param email              The email of the paying customer
     * @param supplements        The list of supplements the paying customer is
     *                           interested in
     * @param paymentMethod      The payment method of the paying customer
     * @param paymentDetail      The payment detail of the paying customer
     * @param associateCustomers The list of associate customers for the paying
     *                           customer
     */
    public PayingCustomer(String name, String email,
            ArrayList<Supplement> supplements, String paymentMethod,
            Integer paymentDetail, ArrayList<AssociateCustomer> associateCustomers) {
        super(name, email, supplements);
        this.paymentMethod = paymentMethod;
        this.paymentDetail = paymentDetail;
        this.associateCustomers = associateCustomers;
    }

    /**
     * Constructor for the PayingCustomer class without supplements
     * 
     * @param name
     * @param email
     * @param paymentMethod
     * @param paymentDetail
     * @param associateCustomers
     */
    public PayingCustomer(String name, String email, String paymentMethod,
            Integer paymentDetail, ArrayList<AssociateCustomer> associateCustomers) {
        super(name, email);
        this.paymentMethod = paymentMethod;
        this.paymentDetail = paymentDetail;
        this.associateCustomers = associateCustomers;
    }

    /**
     * Constructor for the PayingCustomer class without associate customers
     * 
     * @param name
     * @param email
     * @param supplements
     * @param paymentMethod
     * @param paymentDetail
     */
    public PayingCustomer(String name, String email,
            ArrayList<Supplement> supplements, String paymentMethod,
            Integer paymentDetail) {
        super(name, email, supplements);
        this.paymentMethod = paymentMethod;
        this.paymentDetail = paymentDetail;
        this.associateCustomers = new ArrayList<AssociateCustomer>();
    }

    /**
     * Constructor for the PayingCustomer class without supplements and associate
     * customers
     * 
     * @param name
     * @param email
     * @param paymentMethod
     * @param paymentDetail
     */
    public PayingCustomer(String name, String email, String paymentMethod,
            Integer paymentDetail) {
        super(name, email);
        this.paymentMethod = paymentMethod;
        this.paymentDetail = paymentDetail;
        this.associateCustomers = new ArrayList<AssociateCustomer>();
    }

    /**
     * Getter for the payment method of the paying customer
     * 
     * @return The payment method of the paying customer
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Getter for the payment detail of the paying customer
     * 
     * @return The payment detail of the paying customer
     */
    public Integer getPaymentDetail() {
        return paymentDetail;
    }

    /**
     * Getter for the list of associate customers for the paying customer
     * 
     * @return The list of associate customers for the paying customer
     */
    public ArrayList<AssociateCustomer> getAssociateCustomers() {
        return associateCustomers;
    }

    /**
     * Setter for the payment method of the paying customer
     * 
     * @param paymentMethod The payment method of the paying customer
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Setter for the payment detail of the paying customer
     * 
     * @param paymentDetail The payment detail of the paying customer
     */
    public void setPaymentDetail(Integer paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    /**
     * Setter for the list of associate customers for the paying customer
     * 
     * @param associateCustomers The list of associate customers for the paying
     *                           customer
     */
    public void setAssociateCustomers(ArrayList<AssociateCustomer> associateCustomers) {
        this.associateCustomers = associateCustomers;
    }

    /**
     * Add an associate customer to the list of associate customers for the paying
     * customer
     * 
     * @param associateCustomer The associate customer to be added
     */
    public void addAssociateCustomer(AssociateCustomer associateCustomer) {
        associateCustomers.add(associateCustomer);
    }

    /**
     * Remove an associate customer from the list of associate customers for the
     * paying customer
     * 
     * @param associateCustomer The associate customer to be removed
     */
    public void removeAssociateCustomer(AssociateCustomer associateCustomer) {
        associateCustomers.remove(associateCustomer);
    }

    /**
     * Returns the monthly payment due for the paying customer
     * 
     * @param magazine The magazine the customer is subscribed to
     * @return The monthly payment due for the paying customer
     */
    public double calculateMonthlyPayment(Magazine magazine) {
        double monthlyPayment = magazine.getWeeklyCost() * 4;
        for (Supplement supplement : getSupplements()) {
            monthlyPayment += supplement.getWeeklyCost() * 4;
        }
        for (AssociateCustomer associateCustomer : associateCustomers) {
            monthlyPayment += associateCustomer.calculateMonthlyPayment(magazine);
        }
        return monthlyPayment;
    }

    /**
     * Returns the weekly payment due for the paying customer
     * 
     * @param magazine The magazine the customer is subscribed to
     * @return The weekly payment due for the paying customer
     */
    public double calculateWeeklyPayment(Magazine magazine) {
        double weeklyPayment = magazine.getWeeklyCost();
        for (Supplement supplement : getSupplements()) {
            weeklyPayment += supplement.getWeeklyCost();
        }
        for (AssociateCustomer associateCustomer : associateCustomers) {
            weeklyPayment += associateCustomer.calculateWeeklyPayment(magazine);
        }
        return weeklyPayment;
    }

    /**
     * Returns the weekly email for the customer
     * 
     * @return The weekly email for the customer
     */
    public String getWeeklyEmail() {
        String email = "To: " + this.getEmail() + "\n";
        email += "Dear " + this.getName() + ",\n";
        email += "Your magazine is ready to look at. You are currently subscribed to the following supplements:\n";
        for (Supplement supplement : this.getSupplements()) {
            email += supplement.getName() + "\n";
        }
        return email;
    }

    /**
     * Returns the email content for the monthly email
     * 
     * @param customer The customer to generate the email for
     * @param magazine The magazine the customer is subscribed to
     * @return The email content for the monthly email
     */

    public String getMonthlyEmail(Magazine magazine) {
        String email = "To: " + this.getEmail() + "\n";
        email += "Dear " + this.getName() + ",\n";
        // Total cost for the customer
        email += "The total amount due for the month is: " + this.calculateMonthlyPayment(magazine);
        // Itemized cost for the customer
        email += "The itemized cost for the month is:\n";
        for (Supplement supplement : this.getSupplements()) {
            // Calculate monthly cost for the supplement from the weekly cost
            email += supplement.getName() + ": " + supplement.getWeeklyCost() * 4 + "\n";
        }
        // Get the list of associate customers from the paying customer's attribute
        ArrayList<AssociateCustomer> associateCustomers = ((PayingCustomer) this).getAssociateCustomers();
        for (AssociateCustomer associateCustomer : associateCustomers) {
            // Calculate monthly cost for the supplement from the weekly cost
            email += "Associate customer " + associateCustomer.getName() + ":\n";
            for (Supplement supplement : associateCustomer.getSupplements()) {
                email += supplement.getName() + ": " + supplement.getWeeklyCost() * 4 + "\n";
            }
        }
        return email;
    }
}
