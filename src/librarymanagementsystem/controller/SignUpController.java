/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.controller;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import librarymanagementsystem.model.User;
import librarymanagementsystem.model.UserDAO;
import librarymanagementsystem.view.LoginView;
import librarymanagementsystem.view.SignUpView;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author ASUS
 */
public class SignUpController {
    private SignUpView view;

    public SignUpController(SignUpView view) {
        this.view = view;
    }
    
    public void addUser() {
        String nip = view.getNip();
        String name = view.getName();
        String email = view.getEmail();
        String password = view.getPassword();

        if (!isValidNip(nip)) {
            JOptionPane.showMessageDialog(view, "NIP harus terdiri dari 18 digit angka.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidName(name)) {
            JOptionPane.showMessageDialog(view, "Nama harus terdiri dari maksimal 50 karakter.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(view, "Email harus mengandung '@'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidPassword(password)) {
            JOptionPane.showMessageDialog(view, "Password harus terdiri dari minimal 8 karakter dan mengandung kombinasi huruf dan angka.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        User user = new User(nip, name, email, password);
        UserDAO userDAO = new UserDAO();

        try {
            if (userDAO.addUser(user)) {
                JOptionPane.showMessageDialog(view, "Account created successfully.");
                goLogin();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to create account.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(view, "Email already exists. Please choose a different email.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "An unexpected error occurred. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } 
    
    public void goLogin(){
        view.clearFields();
        LoginView loginFrame = new LoginView();
        loginFrame.setVisible(true);
        loginFrame.setLocationRelativeTo(null);
        view.dispose();
    } 
    
    private boolean isValidNip(String nip) {
        return nip.matches("\\d{18}");
    }
    
    private boolean isValidEmail(String email) {
        return email.contains("@");
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (hasLetter && hasDigit) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isValidName(String name) {
        return name.length() <= 50;
    }
}
