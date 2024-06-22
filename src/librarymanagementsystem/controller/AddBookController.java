/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.controller;

import javax.swing.JOptionPane;
import librarymanagementsystem.model.Book;
import librarymanagementsystem.model.BookDAO;
import librarymanagementsystem.view.AddBook;

/**
 *
 * @author ASUS
 */
public class AddBookController {
    private AddBook view;

    public AddBookController(AddBook view) {
        this.view = view;
    }

    public void addBook() {
        String bookID = view.getBookID();
        String name = view.getName();
        String edition = view.getEdition();
        String publisher = view.getPublisher();
        int pages = view.getPages();

        if (!isValidBookID(bookID)) {
            JOptionPane.showMessageDialog(view, "Book ID harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidName(name)) {
            JOptionPane.showMessageDialog(view, "Nama buku harus terdiri dari maksimal 50 karakter.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        if (!isValidPublisher(publisher)) {
            JOptionPane.showMessageDialog(view, "Penerbit harus terdiri dari maksimal 50 karakter.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidPages(pages)) {
            JOptionPane.showMessageDialog(view, "Jumlah halaman harus lebih besar dari 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Book book = new Book(bookID, name, edition, publisher, pages);
        BookDAO bookDAO = new BookDAO();

        if (bookDAO.addBook(book)) {
            JOptionPane.showMessageDialog(view, "Buku berhasil ditambahkan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            view.clearFields();
        } else {
            JOptionPane.showMessageDialog(view, "Gagal menambahkan buku.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean isValidBookID(String bookID) {
        return bookID.matches("\\d+");
    }
    
    private boolean isValidName(String name) {
        return name.length() <= 50;
    }
    
    private boolean isValidPublisher(String publisher) {
        return publisher.length() <= 50;
    }
    
    private boolean isValidPages(int pages) {
        return pages > 0;
    }
}
