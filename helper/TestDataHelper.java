/**
 * @Title: ICT 373 A1
 * @Author: Lim Wen Chao
 * @Date: 2/2/2024
 * @File: TestDataHelper.java
 * @Purpose: A helper class that provides test data for the application either from user inputs or pre-defined data
 * Contains the following methods:
 * 1. createTestMagazine() - creates a test magazine object from user input
 * 2. createPreBuiltMagazine() - creates a magazine object with pre-defined data
 * 3. createTestSupplements() - creates an arrayList of test supplement objects from user input
 * 4. createPreBuiltSupplements() - creates an arrayList of supplement objects with pre-defined data
 * 5. createTestCustomer() - creates an single test customer object from user input
 * 6. createTestPayingCustomer() - creates a single test paying customer object from user input
 * 7. createTestAssociateCustomer() - creates a single test associate customer object from user input
 * 8. createTestCustomers() - creates an arrayList of test customer objects from user input
 * 9. createPreBuiltCustomers() - creates an arrayList of customer objects with pre-defined data
 * 10. removeCustomer() - removes a customer from the customers list
 * 
 * Inputs should be validated with the ValidationHelper class before being passed to the methods in this class
 * @Assumptions: 
 * createPreBuiltCustomers() assumes that the supplementsList contains at least 5 supplements
 * @Limitations:
 */

package helper;

import java.util.ArrayList;
import java.util.Scanner;

import magazine.*;
import customer.*;

public class TestDataHelper {
    /**
     * Method to create a test magazine object from user input
     * 
     * @return A test magazine object
     */
    public static Magazine createTestMagazine(Scanner scanner) {
        while (true) {
            System.out.println("Enter the weekly cost of the magazine: ");
            String input = scanner.nextLine().trim();
            // Check if input is empty
            if (input.isEmpty()) {
                System.out.println("Blank or empty inputs are not allowed. Please enter a double value.");
                continue;
            }
            // Try to parse the input as a double
            try {
                double weeklyCost = Double.parseDouble(input);
                return new Magazine(weeklyCost);
            } catch (NumberFormatException e) {
                System.out.println("A double value expected, but got: " + input);
            }
        }
    }

    /**
     * Method to create a magazine object with pre-defined data
     * 
     * @return A magazine object with pre-defined data
     */
    public static Magazine createPreBuiltMagazine() {
        return new Magazine(5);
    }

    /**
     * Method to create an arrayList of test supplement objects from user input
     * 
     * @return An arrayList of test supplement objects
     */
    public static ArrayList<Supplement> createTestSupplements(Scanner scanner) {
        ArrayList<Supplement> supplements = new ArrayList<Supplement>();
        while (true) {
            String name;
            double weeklyCost;

            while (true) {
                System.out.println("Enter the name of the supplement: ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("Blank or empty inputs are not allowed. Please enter a name.");
                } else {
                    break;
                }
            }

            while (true) {
                System.out.println("Enter the weekly cost of the supplement: ");
                String input = scanner.nextLine().trim();
                // Check if input is empty
                if (input.isEmpty()) {
                    System.out.println("Blank or empty inputs are not allowed. Please enter a double value.");
                    continue;
                }
                // Try to parse the input as a double
                try {
                    weeklyCost = Double.parseDouble(input);
                    supplements.add(new Supplement(name, weeklyCost));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("A double value expected, but got: " + input);
                }
            }
            System.out.println("Do you want to add another supplement? (y/n)");
            String response = scanner.nextLine().toLowerCase().trim();
            while (true) {
                if (response.isEmpty()) {
                    System.out.println("Blank or empty inputs are not allowed. Please enter 'y' or 'n'.");
                } else if (response.equals("n")) {
                    return supplements;
                } else if (response.equals("y")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    continue;
                }
            }
        }
    }

    /**
     * Method to create an arrayList of supplement objects with pre-defined data
     * 
     * @return An arrayList of supplement objects with pre-defined data
     */
    public static ArrayList<Supplement> createPreBuiltSupplements() {
        ArrayList<Supplement> supplements = new ArrayList<Supplement>();
        supplements.add(new Supplement("Supplement 1", 5));
        supplements.add(new Supplement("Supplement 2", 10));
        supplements.add(new Supplement("Supplement 3", 15));
        supplements.add(new Supplement("Supplement 4", 20));
        supplements.add(new Supplement("Supplement 5", 25));

        return supplements;
    }

