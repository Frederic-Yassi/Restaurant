package com.services;

import com.entites.Reservation;
import com.entites.User;
import com.utils.Constantes;
import com.utils.Mappeur;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationService {

    private static final String CountTableAvailableQuery = "SELECT SUM(CEIL(nombrePersonnes/2)) as result  FROM reservations  WHERE ? BETWEEN dateDebutReservation  AND dateFinReservation ;" ;

    private static final String SelectTableUsedQuery = "SELECT tables FROM reservations  WHERE ? BETWEEN dateDebutReservation  AND dateFinReservation ;" ;

    private static final String InsertQuery ="INSERT INTO reservations\n" +
            "(nom, prenom, `role`, email, numero, information, dateDebutReservation, dateFinReservation, nombrePersonnes, tables, dateReservation)\n" +
            "VALUES( ? , ? , ? , ? , ? , ?, ?, ?, ?, ?, ?);" ;

    private static final String SelectReservationsQuery = "SELECT * FROM reservations";

    private Database databaseConnection ;

    public ReservationService(Database databaseConnection) {
        this.databaseConnection = databaseConnection ;
    }

    public ArrayList<String> getCreneauxAvailable(LocalDate date , int numberOfPerson) {
        ArrayList<String> creneauList = new ArrayList<>() ;
        PreparedStatement preparedStatement = null;
        Connection connection = null ;

        try {
            connection =  databaseConnection.getConnection() ;
            for(LocalTime cr : Constantes.Creneaux){
                preparedStatement = connection.prepareStatement(CountTableAvailableQuery);

                LocalDateTime dateTime = date.atTime(cr);
                preparedStatement.setString(1, dateTime.toString());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Optional<Integer> resultQuery = Optional.of(resultSet.getInt("result"));
                    if (!resultQuery.isPresent()) {
                        creneauList.add(cr.toString());
                    } else {
                        int numberOftablesUsed = resultQuery.get();
                        if (Constantes.NumberOftable - numberOftablesUsed >= Math.round(numberOfPerson)) {
                            creneauList.add(cr.toString());
                        }
                    }

                }
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

        return creneauList;
    }

    public void insertReservation(Reservation reservation) {
        Connection connection = null ;
        PreparedStatement preparedStatement = null;
        System.out.println(reservation);

        try {
            connection =  databaseConnection.getConnection() ;
            preparedStatement = connection.prepareStatement(InsertQuery);
            preparedStatement.setString(1, reservation.getNom());
            preparedStatement.setString(2, reservation.getPrenom());
            preparedStatement.setString(3, reservation.getRole());
            preparedStatement.setString(4, reservation.getEmail());
            preparedStatement.setString(5, reservation.getNumero());
            preparedStatement.setString(6, reservation.getInformation());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(reservation.getDateDebutReservation().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
            preparedStatement.setTimestamp(8, Timestamp.valueOf(reservation.getDateFinReservation().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
            preparedStatement.setInt(9, reservation.getNombrePersonnes());
            preparedStatement.setString(10, reservation.getTables());
            preparedStatement.setTimestamp(11, Timestamp.valueOf(reservation.getDateReservation().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));


            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected");

        } catch (Exception e) {
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

    public String getTablesAvailables(LocalDateTime dateTime,int numberTables) {
        ArrayList<Integer> tablesListAvailable = new ArrayList<>() ;
        ArrayList<Integer> tablesListUsed = getTablesUsed(dateTime) ;

        int counter = 0 ;
        for (int i = 1 ; i<= Constantes.NumberOftable ;i++) {
            if (!tablesListUsed.contains(i)) {
                //table available
                tablesListAvailable.add(i);
                counter++;
                if (counter == numberTables) {
                    break;
                }
            }
        }

        return Mappeur.mapListToString(tablesListAvailable) ;

    }

    public ArrayList<Integer> getTablesUsed(LocalDateTime dateTime) {
        ArrayList<Integer> tablesListUsed = new ArrayList<>() ;
        PreparedStatement preparedStatement = null;
        Connection connection = null ;

        try {
            connection = databaseConnection.getConnection()  ;
            preparedStatement = connection.prepareStatement(SelectTableUsedQuery);
            preparedStatement.setString(1, dateTime.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String tables = resultSet.getString("tables");
                ArrayList<Integer> tablesList = Mappeur.mapStringToList(tables);
                if(!tablesList.isEmpty()) {
                    tablesListUsed.addAll(tablesList);
                }

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

        return tablesListUsed ;

    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservationList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getConnection()  ;
            preparedStatement = connection.prepareStatement(SelectReservationsQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String role = resultSet.getString("role");
                String email = resultSet.getString("email");
                String numero = resultSet.getString("numero");
                String information = resultSet.getString("information");
                LocalDateTime dateDebutReservation = LocalDateTime.parse(resultSet.getString("dateDebutReservation"));
                LocalDateTime dateFinReservation = LocalDateTime.parse(resultSet.getString("dateFinReservation"));
                int nombrePersonnes = resultSet.getInt("nombrePersonnes");
                String tables = resultSet.getString("tables");
                LocalDateTime dateReservation = LocalDateTime.parse(resultSet.getString("dateReservation"));

                reservationList.add(new Reservation(id,nom,prenom,role,email,numero,information,dateDebutReservation,dateFinReservation,nombrePersonnes,tables,dateReservation));
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

        return reservationList;
    }


}
