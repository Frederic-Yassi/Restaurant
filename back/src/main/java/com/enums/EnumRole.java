package com.enums;

public enum EnumRole {

    ADMIN("Admin"),
    CLIENT("Client");

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
