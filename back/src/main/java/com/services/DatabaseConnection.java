package com.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements Database {
    private  final String URL = "jdbc:mysql://localhost:3306/restaurant";
    private  final String USER = "root";
    private  final String PASSWORD = "12345678";

    public DatabaseConnection(){}

    public  Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

