package com.services ;

import com.entites.User;
import com.utils.DatabaseConnectionTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    DatabaseConnectionTest databaseConnectionTest = new DatabaseConnectionTest();
    private UserService userService = new UserService(databaseConnectionTest) ;


    @BeforeEach
    public void init() {
        // Initialiser la base de données avant chaque test
        databaseConnectionTest.initializeDatabase();
    }

    @AfterEach
    public void delete() {
        // Nettoyer la base de données après chaque test
        try {
            Connection connection = databaseConnectionTest.getConnection();
            Statement statement = connection.createStatement() ;
            statement.execute("DROP ALL OBJECTS DELETE FILES");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertUserTest() {
        User user = new User("pseudo123", "password123", "Nom", "Prenom", "ADMIN");
        userService.insertUser(user);

        try {
            Connection connection = databaseConnectionTest.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users") ;

            ResultSet resultSet = preparedStatement.executeQuery();
            assertTrue(resultSet.next());
            assertEquals(1, resultSet.getInt("id"));
            assertEquals("pseudo123", resultSet.getString("pseudo"));
            assertEquals("Nom", resultSet.getString("nom"));
            assertEquals("Prenom", resultSet.getString("prenom"));
            assertEquals( "password123" , resultSet.getString("motDePasse"));
            assertEquals("ADMIN", resultSet.getString("role"));
            assertFalse(resultSet.next());

            }
        catch (SQLException e) {
                e.printStackTrace();
        }
    }

    @Test
    public void getUserByUsernameTest() {
        User user = new User("pseudo123", "password123", "Nom", "Prenom", "ADMIN");

        User userNotInsert = userService.getUserByUsername(user.getPseudo());
        assertNull(userNotInsert);

        userService.insertUser(user);


        User userFromDatabase = userService.getUserByUsername(user.getPseudo());
        assertEquals(1,userFromDatabase.getId());
        assertEquals(user.getPseudo(), userFromDatabase.getPseudo());
        assertEquals(user.getNom(),userFromDatabase.getNom());
        assertEquals(user.getPrenom(), userFromDatabase.getPrenom());
        assertEquals( user.getPassword() , userFromDatabase.getPassword());
        assertEquals(user.getRole(), userFromDatabase.getRole());
    }


    @Test
    public void getAllUserTest(){
        User user1 = new User("pseudo123", "password123", "Nom", "Prenom", "ADMIN");
        User user2 = new User("pseudo1231", "password1232", "Nom3", "Prenom4", "ADMIN5");


        userService.insertUser(user1);
        userService.insertUser(user2);


        ArrayList<User> usersFromDatabase = (ArrayList<User>) userService.getAllUsers();
        assertEquals(usersFromDatabase.size(),2) ;

        User userFromDatabase1 = usersFromDatabase.get(0);
        User userFromDatabase2 = usersFromDatabase.get(1);


        assertEquals(1,userFromDatabase1.getId());
        assertEquals(user1.getPseudo(), userFromDatabase1.getPseudo());
        assertEquals(user1.getNom(),userFromDatabase1.getNom());
        assertEquals(user1.getPrenom(), userFromDatabase1.getPrenom());
        assertEquals( user1.getPassword() , userFromDatabase1.getPassword());
        assertEquals(user1.getRole(), userFromDatabase1.getRole());

        assertEquals(2,userFromDatabase2.getId());
        assertEquals(user2.getPseudo(), userFromDatabase2.getPseudo());
        assertEquals(user2.getNom(),userFromDatabase2.getNom());
        assertEquals(user2.getPrenom(), userFromDatabase2.getPrenom());
        assertEquals( user2.getPassword() , userFromDatabase2.getPassword());
        assertEquals(user2.getRole(), userFromDatabase2.getRole());


    }


}