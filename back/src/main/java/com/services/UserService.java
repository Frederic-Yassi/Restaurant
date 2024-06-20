package com.services;

import com.entites.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {


    private static final String InsertQuery = "INSERT INTO users (pseudo,nom,prenom,motDePasse,role) VALUES (?, ? , ? , ? , ?)";

    private static final String SelectUsersQuery = "SELECT * FROM users";
    private static final String SelectUserQueryByPseudo = "SELECT * FROM users WHERE pseudo = ? ";


    public void insertUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnection.getConnection();
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(SelectUserQueryByPseudo);
            preparedStatement.setString(1, pseudo);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("pseudo");
                String email = resultSet.getString("motDePasse");
                user = new User(username, email);
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

        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(SelectUsersQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("pseudo");
                String email = resultSet.getString("motDePasse");
                User user = new User(username, email);
                userList.add(user);
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
