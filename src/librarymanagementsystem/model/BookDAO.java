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
public class BookDAO {
    public boolean addBook(Book book) {
        String query = "INSERT INTO Book (BookID, Name, Edition, Publisher, Pages) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Connect.ConnectDB();
            PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, book.getBookID());
            pst.setString(2, book.getName());
            pst.setString(3, book.getEdition());
            pst.setString(4, book.getPublisher());
            pst.setInt(5, book.getPages());

            int rowsInserted = pst.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static Book getBookById(String bookID) throws SQLException {
        String query = "SELECT * FROM Book WHERE BookID = ?";

        try (Connection conn = Connect.ConnectDB();
            PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, bookID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Book(
                        bookID,
                        rs.getString("Name"),
                        rs.getString("Edition"),
                        rs.getString("Publisher"),
                        rs.getInt("Pages")
                );
            }
        }
        return null;
    }
}
