/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.model;
/**
 *
 * @author ASUS
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {
    public boolean addUser(User user) throws SQLException {
        try (Connection conn = Connect.ConnectDB()) {
            String query = "INSERT INTO Account (NIP, Name, Email, Password) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, user.getNip());
            pst.setString(2, user.getName());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPassword());
            int rowsInserted = pst.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isValidLogin(User user) {
        // SQL query to check if the entered email and password exist in the database
        String query = "SELECT COUNT(*) FROM Account WHERE Email = ? AND Password = ?";

        try (Connection conn = Connect.ConnectDB();
            PreparedStatement pst = conn.prepareStatement(query)) {
            // Set parameters in the prepared statement
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());

            // Execute the query
            ResultSet rs = pst.executeQuery();

            // Check if there's at least one row in the result set (means the login is valid)
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Returns true if count > 0, false otherwise
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing login query", e);
        }

        // If there's an error or no matching user in the database, return false
        return false;
    }
}