    /**
     * Method to create a test customer object from user input
     * Calls createTestPayingCustomer() and createTestAssociateCustomer() to create
     * a test paying customer or associate customer object
     * 
     * @return A test customer object
     */
    public static Customer createTestCustomer(final ArrayList<Customer> customers,
            ArrayList<Supplement> supplementsList, Scanner scanner) {
        String name;
        String email;
        ArrayList<Supplement> userSupplements = new ArrayList<>();
        String response;

        while (true) {
            System.out.println("Enter the name of the customer: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Blank or empty inputs are not allowed. Please enter a name.");
            } else if (ValidationHelper.validateName(name)) {
                break;
            } else {
                System.out.println("Name is invalid :"
                        + name);
            }
        }

        while (true) {
            System.out.println("Enter the email of the customer: ");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Blank or empty inputs are not allowed. Please enter a email.");
            } else if (ValidationHelper.validateEmail(email)) {
                break;
            } else {
                System.out.println("Email is invalid :"
                        + email);
            }
        }

        while (true) {
            System.out.println("Does the customer subscribe to any supplements? (y/n)");
            response = scanner.nextLine().toLowerCase().trim();
            if (response.isEmpty()) {
                System.out.println("Blank or empty inputs are not allowed. Please enter 'y' or 'n'.");
                continue;
            } else if (response.equals("n")) {
                break;
            } else if (response.equals("y")) {
                for (int i = 0; i < supplementsList.size(); i++) {
                    System.out.println(
                            i + 1 + ". " + supplementsList.get(i).getName() + " - "
                                    + supplementsList.get(i).getWeeklyCost());
                }
                while (true) {
                    System.out.println("Enter the number of the supplement that is beside the name: ");
                    String input = scanner.nextLine().trim();
                    // Check if input is empty
                    if (input.isEmpty()) {
                        System.out.println("Blank or empty inputs are not allowed. Please enter a valid number.");
                        continue;
                    }
                    // Try to parse the input as an integer
                    try {
                        int supplementNum = Integer.parseInt(input);
                        if (supplementNum > 0 && supplementNum <= supplementsList.size()) {
                            Supplement selectedSupplement = supplementsList.get(supplementNum - 1);
                            if (!userSupplements.contains(selectedSupplement)) {
                                userSupplements.add(selectedSupplement);
                            } else {
                                System.out.println("This supplement is already added.");
                            }
                        } else {
                            System.out.println("Not a valid number : " + supplementNum);
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("A valid number expected, but got: " + input);
                        continue;
                    }

                    while (true) {
                        System.out.println("Do you want to add another supplement? (y/n)");
                        response = scanner.nextLine().toLowerCase().trim();
                        if (response.isEmpty()) {
                            System.out.println("Blank or empty inputs are not allowed. Please enter 'y' or 'n'.");
                        } else if (response.equals("n") || response.equals("y")) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter 'y' or 'n'.");
                            continue;
                        }
                    }
                    if (response.equals("n")) {
                        break;
                    }
                }
                if (response.equals("n")) {
                    break;
                }
            }
        }

        while (true) {
            System.out.println("Is the customer a paying customer or an associate customer? (p/a)");
            response = scanner.nextLine().toLowerCase().trim();
            if (response.equals("p")) {
                return createTestPayingCustomer(customers, name, email, userSupplements, scanner);
            } else if (response.equals("a")) {
                return createTestAssociateCustomer(customers, name, email, userSupplements, scanner);
            } else {
                System.out.println("Not a valid response :"
                        + response);
            }
        }
    }

    /**
     * Method to create a test paying customer object from user input
     * Name, email, and supplements are passed from createTestCustomer()
     * This method will only need to ask for the payment method, payment detail, and
     * list of associate customers
     * Expected to return a PayingCustomer object to createTestCustomer()
     * 
     * @return A test paying customer object
     */
    public static PayingCustomer createTestPayingCustomer(final ArrayList<Customer> customers, String name,
            String email,
            ArrayList<Supplement> userSupplements, Scanner scanner) {
        // If the customer is a paying customer, ask for the payment method and payment
        // detail and associate customers
        String paymentMethod;
        String paymentDetail;
        // Ask user for the payment method
        while (true) {
            paymentMethod = ""; // Initialize paymentMethod variable
            System.out.println("Enter the payment method of the customer (card / bank): ");
            paymentMethod = scanner.nextLine().trim().toLowerCase();
            // Check if input is empty
            if (paymentMethod.isEmpty()) {
                System.out.println("Blank or empty inputs are not allowed. Please enter a valid payment method.");
                continue;
            }
            // Validate payment method
            if (ValidationHelper.validatePaymentMethod(paymentMethod)) {
                break;
            } else {
                System.out.println("Not a valid payment method (card / bank only) :"
                        + paymentMethod);
                continue;
            }
        }

        // Ask user for the payment detail
        while (true) {
            System.out.println("Enter the payment detail of the customer: ");
            paymentDetail = scanner.nextLine().toLowerCase().trim();
            // Check if input is empty
            if (paymentDetail.isEmpty()) {
                System.out.println("Blank or empty inputs are not allowed. Please enter a valid payment detail.");
                continue;
            }
            // Validate payment detail
            if (ValidationHelper.validatePaymentDetails(paymentDetail)) {
                break;
            } else {
                System.out.println("Not a valid payment detail : " + paymentDetail);
                continue;
            }
        }

        if (userSupplements.isEmpty()) {
            return new PayingCustomer(name, email, paymentMethod, Integer.parseInt(paymentDetail));
        } else {
            return new PayingCustomer(name, email, userSupplements, paymentMethod, Integer.parseInt(paymentDetail));
        }
    }

    /**
     * Method to create a test associate customer object from user input
     * Name, email, and supplements are passed from createTestCustomer()
     * This method will only need to ask for the paying customer
     * Paying customer is mandatory for an associate customer
     * There can only be 1 paying customer for a associate customer
     * Expected to return an AssociateCustomer object to createTestCustomer()
     * 
     * @param customers       An arrayList of customer objects
     * @param name            The name of the associate customer
     * @param email           The email of the associate customer
     * @param userSupplements An arrayList of supplement objects
     * @return A test associate customer object
     */
    public static AssociateCustomer createTestAssociateCustomer(final ArrayList<Customer> customers, String name,
            String email, ArrayList<Supplement> userSupplements, Scanner scanner) {
        ArrayList<PayingCustomer> payingCustomersList = new ArrayList<PayingCustomer>();
        PayingCustomer payingCustomer = null;
        // Display the list of paying customers
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i) instanceof PayingCustomer) {
                payingCustomersList.add((PayingCustomer) customers.get(i));
            }
        }
        if (payingCustomersList.isEmpty()) {
            System.out.println("There are no paying customers. Please add a paying customer first.");
            return null;
        }
        for (int i = 0; i < payingCustomersList.size(); i++) {
            System.out.println(i + 1 + ". " + payingCustomersList.get(i).getName());
        }

        while (true) {
            // Ask user for a paying customer
            System.out.println("Enter the number of the paying customer that is beside the name: ");
            String input = scanner.nextLine().trim();
            // Check if input is empty
            if (input.isEmpty()) {
                System.out.println("Blank or empty inputs are not allowed. Please enter a valid number.");
            } else {
                // Try to parse the input as an integer
                try {
                    int payingCustomerNum = Integer.parseInt(input);
                    if (payingCustomerNum > 0 && payingCustomerNum <= payingCustomersList.size()) {
                        payingCustomer = payingCustomersList.get(payingCustomerNum - 1);
                        break;
                    } else {
                        System.out.println("Not a valid number :" + payingCustomerNum);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("A valid number expected, but got: " + input);
                    continue;
                }
            }
        }

        // Create a new associate customer object with the user input
        if (userSupplements.isEmpty() == false) {
            return new AssociateCustomer(name, email, userSupplements, payingCustomer);
        } else {
            return new AssociateCustomer(name, email, payingCustomer);
        }
    }

    /**
     * Method to create an arrayList of test customer objects from user input
     * Calls createTestCustomer() to create a test customer object
     * 
     * @param supplementsList An arrayList of supplement objects
     * @return An arrayList of test customer objects
     */
    public static ArrayList<Customer> createTestCustomers(ArrayList<Supplement> supplementsList, Scanner scanner) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        String response;
        while (true) {
            Customer customer = createTestCustomer(customers, supplementsList, scanner);
            if (customer != null) {
                // If customer is an associate customer, add the associate customer to the
                // paying customer
                // associate customers list. The paying customer is based on the associate
                // customer's paying customer attribute
                if (customer instanceof AssociateCustomer) {
                    // Get the paying customer from the associate customer
                    PayingCustomer payingCustomer = (PayingCustomer) ((AssociateCustomer) customer)
                            .getPayingCustomer();
                    // Find the matching customer object in the customers list
                    for (int i = 0; i < customers.size(); i++) {
                        if (customers.get(i).equals(payingCustomer)) {
                            // Add the associate customer to the paying customer's associate customers list
                            payingCustomer.addAssociateCustomer((AssociateCustomer) customer);
                        }
                    }
                }
                customers.add(customer);
            } else {
                continue;
            }

            while (true) {
                System.out.println("Do you want to add a customer? (y/n)");
                response = scanner.nextLine().toLowerCase().trim();
                if (response.isEmpty()) {
                    System.out.println("Blank or empty inputs are not allowed. Please enter 'y' or 'n'.");
                    continue;
                } else if (response.equals("n") || response.equals("y")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    continue;
                }
            }
            if (response.equals("n")) {
                break;
            }
        }
        return customers;
    }

    /**
     * Method to create an arrayList of customer objects with pre-defined data
     * 0. Create 1 paying customer with all parameters
     * 1. Create 1 paying customer without supplements
     * 2. Create 1 paying customer without associate customers
     * 3. Create 1 paying customer without supplements and associate customers
     * 4. Create 1 associate customer with all parameters, attached to customer 0
     * 5. Create 1 associate customer with all parameters, attached to
     * customer 1
     * 6. Create 1 associate customer without supplements, attached to customer 1
     * 
     * @param supplementsList An arrayList of supplement objects this list is
     *                        assumed to contain at least 5 supplements
     * @return An arrayList of customer objects with pre-defined data
     */
    public static ArrayList<Customer> createPreBuiltCustomers(ArrayList<Supplement> supplementsList) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        // Create 1 paying customer with all parameters
        ArrayList<Supplement> userSupplements = new ArrayList<Supplement>();
        userSupplements.add(supplementsList.get(0));
        userSupplements.add(supplementsList.get(1));
        customers
                .add(new PayingCustomer("Paying Customer 1", "paycust1@mail.com", userSupplements, "card", 1111111111));
        // Create 1 paying customer without supplements
        customers.add(new PayingCustomer("Paying Customer 2", "paycust2@mail.com", "bank", 222222222));
        // Create 1 paying customer without associate customers
        userSupplements.clear();
        userSupplements.add(supplementsList.get(2));
        customers.add(
                new PayingCustomer("Paying Customer 3", "paycust3@mail.com", userSupplements, "card", 333333333));
        // Create 1 paying customer without supplements and associate customers
        customers.add(new PayingCustomer("Paying Customer 4", "paycust4@gmail.com", "bank", 1234567890));
        // Create 2 associate customer with all parameters
        userSupplements.clear();
        userSupplements.add(supplementsList.get(3));
        userSupplements.add(supplementsList.get(4));
        customers.add(new AssociateCustomer("Associate Customer 1", "assocust1@gmail.com", userSupplements,
                customers.get(0)));
        userSupplements.clear();
        userSupplements.add(supplementsList.get(0));
        userSupplements.add(supplementsList.get(1));
        customers.add(new AssociateCustomer("Associate Customer 2", "assocust2@gmail.com", userSupplements,
                customers.get(1)));
        // Create 2 associate customer without supplements
        customers.add(new AssociateCustomer("Associate Customer 3", "assocust3@gmail.com", customers.get(1)));

        // Add the associate customers to the paying customer's associate customers list
        // Add associate customer 1 to the paying customer's associate customers list
        ((PayingCustomer) customers.get(0)).addAssociateCustomer((AssociateCustomer) customers.get(4));
        // Add associate customer 2 to the paying customer's associate customers list
        ((PayingCustomer) customers.get(1)).addAssociateCustomer((AssociateCustomer) customers.get(5));
        // Add associate customer 3 to the paying customer's associate customers list
        ((PayingCustomer) customers.get(1)).addAssociateCustomer((AssociateCustomer) customers.get(6));

        return customers;
    }

    /**
     * Method to remove a customer from the customers list
     * 
     * @param customers An arrayList of customer objects
     * @param customer  The Paying customer to remove from the customers list
     * @return
     */
    public static ArrayList<Customer> removeCustomer(ArrayList<Customer> customers, Customer customer) {
        // Check if it is a paying customer or associate customer
        // If it is a paying customer, remove the associate customers as well
        // if it is an associate customer, remove it from the paying customer's list as
        // well
        if (customer instanceof PayingCustomer) {
            ArrayList<AssociateCustomer> associateCustomers = ((PayingCustomer) customer).getAssociateCustomers();
            for (AssociateCustomer associateCustomer : associateCustomers) {
                // Remove the associate customer from customers list
                customers.remove(associateCustomer);
            }
        } else {
            PayingCustomer payingCustomer = (PayingCustomer) ((AssociateCustomer) customer).getPayingCustomer();
            payingCustomer.removeAssociateCustomer((AssociateCustomer) customer);
        }

        customers.remove(customer);
        return customers;
    }

}