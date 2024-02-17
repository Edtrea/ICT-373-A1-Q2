
/**
 * @Title: ICT 373 A1
 * @Author: Lim Wen Chao
 * @Date: 2/2/2024
 * @File: Client.java
 * @Purpose: This is the main class for the project.
 * The client program is to test the functionality of the classes and methods in the project.
 * This class is used to test the functionality of the classes and methods in the project.
 * The client program should do the following: 
 * a. construct a magazine with an array of 3-4 supplements with made-up details built in to the 
program (keep provision of inputs from the user using the Java Scanner class), 
 * b. construct an array of 5-6 different customers of various types with made-up details built in to the 
program (keep provision of inputs from the user using the Java Scanner class), 
 * c. print out the text of all the emails for all customers for four weeks of magazines, 
 * d. print out the text for the end of month emails for the paying customers, 
 * e. add a new customer to the magazine service, 
 * f. remove an existing customer from the magazine service
 * @Assumptions: 
 * 1. When asking if a user wants to create their own test data, any answer other than ‘y’ will be assumed to request to use pre-built test data
 * @Limitations:
 */

import java.util.ArrayList;
import java.util.Scanner;

import magazine.*;
import customer.AssociateCustomer;
import customer.Customer;
import customer.PayingCustomer;
import helper.TestDataHelper;

