/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.controller;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import librarymanagementsystem.model.Issue;
import librarymanagementsystem.model.IssueDAO;
import librarymanagementsystem.model.Return;
import librarymanagementsystem.model.ReturnDAO;
import librarymanagementsystem.view.ReturnBook;

/**
 *
 * @author ASUS
 */
public class ReturnBookController {
    public static void searchIssue(String nim, ReturnBook view) {
        try {
            Issue issue = IssueDAO.getIssueByNIM(nim);
            if (issue != null) {
                view.displayissue(issue);
            } else {
                JOptionPane.showMessageDialog(view, "Issue dengan NIM tersebut tidak ditemukan", "Issue Tidak Ditemukan", JOptionPane.WARNING_MESSAGE);
                view.clearFields();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void searchNextIssue(String nim, String bookID, ReturnBook view) {
        try {
            Issue issue = IssueDAO.getIssueByNIMandID(nim,bookID);
            if (issue != null) {
                view.displayissue(issue);
            } else {
                JOptionPane.showMessageDialog(view, "Tidak ada buku lain yang dipinjam oleh mahasiswa dengan NIM tersebut", "Data Tidak Ditemukan", JOptionPane.WARNING_MESSAGE);
                view.clearFields();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void returnBook(String NIM, String returnDate, ReturnBook view) {
        try {
            if (NIM.isEmpty() || returnDate.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Semua field harus diisi.", "Input Tidak Lengkap", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Issue issue = IssueDAO.getIssueByNIM(NIM);
            if (issue == null) {
                JOptionPane.showMessageDialog(view, "Mahasiswa dengan NIM tersebut tidak ditemukan.", "Mahasiswa Tidak Ditemukan", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Return returnbook = new Return(issue, returnDate);
            ReturnDAO returnDAO = new ReturnDAO();
            if (returnDAO.returnDAO(returnbook)) {
                boolean updateStatus = IssueDAO.updateIssueStatus(NIM, issue.getBookID());
                    if(updateStatus) {
                    JOptionPane.showMessageDialog(view, "Buku berhasil dikembalikan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    view.clearFields();
                } else {
                    JOptionPane.showMessageDialog(view, "Gagal mengembalikan buku.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(view, "Gagal mengembalikan buku.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
