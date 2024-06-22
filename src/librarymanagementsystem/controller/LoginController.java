/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.controller;


/**
 *
 * @author ASUS
 */
import javax.swing.JOptionPane;
import librarymanagementsystem.view.Loading;
import librarymanagementsystem.model.User;
import librarymanagementsystem.model.UserDAO;
import librarymanagementsystem.view.LoginView;
import librarymanagementsystem.view.SignUpView;

public class LoginController {
    private LoginView view;
    
    public LoginController(LoginView view) {
        this.view = view;
    }
    
    public void Login() {
        String email = view.getEmail();
        String password = view.getPassword();
    
        User user = new User(email,password);
        UserDAO userDAO = new UserDAO();

        if (userDAO.isValidLogin(user)) {
            // Close the login frame
            goLoading();
        } else {
            // Display an error message if the login is unsuccessful
            JOptionPane.showMessageDialog(view, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } 
    
    public void goSignUp(){
        SignUpView signUpFrame = new SignUpView();
        signUpFrame.setVisible(true);
        signUpFrame.pack();
        signUpFrame.setLocationRelativeTo(null); 
        view.dispose();
    } 
    
    public void goLoading(){
        view.dispose();
        Loading loadingFrame = new Loading();
        loadingFrame.setVisible(true);
        loadingFrame.setLocationRelativeTo(null);
    }
}
