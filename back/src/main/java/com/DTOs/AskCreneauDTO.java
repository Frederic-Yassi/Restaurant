package com.DTOs;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AskCreneauDTO {

    private int nombre ;
    private LocalDate date ;
    private List<String> creneauList ;

    AskCreneauDTO(){}

    public AskCreneauDTO(int nombre, LocalDate date) {
        this.nombre = nombre;
        this.date = date;
    }

    public AskCreneauDTO(int nombre, LocalDate date, List<String> creneauList) {
        this.nombre = nombre;
        this.date = date;
        this.creneauList = creneauList;
    }

    //getters and setters

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getCreneauList() {
        return creneauList;
    }

    public void setCreneauList(List<String> creneauList) {
        this.creneauList = creneauList;
    }

    @Override
    public String toString() {
        return "AskCreneauDTO{" +
                "nombre=" + nombre +
                ", date=" + date +
                ", creneauList=" + creneauList +
                '}';
    }
}
