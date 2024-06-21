package com.utils ;
import com.services.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseConnectionTest implements Database {
    
    private static final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "";

    public DatabaseConnectionTest(){}


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public void initializeDatabase() {
        try {
            Connection connection = this.getConnection() ;
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE `users` (\n" +
                    "  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                    "  `pseudo` varchar(100) NOT NULL,\n" +
                    "  `motDePasse` text NOT NULL,\n" +
                    "  `nom` varchar(100) DEFAULT NULL,\n" +
                    "  `prenom` varchar(100) DEFAULT NULL,\n" +
                    "  `role` varchar(100) NOT NULL\n" +
                    ");\n";

            String query1 = "CREATE TABLE `reservations` (\\n\" +\n" +
                    "                    \"  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,\\n\" +\n" +
                    "                    \"  `nom` varchar(100) NOT NULL,\\n\" +\n" +
                    "                    \"  `prenom` varchar(100) NOT NULL,\\n\" +\n" +
                    "                    \"  `role` varchar(100) NOT NULL,\\n\" +\n" +
                    "                    \"  `email` varchar(100) DEFAULT NULL,\\n\" +\n" +
                    "                    \"  `numero` varchar(100) NOT NULL,\\n\" +\n" +
                    "                    \"  `information` text,\\n\" +\n" +
                    "                    \"  `dateDebutReservation` datetime NOT NULL,\\n\" +\n" +
                    "                    \"  `dateFinReservation` datetime NOT NULL,\\n\" +\n" +
                    "                    \"  `nombre` int NOT NULL,\\n\" +\n" +
                    "                    \"  `table` int NOT NULL,\\n\" +\n" +
                    "                    \"  `dateReservation` datetime NOT NULL,\\n\" +\n" +
                    "                    \"  PRIMARY KEY (`id`)\\n\" +\n" +
                    "                    \") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;" ;

            
            // Créer des tables
            statement.execute(query);
            //statement.execute(query1);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}