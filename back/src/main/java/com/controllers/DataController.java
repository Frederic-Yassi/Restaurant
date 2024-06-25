package com.controllers;

import com.DTOs.AskCreneauDTO;
import com.DTOs.ResponseAuth;
import com.DTOs.UserDTO;
import com.entites.Reservation;
import com.entites.User;
import com.enums.EnumRole;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.services.DatabaseConnection;
import com.services.ReservationService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.utils.JwtUtil;
import com.utils.LocalDateTimeDeserializer;
import com.utils.Mappeur;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    private static final ReservationService reservationService = new ReservationService(new DatabaseConnection());
    
    public DataController() {
    }

    // Methode for endpoint /api/data/getCreneaux
    public static class getCreneauxHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            logger.info("New request on /api/data/getCreneaux");
            if ("GET".equals(exchange.getRequestMethod())) {
                try {
                    InputStream is = exchange.getRequestBody();
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new JavaTimeModule());
                    AskCreneauDTO askCreneauDTO = objectMapper.readValue(is, AskCreneauDTO.class);


                    ArrayList<String> arrayList  = reservationService.getCreneauxAvailable(askCreneauDTO.getDate(),askCreneauDTO.getNombre());
                    String responseJson ;
                    AskCreneauDTO response ;


                    response = new AskCreneauDTO(askCreneauDTO.getNombre(), askCreneauDTO.getDate(),arrayList );
                    responseJson = objectMapper.writeValueAsString(response);

                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, responseJson.getBytes().length);


                    OutputStream os = exchange.getResponseBody();
                    os.write(responseJson.getBytes());
                    os.close();

                }
                catch (JsonParseException | JsonMappingException e){
                    logger.error("mapping Error : "+ e.getMessage() );
                    exchange.sendResponseHeaders(400, -1); // 400 Bad Request
                }

                catch (Exception e){
                    logger.error("Error during request treatment : "+e.getMessage() );
                    exchange.sendResponseHeaders(500, -1); // 500 Internal Server Error
                }
            }
            else {
                logger.info("Method request Not allowed");
                exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
            }
        }

    }

    // Methode for endpoint /api/data/makeReservation
    public static class makeReservationHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            logger.info("New request on /api/data/makeReservation");
            if ("POST".equals(exchange.getRequestMethod())) {

                try {
                    InputStream is = exchange.getRequestBody();
                    ObjectMapper objectMapper = new ObjectMapper();

                    SimpleModule module = new SimpleModule();
                    module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
                    objectMapper.registerModule(module);

                    Reservation reservation = objectMapper.readValue(is, Reservation.class);


                    reservation.setTables(reservationService.getTablesAvailables(reservation.getDateDebutReservation(), (int) Math.ceil((float)reservation.getNombrePersonnes()/2)));

                    reservationService.insertReservation(new Reservation(reservation.getNom(),reservation.getPrenom(),EnumRole.CLIENT.getRole(),reservation.getEmail(),reservation.getNumero(),reservation.getInformation(),reservation.getDateDebutReservation(),reservation.getNombrePersonnes(),reservation.getTables()));
                    logger.info("Reservation Succeed");

                    String response ="OK" ;


                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, response.getBytes().length);


                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
                catch (JsonParseException | JsonMappingException e){
                    logger.error("mapping Error : "+ e.getMessage() );
                    exchange.sendResponseHeaders(400, -1); // 400 Bad Request
                }

                catch (Exception e){
                    logger.error("Error during request treatment : "+e.getMessage() );
                    exchange.sendResponseHeaders(500, -1); // 500 Internal Server Error
                }
            } else {
                logger.info("Method request Not allowed");
                exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
            }
        }

    }

    // Methode for endpoint /api/data/makeReservationNow
    public static class makeReservationHandlerNow implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            logger.info("New request on /api/data/makeReservationNow");
            if ("POST".equals(exchange.getRequestMethod())) {

                try {
                    InputStream is = exchange.getRequestBody();
                    ObjectMapper objectMapper = new ObjectMapper();

                    SimpleModule module = new SimpleModule();
                    module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
                    objectMapper.registerModule(module);

                    Reservation reservation = objectMapper.readValue(is, Reservation.class);
                    reservation.setDateDebutReservation(LocalDateTime.now());

                    reservation.setTables(reservationService.getTablesAvailables(reservation.getDateDebutReservation(), (int) Math.ceil((float)reservation.getNombrePersonnes()/2)));

                    reservationService.insertReservation(new Reservation(reservation.getNom(),reservation.getPrenom(),EnumRole.CLIENT.getRole(),reservation.getEmail(),reservation.getNumero(),reservation.getInformation(),reservation.getDateDebutReservation(),reservation.getNombrePersonnes(),reservation.getTables()));
                    logger.info("Reservation Succeed");

                    String response ="OK" ;


                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, response.getBytes().length);


                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
                catch (JsonParseException | JsonMappingException e){
                    logger.error("mapping Error : "+ e.getMessage() );
                    exchange.sendResponseHeaders(400, -1); // 400 Bad Request
                }

                catch (Exception e){
                    logger.error("Error during request treatment : "+e.getMessage() );
                    exchange.sendResponseHeaders(500, -1); // 500 Internal Server Error
                }
            } else {
                logger.info("Method request Not allowed");
                exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
            }
        }

    }

    // Methode for endpoint /api/data/getTablesUsedNow
    public static class getTablesUsedNowHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            logger.info("New request on /api/data/getTablesUsedNow");
            if ("GET".equals(exchange.getRequestMethod())) {
                try {



                    ArrayList<Integer> response = reservationService.getTablesUsed(LocalDateTime.now());


                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonResponse = objectMapper.writeValueAsString(response);

                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);

                    OutputStream os = exchange.getResponseBody();
                    os.write(jsonResponse.getBytes());
                    os.close();
                    logger.info("Response OK");
                }

                catch (Exception e){
                    logger.error("Error during request treatment : "+e.getMessage() );
                    exchange.sendResponseHeaders(500, -1); // 500 Internal Server Error
                }
            } else {
                logger.info("Method request Not allowed");
                exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
            }
        }

    }
}
