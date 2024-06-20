package com.DTOs;

public class ResponseAuth {
    private String username ;
    private String value;

    public ResponseAuth(){}


    public ResponseAuth(String username, String value) {
        this.username = username;
        this.value = value;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ResponseAuth{" +
                "username='" + username + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
