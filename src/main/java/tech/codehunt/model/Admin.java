package tech.codehunt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sn;
    @Column(unique = true)
    private String username;
    private String password;

    // No-argument constructor
    public Admin() {
    }

    // All-argument constructor
    public Admin(int sn, String username, String password) {
        this.sn = sn;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString() method
    @Override
    public String toString() {
        return "Admin{" +
                "sn=" + sn +
                ", username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}
