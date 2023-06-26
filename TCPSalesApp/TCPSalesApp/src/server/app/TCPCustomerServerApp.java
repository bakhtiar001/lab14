package server.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import server.controller.CustomerDataManager;
import model.Customer;

public class TCPCustomerServerApp {
    public static void main(String[] args) {
        int portNo = 8088;

        CustomerDataManager customerManager = new CustomerDataManager();

        System.out.println("\n\tExecuting TCPCustomerServerApp");

        try {
            System.out.println("\tWaiting for next request");

            // 1. Bind to a port
            ServerSocket serverSocket = new ServerSocket(portNo);

            // 2. Server needs to continually run to listen to requests
            while (true) {
                // 3. Accept request from client
                Socket clientSocket = serverSocket.accept();
                System.out.println("\tClient connected: " + clientSocket.getInetAddress().getHostAddress());

                // 4. Process request - create input stream to read request
                // Request - customer name: string
                InputStream is = clientSocket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                // Read customer name from the client
                String customerName = br.readLine();
                System.out.println("\tRequest for customer name: " + customerName);

                // Get customer by name
                Customer customer = customerManager.searchCustomerByName(customerName);

                // 5. Respond to client
                OutputStream os = clientSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(customer);
                System.out.println("\tSending customer: " + customer.getCustomerID() + " " + customer.getCustomerName());

                // Close the client socket
                clientSocket.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
