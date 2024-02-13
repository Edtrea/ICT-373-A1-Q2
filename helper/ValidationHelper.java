/**
 * @Title: ICT 373 A1
 * @Author: Lim Wen Chao
 * @Date: 2/2/2024
 * @File: ValidationHelper.java
 * @Purpose: A class that contains helper methods for validation
 * Contains methods for the following validations:
 * 1. Email validation
 * 2. Name validation
 * 4. Payment method validation (card / bank account only)
 * 5. Payment details validation (card number or bank account number only)
 * @Assumptions:
 * @Limitations:
 */

package helper;

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
