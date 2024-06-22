/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import librarymanagementsystem.view.ChatClientView;

/**
 *
 * @author ASUS
 */
public class ChatClientController {
   private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private final ChatClientView view;

    public ChatClientController(ChatClientView view) {
        this.view = view;
    }

    public void connectToServer() {
        try {
            // Replace with your server address and port
            socket = new Socket("localhost", 1201);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            // Listen for messages from the server in a new thread
            new Thread(() -> {
                try {
                    String message;
                    while ((message = dataInputStream.readUTF()) != null) {
                        System.out.println("Received message from server: " + message);
                        view.displayMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Unable to connect to the server.");
        }
    }

    public void sendMessage(String message) {
        try {
            dataOutputStream.writeUTF(message);
            view.displayMessage("Client: " + message);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error sending message.");
        }
    }

    public void closeConnection() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
