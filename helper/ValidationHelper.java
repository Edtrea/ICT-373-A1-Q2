/**
 * @File: ValidationHelper.java
 * @Author: Lim Wen Chao
 * @Date: 2024/2/2
 * @Version: 1.0
 * @Revision: none
 * @Usage: A class that contains helper methods for validation
 * @Description: Contains methods for the following validations:
 * 1. Email validation
 * 2. Name validation
 * 4. Payment method validation (card / bank account only)
 * 5. Payment details validation (card number or bank account number only)
 */

package src.helper;

public class ValidationHelper {
    /**
     * Method to validate an email
     * 
     * @param email The email to be validated
     * @return true if the email is valid, false otherwise
     */
    public static boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }

    /**
     * Method to validate a name
     * 
     * @param name The name to be validated
     * @return true if the name is valid, false otherwise
     */
    public static boolean validateName(String name) {
        return name.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    }

    /**
     * Method to validate the payment method
     * 
     * @param paymentMethod The payment method to be validated
     * @return true if the payment method is valid, false otherwise
     */
    public static boolean validatePaymentMethod(String paymentMethod) {
        return paymentMethod.equals("card") || paymentMethod.equals("bank");
    }

    /**
     * Method to validate the payment details
     * 
     * @param paymentDetails The payment details to be validated
     * @return true if the payment details are valid, false otherwise
     */
    public static boolean validatePaymentDetails(String paymentDetails) {
        // Payment details should be a number only
        return paymentDetails.matches("^[0-9]*$");
    }
}
