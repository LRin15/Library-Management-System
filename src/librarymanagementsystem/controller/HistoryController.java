/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import librarymanagementsystem.model.Connect;
/**
 *
 * @author ASUS
 */
public class HistoryController {
    public void loadIssueData(DefaultTableModel model) {
        model.setRowCount(0); // clear existing rows
        try (Connection conn = Connect.ConnectDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT BookID, BookName, NIM, MahasiswaName, IssueDate FROM Issue")) {

            while (rs.next()) {
                Object[] row = {
                    rs.getString("BookID"),
                    rs.getString("BookName"),
                    rs.getString("NIM"),
                    rs.getString("MahasiswaName"),
                    rs.getString("IssueDate")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadReturnData(DefaultTableModel model) {
        model.setRowCount(0); // clear existing rows
        try (Connection conn = Connect.ConnectDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT BookID, BookName, NIM, MahasiswaName, IssueDate, ReturnDate FROM Return")) {

            while (rs.next()) {
                Object[] row = {
                    rs.getString("BookID"),
                    rs.getString("BookName"),
                    rs.getString("NIM"),
                    rs.getString("MahasiswaName"),
                    rs.getString("IssueDate"),
                    rs.getString("ReturnDate")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
