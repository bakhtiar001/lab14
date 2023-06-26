package server.app;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import server.controller.CustomerDataManager;
import model.Customer;

public class TCPCustomersServerApp {

    public static void main(String[] args) {

        int portNo = 8088;
        CustomerDataManager manager = new CustomerDataManager();

        System.out.println("\nExecuting TCPCustomersServerApp");

        try {
            System.out.println("Waiting for next request");

            // 1. Bind to a port
            ServerSocket serverSocket = new ServerSocket(portNo);

            // 2. Server needs to continually run to listen to requests
            while (true) {
                // 3. Accept request from client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // 4. Process request - create input stream to read request
                // Request - customer ID: int
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());

                // Read customer ID from client
                int customerID = dis.readInt();
                System.out.println("Request for customer ID: " + customerID);

                // Get customer
                Customer customer = manager.searchCustomerByID(customerID);

                // 5. Respond to client
                OutputStream os = clientSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(customer);
                System.out.println("Sending customer: " + customer.getCustomerID() + " " + customer.getCustomerName());

                // Close the client socket
                clientSocket.close();
                System.out.println("Client disconnected\n");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
