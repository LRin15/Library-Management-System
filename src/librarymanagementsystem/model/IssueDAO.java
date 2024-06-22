/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class IssueDAO {
    public static boolean issueDAO(Issue issue) {
        String query = "INSERT INTO Issue (NIM, MahasiswaName, Year, Course, Email, BookID, BookName, Edition, Publisher, Pages, IssueDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connect.ConnectDB();
            PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, issue.getNim());
            pst.setString(2, issue.getMahasiswaName());
            pst.setString(3, issue.getYear());
            pst.setString(4, issue.getCourse());
            pst.setString(5, issue.getEmail());
            pst.setString(6, issue.getBookID());
            pst.setString(7, issue.getBookName());
            pst.setString(8, issue.getEdition());
            pst.setString(9, issue.getPublisher());
            pst.setInt(10, issue.getPages());
            pst.setString(11, issue.getIssueDate());

            int rowsInserted = pst.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Issue getIssueByNIM(String nim) throws SQLException {
        String query = "SELECT * FROM Issue WHERE nim = ? AND status = 'issued'";
        try (Connection conn = Connect.ConnectDB();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nim);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Issue(
                        rs.getString("nim"),
                        rs.getString("MahasiswaName"),
                        rs.getString("Year"),
                        rs.getString("Course"),
                        rs.getString("Email"),
                        rs.getString("BookID"),
                        rs.getString("BookName"),
                        rs.getString("Edition"),
                        rs.getString("Publisher"),
                        rs.getInt("Pages"),
                        rs.getString("IssueDate")
                );
            }
        }
        return null;
    }

    public static Issue getIssueByNIMandID(String nim, String bookID) throws SQLException {
        try (Connection conn = Connect.ConnectDB()) {
        String query = "SELECT * FROM Issue WHERE NIM = ? AND BookID != ? AND status = 'issued'";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, nim);
        pst.setString(2, bookID);
        ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Issue(
                        rs.getString("nim"),
                        rs.getString("MahasiswaName"),
                        rs.getString("Year"),
                        rs.getString("Course"),
                        rs.getString("Email"),
                        rs.getString("BookID"),
                        rs.getString("BookName"),
                        rs.getString("Edition"),
                        rs.getString("Publisher"),
                        rs.getInt("Pages"),
                        rs.getString("IssueDate")
                );
            }
        }
        return null;
    }
    
    public static boolean updateIssueStatus(String nim, String bookID) throws SQLException {
    String query = "UPDATE Issue SET status = 'returned' WHERE NIM = ? AND BookID = ?";
    try (Connection conn = Connect.ConnectDB();
         PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, nim);
        pst.setString(2, bookID);
        int rowsUpdated = pst.executeUpdate();
        return rowsUpdated > 0;
    }
}
}
