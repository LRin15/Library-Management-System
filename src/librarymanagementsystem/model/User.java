/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.model;

/**
 *
 * @author ASUS
 */
public class User {
    private String nip;
    private String name;
    private String email;
    private String password;

    public User(String nip, String name, String email, String password) {
        this.nip = nip;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
      
}
