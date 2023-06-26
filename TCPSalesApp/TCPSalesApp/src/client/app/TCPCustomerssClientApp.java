package client.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Collections;

import model.Customer;

public class TCPCustomerssClientApp {

    public static void main(String[] args) {
        try {
            // Server information
            int serverPortNo = 8088;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // Connect to the server
            Socket socket = new Socket(serverAddress, serverPortNo);

            // Create stream to receive response from the server
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            // Read response from the server - cast the object
            List<Customer> customers = (List<Customer>) ois.readObject();

            // Sort customers by name in alphabetical order
            Collections.sort(customers, (c1, c2) -> c1.getCustomerName().compareToIgnoreCase(c2.getCustomerName()));

            // Process response - display customer details
            System.out.println("Customer Information (Alphabetical Order)");
            for (Customer customer : customers) {
                System.out.println("Customer ID: " + customer.getCustomerID());
                System.out.println("Name: " + customer.getCustomerName());
                System.out.println();
            }

            // Close the socket
            socket.close();

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
