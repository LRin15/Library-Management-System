/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.model;

/**
 *
 * @author ASUS
 */
public class Return {
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
    private String returnDate;

    public Return(Issue issue, String returnDate) {
        this.nim = issue.getNim();
        this.mahasiswaName = issue.getMahasiswaName();
        this.year = issue.getYear();
        this.course = issue.getCourse();
        this.email = issue.getEmail();
        this.bookID = issue.getBookID();
        this.bookName = issue.getBookName();
        this.edition = issue.getEdition();
        this.publisher = issue.getPublisher();
        this.pages = issue.getPages();
        this.issueDate = issue.getIssueDate();
        this.returnDate = returnDate;
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
    
    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
