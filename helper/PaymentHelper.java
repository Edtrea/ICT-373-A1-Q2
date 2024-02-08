/**
 * @File PaymentHelper.java
 * @Class:  PaymentHelper
 * @Author:  Lim Wen Chao
 * @Date:  2024/2/2
 * @Version:  1.0
 * @Revision:  none
 * @Usage:  This class is used to calculate the monthly amount of payment due for a customer based on their subscription and the cost of thier associate customer's subscription
 * @Description:  The weekly cost of the base magazine subscription is can be obtained from the magazine object, the weekly cost of the supplements can be obtained from the supplements object
 * The class contains the following methods:
 * 1. calculateWeeklyAssociatePayment: This method is used to calculate the weekly payment due for an associate customer
 * 2. calculateWeeklyPayingPayment: This method is used to calculate the weekly payment due for a paying customer
 * 3. calculateWeeklyPayment: This method is used to calculate the weekly payment due for a customer
 * 4. calculateMonthlyPayment: This method is used to calculate the monthly payment due for a customer
 */
package src.helper;

import src.Magazine.Magazine;
import src.customer.Customer;
import src.Magazine.Supplement;
import src.customer.PayingCustomer;

public class PaymentHelper {
    /**
     * Returns the weekly payment due for an associate customer
     * 
     * @param customer The associate customer to calculate the weekly payment for
     * @param magazine The magazine the customer is subscribed to
     * @return The weekly payment due for the associate customer
     */
    public static double calculateWeeklyAssociatePayment(Customer customer, Magazine magazine) {
        double weeklyPayment = magazine.getWeeklyCost();
        for (Supplement supplement : customer.getSupplements()) {
            weeklyPayment += supplement.getWeeklyCost();
        }
        return weeklyPayment;
    }

    /**
     * Returns the weekly payment due for a paying customer
     * 
     * @param customer The paying customer to calculate the weekly payment for
     * @param magazine The magazine the customer is subscribed to
     * @return The weekly payment due for the paying customer
     */
    public static double calculateWeeklyPayingPayment(Customer customer, Magazine magazine) {
        double weeklyPayment = magazine.getWeeklyCost();
        for (Supplement supplement : customer.getSupplements()) {
            weeklyPayment += supplement.getWeeklyCost();
        }
        for (Customer associateCustomer : ((PayingCustomer) customer).getAssociateCustomers()) {
            weeklyPayment += calculateWeeklyAssociatePayment(associateCustomer, magazine);
        }
        return weeklyPayment;
    }

    /**
     * Returns the weekly payment due for a customer
     * 
     * @param customer The customer to calculate the weekly payment for
     * @param magazine The magazine the customer is subscribed to
     * @return The weekly payment due for the customer
     */
    public static double calculateWeeklyPayment(Customer customer, Magazine magazine) {
        if (customer instanceof PayingCustomer) {
            return calculateWeeklyPayingPayment(customer, magazine);
        } else {
            return calculateWeeklyAssociatePayment(customer, magazine);
        }
    }

    /**
     * Returns the monthly payment due for a customer
     * 
     * @param customer The customer to calculate the monthly payment for
     * @param magazine The magazine the customer is subscribed to
     * @return The monthly payment due for the customer
     */
    public static double calculateMonthlyPayment(Customer customer, Magazine magazine) {
        return calculateWeeklyPayment(customer, magazine) * 4;
    }
}
