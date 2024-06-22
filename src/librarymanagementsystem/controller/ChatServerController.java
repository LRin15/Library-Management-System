/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import librarymanagementsystem.view.ChatServerView;
/**
 *
 * @author ASUS
 */
public class ChatServerController {
    private final ChatServerView view;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public ChatServerController(ChatServerView view) {
        this.view = view;
    }

    public void startServer() {
        try {
            // Start server socket and accept incoming connections
            ServerSocket serverSocket = new ServerSocket(1201);
            socket = serverSocket.accept();
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            // Listen for messages from the client in a new thread
            new Thread(() -> {
                try {
                    String msgIn;
                    while (true) {
                        msgIn = dataInputStream.readUTF();
                        view.displayMessage("Client: " + msgIn);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            dataOutputStream.writeUTF(message);
            view.displayMessage("Server: " + message);
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
