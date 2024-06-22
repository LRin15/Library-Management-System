/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.controller;
import javax.swing.JOptionPane;
import librarymanagementsystem.view.AddMahasiswa;
import librarymanagementsystem.model.Mahasiswa;
import librarymanagementsystem.model.MahasiswaDAO;
/**
 *
 * @author ASUS
 */
public class AddMahasiswaController {
    private AddMahasiswa view;

    public AddMahasiswaController(AddMahasiswa view) {
        this.view = view;
    }

    public void registerMahasiswa() {
        String nim = view.getNim();
        String name = view.getName();
        String year = view.getYear();
        String course = view.getCourse();
        String email = view.getEmail();
        
        if (!isValidNim(nim)) {
            JOptionPane.showMessageDialog(view, "NIM harus berupa 9 digit angka.", "Error", JOptionPane.ERROR_MESSAGE);
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

        Mahasiswa mahasiswa = new Mahasiswa(nim, name, year, course, email);
        MahasiswaDAO mahasiswaDAO = new MahasiswaDAO();

        if (mahasiswaDAO.addMahasiswa(mahasiswa)) {
            JOptionPane.showMessageDialog(view, "Mahasiswa berhasil didaftarkan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            view.clearFields();
        } else {
            JOptionPane.showMessageDialog(view, "Gagal mendaftarkan mahasiswa.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean isValidNim(String nim) {
        return nim.matches("\\d{9}");
    }
    
    private boolean isValidName(String name) {
        return name.length() <= 50;
    }
    
    private boolean isValidEmail(String email) {
        return email.contains("@");
    }
}
