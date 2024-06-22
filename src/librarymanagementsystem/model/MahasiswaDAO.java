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
public class MahasiswaDAO {
     public boolean addMahasiswa(Mahasiswa mahasiswa) {
        String query = "INSERT INTO Mahasiswa (NIM, Name, Year, Course, Email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Connect.ConnectDB();
            PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, mahasiswa.getNim());
            pst.setString(2, mahasiswa.getName());
            pst.setString(3, mahasiswa.getYear());
            pst.setString(4, mahasiswa.getCourse());
            pst.setString(5, mahasiswa.getEmail());

            int rowsInserted = pst.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
     
    public static Mahasiswa getMahasiswaByNIM(String nim) throws SQLException {
        String query = "SELECT * FROM Mahasiswa WHERE NIM = ?";

        try (Connection conn = Connect.ConnectDB();
            PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nim);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Mahasiswa(
                        nim,
                        rs.getString("Name"),
                        rs.getString("Year"),
                        rs.getString("Course"),
                        rs.getString("Email")
                );
            }
        }
        return null;
    }
}
