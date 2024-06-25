package com.entites;

import com.utils.Mappeur;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reservation {
    private int id ;
    private String nom ;
    private String prenom ;
    private String role="" ;
    private String email ="" ;
    private String numero  ;
    private String information = "";
    private LocalDateTime dateDebutReservation ;
    private LocalDateTime dateFinReservation ;

    private int nombrePersonnes ;
    private String tables = "" ;
    private LocalDateTime dateReservation ;

    public Reservation(){}


    public Reservation(String nom, String prenom, String role ,String email, String numero, String information, LocalDateTime dateDebutReservation, int nombrePersonnes , String tables) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role ;
        this.email = email;
        this.numero = numero;
        this.information = information;
        this.dateDebutReservation = dateDebutReservation.withSecond(0);
        this.dateFinReservation = dateDebutReservation.plusHours(2).withSecond(0);
        this.nombrePersonnes = nombrePersonnes;
        this.dateReservation = LocalDateTime.now() ;
        this.tables = tables ;
    }

    public Reservation(int id, String nom, String prenom, String role, String email, String numero, String information, LocalDateTime dateDebutReservation, LocalDateTime dateFinReservation, int nombrePersonnes, String tables, LocalDateTime dateReservation) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.email = email;
        this.numero = numero;
        this.information = information;
        this.dateDebutReservation = dateDebutReservation;
        this.dateFinReservation = dateFinReservation;
        this.nombrePersonnes = nombrePersonnes;
        this.tables = tables;
        this.dateReservation = dateReservation;
    }

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

    public int getNombrePersonnes() {
        return nombrePersonnes;
    }

    public void setNombrePersonnes(int nombrePersonnes) {
        this.nombrePersonnes = nombrePersonnes;
    }

    public String getTables() {
        return tables;
    }

    public void setTables(String tables) {
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
                ", nombrePersonnes=" + nombrePersonnes +
                ", tables='" + tables + '\'' +
                ", dateReservation=" + dateReservation +
                '}';
    }
}
