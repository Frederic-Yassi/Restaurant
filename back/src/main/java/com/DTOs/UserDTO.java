package com.DTOs;

public class UserDTO {

    private String username ;
    private String password ;
    private String name ;
    private String surname ;

    //constructors

    UserDTO(){}

    public UserDTO(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //getters and setters


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nom='" + surname + '\'' +
                ", prenom='" + name + '\'' +
                '}';
    }
}
