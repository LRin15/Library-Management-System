/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.model;

/**
 *
 * @author ASUS
 */
public class Issue {
    private String nim;
    private String mahasiswaName;
    private String year;
    private String course;
    private String email;
    private String bookID;
    private String bookName;
    private String edition;
    private String publisher;
    private int pages;
    private String  issueDate;

    public Issue(Book book, Mahasiswa mahasiswa, String issueDate) {
        this.nim = mahasiswa.getNim();
        this.mahasiswaName = mahasiswa.getName();
        this.year = mahasiswa.getYear();
        this.course = mahasiswa.getCourse();
        this.email = mahasiswa.getEmail();
        this.bookID = book.getBookID();
        this.bookName = book.getName();
        this.edition = book.getEdition();
        this.publisher = book.getPublisher();
        this.pages = book.getPages();
        this.issueDate = issueDate;
    }

    public Issue(String nim, String mahasiswaName, String year, String course, String email, String bookID, String bookName, String edition, String publisher, int pages, String issueDate) {
        this.nim = nim;
        this.mahasiswaName = mahasiswaName;
        this.year = year;
        this.course = course;
        this.email = email;
        this.bookID = bookID;
        this.bookName = bookName;
        this.edition = edition;
        this.publisher = publisher;
        this.pages = pages;
        this.issueDate = issueDate;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getMahasiswaName() {
        return mahasiswaName;
    }

    public void setMahasiswaName(String mahasiswaName) {
        this.mahasiswaName = mahasiswaName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

}
