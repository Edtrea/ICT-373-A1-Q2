/**
 * PayingCustomer.java
 * A child class of Customer that represents a paying customer
 * A paying customer has a payment method, payment detail and a list of associate customers
 * A payment method/detail could only be a specified credit card or a bank account
 */
package src.customer;

import java.util.ArrayList;
import src.Magazine.Supplement;

public class PayingCustomer extends Customer {
    private String paymentMethod;
    private String paymentDetail;
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
            String paymentDetail, ArrayList<AssociateCustomer> associateCustomers) {
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
            String paymentDetail, ArrayList<AssociateCustomer> associateCustomers) {
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
            String paymentDetail) {
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
            String paymentDetail) {
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
    public String getPaymentDetail() {
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
    public void setPaymentDetail(String paymentDetail) {
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
}
