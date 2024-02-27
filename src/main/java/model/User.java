package model;

import java.util.Date;

public class User {
    private int id;
    private String email;
    private String mot_de_passe;
    private Date date_creation;
    private String role;
    private int cin;

    public User() {
    }

    public User(int id, String email, String mot_de_passe, Date date_creation, String role, int cin) {
        this.id = id;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.date_creation = date_creation;
        this.role = role;
        this.cin = cin;
    }

    public User(String email, String mot_de_passe, Date date_creation, String role, int cin) {
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.date_creation = date_creation;
        this.role = role;
        this.cin = cin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", date_creation=" + date_creation +
                ", role='" + role + '\'' +
                ", cin=" + cin +
                '}';
    }
}