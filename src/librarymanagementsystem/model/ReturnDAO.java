/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author ASUS
 */
public class ReturnDAO {
    public static boolean returnDAO(Return returnbook) {
        String query = "INSERT INTO Return (NIM, MahasiswaName, Year, Course, Email, BookID, BookName, Edition, Publisher, Pages, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connect.ConnectDB();
            PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, returnbook.getNim());
            pst.setString(2, returnbook.getMahasiswaName());
            pst.setString(3, returnbook.getYear());
            pst.setString(4, returnbook.getCourse());
            pst.setString(5, returnbook.getEmail());
            pst.setString(6, returnbook.getBookID());
            pst.setString(7, returnbook.getBookName());
            pst.setString(8, returnbook.getEdition());
            pst.setString(9, returnbook.getPublisher());
            pst.setInt(10, returnbook.getPages());
            pst.setString(11, returnbook.getIssueDate());
            pst.setString(12, returnbook.getReturnDate());

            int rowsInserted = pst.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
