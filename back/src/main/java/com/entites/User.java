package com.entites;

public class User {

    private int id ;
    private String pseudo ;
    private String motdepasse;
    private String nom= null ;
    private String prenom = null ;
    private String role ;

    public User(String pseudo, String motdepasse, String nom, String prenom , String role) {
        this.pseudo = pseudo;
        this.motdepasse = motdepasse;
        this.nom = nom;
        this.prenom = prenom;
        this.role=role ;
    }

    public User() {

    }

    public User(int id, String pseudo, String motdepasse, String nom, String prenom, String role) {
        this.id = id;
        this.pseudo = pseudo;
        this.motdepasse = motdepasse;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
    }
    // getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return motdepasse;
    }

    public void setPassword(String password) {
        this.motdepasse = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + pseudo + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
