package com.entites;

import java.time.LocalDateTime;
import java.util.List;

public class Reservation {
    private int id ;
    private String nom ;
    private String prenom ;
    private String role ;
    private String email ;
    private String numero ;
    private String information ;
    private LocalDateTime dateDebutReservation ;
    private LocalDateTime dateFinReservation ;
    private int nombre ;
    private List<Integer> tables ;
    private LocalDateTime dateReservation ;





    //getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public LocalDateTime getDateDebutReservation() {
        return dateDebutReservation;
    }

    public void setDateDebutReservation(LocalDateTime dateDebutReservation) {
        this.dateDebutReservation = dateDebutReservation;
    }

    public LocalDateTime getDateFinReservation() {
        return dateFinReservation;
    }

    public void setDateFinReservation(LocalDateTime dateFinReservation) {
        this.dateFinReservation = dateFinReservation;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public List<Integer> getTables() {
        return tables;
    }

    public void setTables(List<Integer> tables) {
        this.tables = tables;
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", numero='" + numero + '\'' +
                ", information='" + information + '\'' +
                ", dateDebutReservation=" + dateDebutReservation +
                ", dateFinReservation=" + dateFinReservation +
                ", nombre=" + nombre +
                ", tables=" + tables +
                ", dateReservation=" + dateReservation +
                '}';
    }
}
