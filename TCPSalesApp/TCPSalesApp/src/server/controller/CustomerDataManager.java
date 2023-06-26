package server.controller;

import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDataManager {
    private List<Customer> customers;

    public CustomerDataManager() {
        customers = new ArrayList<>();
        createSampleCustomers();
    }

    private void createSampleCustomers() {
        // Method to create a list of sample customer data
        // Add 10 sample customers to the list
        String[] names = {"Abu Baker", "Mary", "Michael Johnson", "Emily Davis", "Zebra Zack",
                          "Bin Ciling", "John Cin", "Sophia Martinez", "James Taylor", "Siti Fatimah"};

        for (int i = 1; i <= 10; i++) {
            Customer customer = new Customer();
            customer.setCustomerID(i);
            customer.setCustomerName(names[i - 1]);
            customers.add(customer);
        }
    }


    public Customer searchCustomerByName(String customerName) {
        // Method to search for a customer by name
        // Returns the Customer object if found, otherwise returns null
        if (customers != null) {
            for (Customer customer : customers) {
                if (customer.getCustomerName().toLowerCase().contains(customerName.toLowerCase())) {
                    return customer;
                }
            }
        }
        return null;
    }

    public Customer searchCustomerByID(int customerID) {
        // Method to search for a customer by ID
        // Returns the Customer object if found, otherwise returns null
        if (customers != null) {
            for (Customer customer : customers) {
                if (customer.getCustomerID() == customerID) {
                    return customer;
                }
            }
        }
        return null;
    }

    public List<Customer> getCustomers() {
        // Method to return the list of customers
        return customers;
    }
}
