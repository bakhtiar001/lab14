package client.view;

import java.util.List;

import model.Customer;

public class CustomerViewer {

    public void displayCustomer(Customer customer) {
        if (customer != null) {
            System.out.println("\nCustomer Information");
            System.out.println("Customer ID: " + customer.getCustomerID());
            System.out.println("Name: " + customer.getCustomerName());
        } else {
            System.out.println("Customer not found.");
        }
    }
}
