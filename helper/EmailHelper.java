/**
 * @file EmailHelper.java
 * @Class:  EmailHelper
 * @Author:  Lim Wen Chao
 * @Date:  2024/2/2
 * @Version:  1.0
 * @Revision:  none
 * @Usage:  returns string for weekly and monthly emails to customer regarding their magazine subscription and payment
 * @Description:  This class is used to generate the email content for the customer
 * The email content is generated based on the customer's subscription and payment status
 * The payment amount dued is checked using the PaymentHelper class
 * The email content is generated for weekly and monthly emails
 * The weekly email print out the text of all the emails for all customers for four weeks of magazines
 * Each week, each customer gets an email telling them their magazine is ready to look at and listing the supplements that they currently subscribe to.
 * Each month, each paying customer gets an email telling them how much is being charged to their 
card or account for the month and itemizing the cost by supplements for them and any of their 
associate customers
 */

package src.helper;

import java.util.ArrayList;

import src.Magazine.Magazine;
import src.customer.AssociateCustomer;
import src.customer.Customer;
import src.Magazine.Supplement;
import src.customer.PayingCustomer;

public class EmailHelper {
    /**
     * Returns the email content for the weekly email
     * 
     * @param customer The customer to generate the email for
     * @param magazine The magazine the customer is subscribed to
     * @return The email content for the weekly email
     */
    public static String getWeeklyEmail(Customer customer, Magazine magazine) {
        String email = "Dear " + customer.getName() + ",\n";
        email += "Your magazine is ready to look at. You are currently subscribed to the following supplements:\n";
        for (Supplement supplement : customer.getSupplements()) {
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

    public static String getMonthlyEmail(Customer customer, Magazine magazine) {
        String email = "Dear " + customer.getName() + ",\n";
        // Total cost for the customer
        email += "The total amount due for the month is: " + PaymentHelper.calculateMonthlyPayment(customer, magazine);
        // Itemized cost for the customer
        email += "The itemized cost for the month is:\n";
        for (Supplement supplement : customer.getSupplements()) {
            // Calculate monthly cost for the supplement from the weekly cost
            email += supplement.getName() + ": " + supplement.getWeeklyCost() * 4 + "\n";
        }
        // Get the list of associate customers from the paying customer's attribute
        ArrayList<AssociateCustomer> associateCustomers = ((PayingCustomer) customer).getAssociateCustomers();
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
