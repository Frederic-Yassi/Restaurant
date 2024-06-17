package com.projetetudiant.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mail;
    private String username ;
    private String role ;
    private String password;

    public User() {
    }

    public User(int id, String mail, String username, String role, String password) {
        this.id = id;
        this.mail = mail;
        this.username = username;
        this.role = role;
        this.password = password;
    }


    public User(String username, String password, String role, String mail) {
        this.username = username ;
        this.password = password ;
        this.role = role ;
        this.mail = mail ;
    }

    public User(String username, String password, String role) {
        this.username = username ;
        this.password = password ;
        this.role = role ;
    }

    // Getters et setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setPseudo(String pseudo) {
        this.username = pseudo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", pseudo='" + username + '\'' +
                ", role='" + role + '\'' +
                ", mdp='" + password + '\'' +
                '}';
    }
}