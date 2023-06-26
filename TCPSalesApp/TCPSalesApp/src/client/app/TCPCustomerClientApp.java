package client.app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import server.controller.CustomerDataManager;
import model.Customer;
import client.view.CustomerViewer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPCustomerClientApp {

    public static void main(String[] args) {

        try {

            // Server information
            int serverPortNo = 8088;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // 1. Connect to the remote machine
            Socket socket = new Socket(serverAddress, serverPortNo);

            // Create stream to send request
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            // 2. Send a single customer name to the server
            String customerName = "John Cin";
            oos.writeObject(customerName);
            System.out.println("Requesting customer information for: " + customerName + "\n");

            // Create stream to receive response from the server
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // 3. Read response from the server - cast object
            Customer customer = (Customer) ois.readObject();

            // 4. Process response - display customer information
            CustomerViewer customerViewer = new CustomerViewer();
            customerViewer.displayCustomer(customer);
            

            // Close the socket
            socket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
