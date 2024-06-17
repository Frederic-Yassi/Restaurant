package com.projetetudiant.demo.Enum;

public enum EnumRole {

    ADMIN("Admin"),
    ETUDIANT("Etudiant"),
    ENSEIGNANT("Enseignant");

    private String role ;

    // Constructeur
    EnumRole(String role) {
        this.role = role ;
    }

    // Méthode pour obtenir la description
    public String getRole() {
        return role ;
    }

}