public class Client {
    public static void main(String[] args) {
        System.out.println(displayStudentDetails());
        try (// initialize scanner
                Scanner scanner = new Scanner(System.in)) {
            String response;
            /**
             * a. construct a magazine with an array of 3-4 supplements with made-up details
             * built in to the program (keep provision of inputs from the user using the
             * Java Scanner class)
             * Ask user if they want to create their own test magazine or use pre-built ones
             * Ask user if they want to create thier own set of supplements or use pre-built
             * ones
             */
            Magazine magazine;
            System.out.println(
                    "Do you want to create your own test magazine? If not, pre-built test data are available. (y/n)");
            while (true) {
                response = scanner.nextLine().toLowerCase().trim();
                // Check if input is empty
                if (response.isEmpty()) {
                    System.out.println("Blank or empty inputs are not allowed. Please enter 'y' or 'n'.");
                    continue;
                }
                // Proceed based on response
                if (response.equals("y")) {
                    magazine = TestDataHelper.createTestMagazine(scanner);
                    break;
                } else if (response.equals("n")) {
                    magazine = TestDataHelper.createPreBuiltMagazine();
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            }

            ArrayList<Supplement> supplements = new ArrayList<Supplement>();
            System.err.println(
                    "Do you want to create your own test supplements? If not, pre-built test data are available. (y/n)");
            while (true) {
                response = scanner.nextLine().toLowerCase().trim();
                // Check if input is empty
                if (response.isEmpty()) {
                    System.out.println("Blank or empty inputs are not allowed. Please enter 'y' or 'n'.");
                    continue;
                }
                // Proceed based on response
                if (response.equals("y")) {
                    supplements = TestDataHelper.createTestSupplements(scanner);
                    break;
                } else if (response.equals("n")) {
                    supplements = TestDataHelper.createPreBuiltSupplements();
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            }

            /**
             * b. construct an array of 5-6 different customers of various types with
             * made-up details built in to the program (keep provision of inputs from the
             * user using the Java Scanner class)
             * Ask the user if they want to create their own test customers or use pre-built
             * ones
             */
            ArrayList<Customer> customers = new ArrayList<>();
            System.out.println(
                    "Do you want to create your own test customers? If not, pre-built test data are available. (y/n)");
            while (true) {
                response = scanner.nextLine().toLowerCase().trim();
                // Check if input is empty
                if (response.isEmpty()) {
                    System.out.println("Blank or empty inputs are not allowed. Please enter 'y' or 'n'.");
                    continue;
                }

                if (response.equals("y")) {
                    while (true) {
                        customers = TestDataHelper.createTestCustomers(supplements, scanner);
                        if (customers == null) {
                            System.out.println("Invalid input. Please try again.");
                        } else {
                            break;
                        }
                    }
                    break;
                } else if (response.equals("n")) {
                    customers = TestDataHelper.createPreBuiltCustomers(supplements);
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    break;
                }
            }

            /**
             * Display the menu for the user to select which function they want to execute
             * Ask the user for which function c, d, e, or f they want to execute until the
             * user select to exit program
             */
            while (true) {
                System.out.println("Select a function to execute: ");
                System.out.println(
                        "c. print out the text of all the emails for all customers for four weeks of magazines");
                System.out.println("d. print out the text for the end of month emails for the paying customers");
                System.out.println("e. add a new customer to the magazine service");
                System.out.println("f. remove an existing customer from the magazine service");
                System.out.println("exit. Exit program");
                System.out.println();
                response = scanner.nextLine();

                switch (response) {
                    case "c":
                        /**
                         * c. print out the text of all the emails for all customers for four weeks of
                         * magazines
                         * Each week, each customer gets an email telling them their magazine is ready
                         * to look at and listing the supplements that they currently subscribe to.
                         */
                        for (int i = 0; i < 4; i++) {
                            System.out.println("Week " + (i + 1) + " emails:\n");
                            for (Customer customer : customers) {
                                System.out.println(customer.getWeeklyEmail());
                            }
                        }
                        break;
                    case "d":
                        /**
                         * d. print out the text for the end of month emails for the paying customers
                         * Each month, each paying customer gets an email telling them how much is being
                         * charged to their card or account for the month and itemizing the cost by
                         * supplements for them and any of their associate customers.
                         */
                        for (Customer customer : customers) {
                            if (customer instanceof PayingCustomer) {
                                System.out.println(customer.getMonthlyEmail(magazine));
                            }
                        }
                        break;
                    case "e":
                        /**
                         * e. add a new customer to the magazine service
                         */
                        Customer newCustomer = TestDataHelper.createTestCustomer(customers, supplements, scanner);
                        // If newCustomer is an associate customer, add it to the list of associate
                        // customer for the paying customer that is linked to the new associate customer
                        if (newCustomer instanceof AssociateCustomer) {
                            PayingCustomer payingCustomer = (PayingCustomer) ((AssociateCustomer) newCustomer)
                                    .getPayingCustomer();
                            payingCustomer.addAssociateCustomer((AssociateCustomer) newCustomer);
                        }
                        customers.add(newCustomer);
                        break;
                    case "f":
                        /**
                         * f. remove an existing customer from the magazine service
                         */
                        // Display the list of customers
                        for (int i = 0; i < customers.size(); i++) {
                            System.out.println(i + 1 + ". " + customers.get(i).getName());
                        }
                        while (true) {
                            System.out.println("Enter the index of the customer to remove: ");
                            String input = scanner.nextLine().trim();
                            // Check if input is empty
                            if (input.isEmpty()) {
                                System.out
                                        .println("Blank or empty inputs are not allowed. Please enter a valid index.");
                                continue;
                            }
                            // Try to parse the input as an integer
                            try {
                                int index = Integer.parseInt(input);
                                if (index < 1 || index > customers.size()) {
                                    System.out.println("Invalid index");
                                } else {
                                    Customer customer = customers.get(index - 1);
                                    customers = TestDataHelper.removeCustomer(customers, customer);
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("A valid index expected, but got: " + input);
                            }
                        }

                        break;
                    case "exit":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            }
        }
    }

    static String displayStudentDetails() {
        StringBuilder studentDetails = new StringBuilder();
        studentDetails.append("Name: Lim Wen Chao\n");
        studentDetails.append("Student ID: 34368872\n");
        studentDetails.append("Mode of Enrolment: Kaplan Singapore\n");
        studentDetails.append("Tutor Name: Andy Lee Kok Eng\n");
        studentDetails.append("Tutorial Day and Time: Tuesday 2:15PM\n");
        return studentDetails.toString();
    }
}