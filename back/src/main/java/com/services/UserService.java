package com.services;

import com.entites.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {


    private static final String InsertQuery = "INSERT INTO users (pseudo,nom,prenom,motDePasse,role) VALUES (?, ? , ? , ? , ?)";

    private static final String SelectUsersQuery = "SELECT * FROM users";
    private static final String SelectUserQueryByPseudo = "SELECT * FROM users WHERE pseudo = ? ";

    private final Database databaseConnection ;

    public UserService(Database databaseConnection){
        this.databaseConnection =  databaseConnection;
    }


    public void insertUser(User user) {
        Connection connection = null ;
        PreparedStatement preparedStatement = null;

        try {
            connection =  databaseConnection.getConnection() ;
            preparedStatement = connection.prepareStatement(InsertQuery);
            preparedStatement.setString(1, user.getPseudo());
            preparedStatement.setString(2, user.getNom());
            preparedStatement.setString(3, user.getPrenom());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected");

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        } finally {
            // Fermer les ressources ouvertes
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Gérer l'exception de fermeture
            }
        }
    }

    public User getUserByUsername(String pseudo) {
        User user = null ;
        PreparedStatement preparedStatement = null;
        Connection connection = null ;

        try {
            connection =  databaseConnection.getConnection() ;
            preparedStatement = connection.prepareStatement(SelectUserQueryByPseudo);
            preparedStatement.setString(1, pseudo);
             ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String motdepasse = resultSet.getString("motDePasse");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String role = resultSet.getString("role");
                user = new User(id,pseudo, motdepasse,nom,prenom,role);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        } finally {
            // Fermer les ressources ouvertes
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Gérer l'exception de fermeture
            }
        }

        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getConnection()  ;
            preparedStatement = connection.prepareStatement(SelectUsersQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String pseudo = resultSet.getString("pseudo");
                String motdepasse = resultSet.getString("motDePasse");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String role = resultSet.getString("role");
                userList.add(new User(id,pseudo, motdepasse,nom,prenom,role));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        } finally {
            // Fermer les ressources ouvertes
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Gérer l'exception de fermeture
            }
        }

        return userList;
    }

}
