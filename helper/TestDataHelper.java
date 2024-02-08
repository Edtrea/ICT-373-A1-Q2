/**
 * @Author: Lim Wen Chao
 * @Date: 2024/2/2
 * @Version: 1.0
 * @Revision: none
 * @Usage: A helper class that provides test data for the application either from user inputs or pre-defined data
 * @Description: Contains 4 methods to provide test data for the application
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
 */

package src.helper;

import java.util.ArrayList;
import java.util.Scanner;
import src.Magazine.Magazine;
import src.Magazine.Supplement;
import src.customer.AssociateCustomer;
import src.customer.Customer;
import src.customer.PayingCustomer;

public class TestDataHelper {
    /**
     * Method to create a test magazine object from user input
     * 
     * @return A test magazine object
     */
    public static Magazine createTestMagazine() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println("Enter the weekly cost of the magazine: ");
            // if the next is a Double
            if (scanner.hasNextDouble()) {
                double weeklyCost = scanner.nextDouble();
                scanner.close();
                return new Magazine(weeklyCost);
            }
            // if no Double is found
            else {
                System.out.println("A double value expected :"
                        + scanner.next());
            }
        }
        scanner.close();
        return null;
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
    public static ArrayList<Supplement> createTestSupplements() {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<Supplement> supplements = new ArrayList<Supplement>();

            while (true) {
                System.out.println("Do you want to add a supplement? (y/n)");
                String response = scanner.nextLine();
                if (response.equals("n")) {
                    break;
                }
                System.out.println("Enter the name of the supplement: ");
                String name = scanner.next();
                while (true) {
                    System.out.println("Enter the weekly cost of the supplement: ");
                    // if the next is a Double
                    if (scanner.hasNextDouble()) {
                        double weeklyCost = scanner.nextDouble();
                        supplements.add(new Supplement(name, weeklyCost));
                        break;
                    }
                    // if no Double is found
                    else {
                        System.out.println("A double value expected :"
                                + scanner.next());
                    }
                }
            }
            return supplements;
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
            ArrayList<Supplement> supplementsList) {
        try (Scanner scanner = new Scanner(System.in)) {
            String name;
            String email;
            ArrayList<Supplement> userSupplements = new ArrayList<>();
            boolean isPayingCustomer = false;

            System.out.println("Enter the name of the customer: ");
            name = scanner.next();
            System.out.println("Enter the email of the customer: ");
            email = scanner.next();
            System.out.println("Does the customer subscribe to any supplements? (y/n)");
            String response = scanner.nextLine();
            if (response.equals("y")) {
                for (int i = 0; i < supplementsList.size(); i++) {
                    System.out.println(
                            i + 1 + ". " + supplementsList.get(i).getName() + " - "
                                    + supplementsList.get(i).getWeeklyCost());
                }
                while (true) {
                    System.out.println("Enter the number of the supplement that is beside the name: ");
                    if (scanner.hasNextInt()) {
                        int supplementNum = scanner.nextInt();
                        userSupplements.add(supplementsList.get(supplementNum - 1));
                    } else {
                        System.out.println("Not a valid number :"
                                + scanner.next());
                    }
                    System.out.println("Do you want to add another supplement? (y/n)");
                    response = scanner.nextLine();
                    if (response.equals("n")) {
                        break;
                    }
                }
            }
            System.out.println("Is the customer a paying customer or an associate customer? (p/a)");
            response = scanner.nextLine();
            if (response.equals("p")) {
                isPayingCustomer = true;
            }
            if (isPayingCustomer) {
                return createTestPayingCustomer(customers, name, email, userSupplements);
            } else {
                return createTestAssociateCustomer(customers, name, email, userSupplements);
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
            ArrayList<Supplement> userSupplements) {
        try (Scanner scanner = new Scanner(System.in)) {
            // If the customer is a paying customer, ask for the payment method and payment
            // detail and associate customers
            String paymentMethod;
            String paymentDetail;
            ArrayList<AssociateCustomer> userAssociateCustomers = new ArrayList<AssociateCustomer>();
            boolean hasAssociateCustomers = false;
            // Ask user for the payment method
            while (true) {
                paymentMethod = ""; // Initialize paymentMethod variable
                System.out.println("Enter the payment method of the customer (card / bank): ");
                if (scanner.hasNext()) {
                    paymentMethod = scanner.next().trim().toLowerCase();
                    // Validate payment method
                    if (ValidationHelper.validatePaymentMethod(paymentMethod)) {
                        break;
                    } else {
                        System.out.println("Not a valid payment method (card / bank only) :"
                                + paymentMethod);
                    }
                } else {
                    System.out.println("Payment method is mandatory :"
                            + paymentMethod);
                }
            }

            // Ask user for the payment detail
            while (true) {
                System.out.println("Enter the payment detail of the customer: ");
                if (scanner.hasNext()) {
                    paymentDetail = scanner.next();
                    // Validate payment detail
                    if (ValidationHelper.validatePaymentDetails(paymentDetail)) {
                        break;
                    } else {
                        System.out.println("Not a valid payment detail :"
                                + scanner.next());
                    }
                } else {
                    System.out.println("Payment detail is mandatory :"
                            + scanner.next());
                }
            }

            // Ask user if the customer has any associate customers
            System.out.println("Does the customer have any associate customers? (y/n)");
            String response = scanner.nextLine();
            if (response.equals("y")) {
                hasAssociateCustomers = true;
            }
            // If the customer has associate customers, display the list of associate
            // customers that exists in customers list and ask the user to select the
            // associate customers
            // If the customer does not have any associate customers, do not pass any
            // associate customers
            if (hasAssociateCustomers) {
                ArrayList<AssociateCustomer> associateCustomersList = new ArrayList<AssociateCustomer>();
                // Get all associate customers from the customers list
                for (int i = 0; i < customers.size(); i++) {
                    if (customers.get(i) instanceof AssociateCustomer) {
                        associateCustomersList.add((AssociateCustomer) customers.get(i));
                    }
                }

                if (associateCustomersList.isEmpty()) {
                    System.out.println(
                            "There are no associate customers in the list. Please add an associate customer first.");
                    System.out.println("The customer will be created without any associate customers.");
                    // Create a new paying customer object with the user input
                    if (userSupplements.isEmpty() == false) {
                        return new PayingCustomer(name, email, userSupplements, paymentMethod, paymentDetail);
                    } else {
                        return new PayingCustomer(name, email, paymentMethod, paymentDetail);
                    }
                } else {
                    // Display the list of associate customers
                    for (int i = 0; i < associateCustomersList.size(); i++) {
                        System.out.println(i + 1 + ". " + associateCustomersList.get(i).getName());
                    }

                    // Ask user for the number of associate customers the customer has
                    System.out.println("Enter the number of associate customers the customer has: ");
                    int numAssociateCustomers = scanner.nextInt();
                    // Ask user which associate customers the customer has based on the displayed
                    // list
                    for (int i = 0; i < numAssociateCustomers; i++) {
                        System.out.println("Enter the number of the associate customer that is beside the name: ");
                        int associateCustomerNum = scanner.nextInt();
                        if (associateCustomerNum > 0 && associateCustomerNum <= associateCustomersList.size()) {
                            userAssociateCustomers
                                    .add((AssociateCustomer) associateCustomersList.get(associateCustomerNum - 1));
                        } else {
                            System.out.println("Not a valid number :"
                                    + associateCustomerNum);
                        }
                        userAssociateCustomers
                                .add((AssociateCustomer) associateCustomersList.get(associateCustomerNum - 1));
                    }

                    // Create a new paying customer object with the user input
                    if (userSupplements.isEmpty() == false && userAssociateCustomers != null) {
                        return new PayingCustomer(name, email,
                                userSupplements, paymentMethod, paymentDetail,
                                userAssociateCustomers);
                    } else if (userSupplements.isEmpty() == false && userAssociateCustomers == null) {
                        return new PayingCustomer(name, email, userSupplements, paymentMethod, paymentDetail);
                    } else if (userAssociateCustomers != null && userSupplements.isEmpty() == true) {
                        return new PayingCustomer(name, email, paymentMethod, paymentDetail,
                                userAssociateCustomers);
                    } else {
                        return new PayingCustomer(name, email, paymentMethod, paymentDetail);
                    }
                }
            }
        }
        return null;
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
            String email, ArrayList<Supplement> userSupplements) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<PayingCustomer> payingCustomersList = new ArrayList<PayingCustomer>();
            // Display the list of paying customers
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i) instanceof PayingCustomer) {
                    payingCustomersList.add((PayingCustomer) customers.get(i));
                }
            }
            if (payingCustomersList.isEmpty()) {
                System.out.println("There are no paying customers in the list. Please add a paying customer first.");
                return null;
            }
            for (int i = 0; i < payingCustomersList.size(); i++) {
                System.out.println(i + 1 + ". " + payingCustomersList.get(i).getName());
            }

            // Ask user for a paying customer
            System.out.println("Enter the number of the paying customer that is beside the name: ");
            int payingCustomerNum = scanner.nextInt();
            if (payingCustomerNum > 0 && payingCustomerNum <= payingCustomersList.size()) {
                payingCustomersList.get(payingCustomerNum - 1);
            } else {
                System.out.println("Not a valid number :"
                        + payingCustomerNum);
            }
            // Create a new associate customer object with the user input
            if (userSupplements.isEmpty() == false) {
                return new AssociateCustomer(name, email,
                        userSupplements,
                        payingCustomersList.get(payingCustomerNum - 1));
            } else {
                return new AssociateCustomer(name, email, payingCustomersList.get(payingCustomerNum - 1));
            }
        }
    }

    /**
     * Method to create an arrayList of test customer objects from user input
     * Calls createTestCustomer() to create a test customer object
     * 
     * @param supplementsList An arrayList of supplement objects
     * @return An arrayList of test customer objects
     */
    public static ArrayList<Customer> createTestCustomers(ArrayList<Supplement> supplementsList) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<Customer> customers = new ArrayList<Customer>();
            while (true) {
                System.out.println("Do you want to add a customer? (y/n)");
                String response = scanner.nextLine();
                if (response.equals("n")) {
                    break;
                }
                Customer customer = createTestCustomer(customers, supplementsList);
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
                }
            }
            return customers;
        }
    }

    /**
     * Method to create an arrayList of customer objects with pre-defined data
     * 1. Create 1 paying customer with all parameters
     * 2. Create 1 paying customer without supplements
     * 3. Create 1 paying customer without associate customers
     * 4. Create 1 paying customer without supplements and associate customers
     * 5. Create 2 associate customer with all parameters
     * 6. Create 2 associate customer without supplements
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
                .add(new PayingCustomer("Paying Customer 1", "paycust1@mail.com", userSupplements, "card", "1234567890",
                        null));
        // Create 1 paying customer without supplements
        customers.add(new PayingCustomer("Paying Customer 2", "paycust2@mail.com", "card", "1234567890", null));
        // Create 1 paying customer without associate customers
        userSupplements.clear();
        userSupplements.add(supplementsList.get(2));
        customers.add(
                new PayingCustomer("Paying Customer 3", "paycust3@mail.com", userSupplements, "card", "1234567890"));
        // Create 1 paying customer without supplements and associate customers
        customers.add(new PayingCustomer("Paying Customer 4", "paycust4@gmail.com", "card", "1234567890"));
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
        customers.add(new AssociateCustomer("Associate Customer 3", "assocust3@gmail.com", customers.get(0)));
        customers.add(new AssociateCustomer("Associate Customer 4", "assocust4@gmail.com", customers.get(1)));

        // Add the associate customers to the paying customer's associate customers list
        // Add associate customer 1 to the paying customer's associate customers list
        ((PayingCustomer) customers.get(0)).addAssociateCustomer((AssociateCustomer) customers.get(4));
        // Add associate customer 2 to the paying customer's associate customers list
        ((PayingCustomer) customers.get(1)).addAssociateCustomer((AssociateCustomer) customers.get(5));
        // Add associate customer 3 to the paying customer's associate customers list
        ((PayingCustomer) customers.get(0)).addAssociateCustomer((AssociateCustomer) customers.get(6));
        // Add associate customer 4 to the paying customer's associate customers list
        ((PayingCustomer) customers.get(1)).addAssociateCustomer((AssociateCustomer) customers.get(7));

        return customers;
    }

    /**
     * Method to remove a customer from the customers list
     * 
     * @param customers An arrayList of customer objects
     * @param customer  The customer to remove from the customers list
     * @return
     */
    public static ArrayList<Customer> removeCustomer(ArrayList<Customer> customers, Customer customer) {
        // Check if it is a paying customer or associate customer
        // If it is a paying customer, remove the associate customers as well
        // if it is an associate customer, remove it from the paying customer's list as
        // well
        if (customer instanceof PayingCustomer) {
            for (int i = 0; i < ((PayingCustomer) customer).getAssociateCustomers().size(); i++) {
                ((PayingCustomer) customer).getAssociateCustomers().remove(i);
            }
        } else {
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i) instanceof PayingCustomer) {
                    ((PayingCustomer) customers.get(i)).getAssociateCustomers().remove(customer);
                }
            }
        }
        customers.remove(customer);
        return customers;
    }

}