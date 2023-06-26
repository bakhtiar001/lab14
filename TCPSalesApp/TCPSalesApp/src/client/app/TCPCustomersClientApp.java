package client.app;

import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import server.controller.CustomerDataManager;
import model.Customer;
import client.view.CustomerViewer;

public class TCPCustomersClientApp {

    public static void main(String[] args) {

        try {
            // Server information
            int serverPortNo = 8088;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // 1. Connect to remote machine
            Socket socket = new Socket(serverAddress, serverPortNo);

            // Create stream to receive response from the server
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // Send customer ID requests to the server
            int[] customerIDs = {1, 5};
            for (int customerID : customerIDs) {
                // 2. Send request to the server
                OutputStream os = socket.getOutputStream();
                os.write(customerID);
                os.flush();
                System.out.println("Requesting customer ID: " + customerID);

                // 3. Read response from the server - cast the object
                Customer customer = (Customer) ois.readObject();

                // 4. Process response - display the object
                if (customer != null) {
                    System.out.println("Customer Information (From the server)");
                    System.out.println("Customer ID: " + customer.getCustomerID());
                    System.out.println("Name: " + customer.getCustomerName());
                } else {
                    System.out.println("Customer not found");
                }
                System.out.println();
            }

            // Close the socket
            socket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
