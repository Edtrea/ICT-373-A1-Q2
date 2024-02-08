/**
 * This class is used to create an Associate Customer object.
 * An Associate Customer is a customer whose subscription is paid for by a specified Paying Customer.
 */

package src.customer;

import java.util.ArrayList;
import src.Magazine.Supplement;

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
}
