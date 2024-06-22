/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.controller;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import librarymanagementsystem.model.Book;
import librarymanagementsystem.model.BookDAO;
import librarymanagementsystem.model.Issue;
import librarymanagementsystem.model.IssueDAO;
import librarymanagementsystem.model.Mahasiswa;
import librarymanagementsystem.model.MahasiswaDAO;
import librarymanagementsystem.view.IssueBook;
/**
 *
 * @author ASUS
 */
public class IssueBookController {
    public static void searchBook(String bookID, IssueBook view) {
        try {
            Book book = BookDAO.getBookById(bookID);
            if (book != null) {
                view.displayBook(book);
            } else {
                JOptionPane.showMessageDialog(view, "Buku dengan ID tersebut tidak ditemukan", "Buku Tidak Ditemukan", JOptionPane.WARNING_MESSAGE);
                view.clearBookFields();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void searchMahasiswa(String nim, IssueBook view) {
        try {
            Mahasiswa mahasiswa = MahasiswaDAO.getMahasiswaByNIM(nim);
            if (mahasiswa != null) {
                view.displayMahasiswa(mahasiswa);
            } else {
                JOptionPane.showMessageDialog(view, "Mahasiswa dengan NIM tersebut tidak ditemukan", "Mahasiswa Tidak Ditemukan", JOptionPane.WARNING_MESSAGE);
                view.clearMahasiswaFields();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void issueBook(String bookID, String NIM, String issueDate, IssueBook view) {
        try {
            if (bookID.isEmpty() || NIM.isEmpty() || issueDate.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Semua field harus diisi.", "Input Tidak Lengkap", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Book book = BookDAO.getBookById(bookID);
            if (book == null) {
                JOptionPane.showMessageDialog(view, "Buku dengan ID tersebut tidak ditemukan.", "Buku Tidak Ditemukan", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Mahasiswa mahasiswa = MahasiswaDAO.getMahasiswaByNIM(NIM);
            if (mahasiswa == null) {
                JOptionPane.showMessageDialog(view, "Mahasiswa dengan NIM tersebut tidak ditemukan.", "Mahasiswa Tidak Ditemukan", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Issue issue = new Issue(book, mahasiswa, issueDate);
            IssueDAO issueDAO = new IssueDAO();
            if (issueDAO.issueDAO(issue)) {
                JOptionPane.showMessageDialog(view, "Buku berhasil dipinjam.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                view.clearAllFields();
            } else {
                JOptionPane.showMessageDialog(view, "Gagal meminjam buku.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
