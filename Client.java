/**
 * @Title: ICT 373 A1
 * @Author: Lim Wen Chao
 * @Date: 2024/2/2
 * @File: Client.java
 * @Version: 1.0
 * @Revision: none
 * @Usage: This is the main class for the project.
 * The client program is to test the functionality of the classes and methods in the project.
 * @Description: This class is used to test the functionality of the classes and methods in the project.
 * The client program should do the following: 
 * a. construct a magazine with an array of 3-4 supplements with made-up details built in to the 
program (keep provision of inputs from the user using the Java Scanner class), 
 * b. construct an array of 5-6 different customers of various types with made-up details built in to the 
program (keep provision of inputs from the user using the Java Scanner class), 
 * c. print out the text of all the emails for all customers for four weeks of magazines, 
 * d. print out the text for the end of month emails for the paying customers, 
 * e. add a new customer to the magazine service, 
 * f. remove an existing customer from the magazine service, 
 */
package src;

import java.util.ArrayList;
import java.util.Scanner;

import src.Magazine.Magazine;
import src.Magazine.Supplement;
import src.customer.Customer;
import src.customer.PayingCustomer;
import src.helper.EmailHelper;
import src.helper.TestDataHelper;

public class Client {
    public static void main(String[] args) {
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
            System.out.println("Do you want to create your own test magazine or use pre-built? (y/n)");
            response = scanner.nextLine();
            Magazine magazine;
            if (response.equals("y")) {
                magazine = TestDataHelper.createTestMagazine();
            } else {
                magazine = TestDataHelper.createPreBuiltMagazine();
            }

            System.err.println("Do you want to create your own test supplements or use pre-built? (y/n)");
            response = scanner.nextLine();
            ArrayList<Supplement> supplements;
            if (response.equals("y")) {
                supplements = TestDataHelper.createTestSupplements();
            } else {
                supplements = TestDataHelper.createPreBuiltSupplements();
            }

            /**
             * b. construct an array of 5-6 different customers of various types with
             * made-up details built in to the program (keep provision of inputs from the
             * user using the Java Scanner class)
             * Ask the user if they want to create their own test customers or use pre-built
             * ones
             */
            System.out.println("Do you want to create your own test customers or use pre-built? (y/n)");
            response = scanner.nextLine();
            ArrayList<Customer> customers;
            while (true) {
                if (response.equals("y")) {
                    customers = TestDataHelper.createTestCustomers(supplements);
                } else {
                    customers = TestDataHelper.createPreBuiltCustomers(supplements);
                    break;
                }
                System.out.println("Do you want to add more customers? (y/n)");
                response = scanner.nextLine();
                if (response.equals("n")) {
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
                response = scanner.nextLine();
                switch (response) {
                    case "c":
                        /**
                         * c. print out the text of all the emails for all customers for four weeks of
                         * magazines
                         * Each week, each customer gets an email telling them their magazine is ready
                         * to look at and listing the supplements that they currently subscribe to.
                         */
                        for (int i = 0; i < customers.size(); i++) {
                            System.out.println("Week " + (i + 1) + " emails:");
                            for (Customer customer : customers) {
                                System.out.println(EmailHelper.getWeeklyEmail(customer, magazine));
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
                                System.out.println(EmailHelper.getMonthlyEmail(customer, magazine));
                            }
                        }
                        break;
                    case "e":
                        /**
                         * e. add a new customer to the magazine service
                         */
                        customers.add(TestDataHelper.createTestCustomer(customers, supplements));
                        break;
                    case "f":
                        /**
                         * f. remove an existing customer from the magazine service
                         */
                        // Display the list of customers
                        for (int i = 0; i < customers.size(); i++) {
                            System.out.println(i + ". " + customers.get(i).getName());
                        }
                        System.out.println("Enter the index of the customer to remove: ");
                        int index = scanner.nextInt();
                        if (index < 0 || index >= customers.size()) {
                            System.out.println("Invalid index");
                            break;
                        }

                        Customer customer = customers.get(index);
                        customers = TestDataHelper.removeCustomer(customers, customer);

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
}