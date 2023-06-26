package server.app;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import server.controller.CustomerDataManager;
import model.Customer;

public class TCPCustomerssServerApp {

    public static void main(String[] args) {

        int portNo = 8088;

        CustomerDataManager manager = new CustomerDataManager();

        System.out.println("\n\tExecuting TCPCustomersServerApp");

        try {
            System.out.println("\tWaiting for next request");

            // 1. Bind to a port
            ServerSocket serverSocket = new ServerSocket(portNo);

            // 2. Server needs to continually run to listen to requests
            while (true) {
                // 3. Accept request from client
                Socket clientSocket = serverSocket.accept();

                // 4. Process request - create output stream to send response
                OutputStream os = clientSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);

                // Get list of customers
                List<Customer> customers = manager.getCustomers();

                // 5. Respond to client
                oos.writeObject(customers);
                System.out.println("\tSending " + customers.size() + " customers");

                // Close the socket
                clientSocket.close();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
