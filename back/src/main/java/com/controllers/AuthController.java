package com.controllers;

import com.DTOs.ResponseAuth;
import com.DTOs.UserDTO;
import com.entites.User;
import com.enums.EnumRole;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.DatabaseConnection;
import com.services.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import org.mindrot.jbcrypt.BCrypt;


public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private static final UserService userService = new  UserService(new DatabaseConnection());

    private static BCrypt passwordService = new BCrypt() ;

    public AuthController(){}

    // Methode for endpoint /api/auth/createAdmin
     public static class createAdminHandler implements HttpHandler {


        @Override
        public void handle(HttpExchange exchange) throws IOException {
            logger.info("New request on /api/auth/createAdmin");
            if ("POST".equals(exchange.getRequestMethod())) {
                try {
                    InputStream is = exchange.getRequestBody();
                    ObjectMapper objectMapper = new ObjectMapper();
                    UserDTO userDTO = objectMapper.readValue(is, UserDTO.class);

                    User user = userService.getUserByUsername(userDTO.getUsername());
                    String responseJson ;
                    ResponseAuth response ;

                    if(user!= null){
                        //user exist
                        response = new ResponseAuth(userDTO.getUsername(), "ERROR");
                        responseJson = objectMapper.writeValueAsString(response);

                        exchange.getResponseHeaders().set("Content-Type", "application/json");
                        exchange.sendResponseHeaders(200, responseJson.getBytes().length);


                        OutputStream os = exchange.getResponseBody();
                        os.write(responseJson.getBytes());
                        os.close();
                    }
                    else {
                        userService.insertUser(
                                new User(userDTO.getUsername(), passwordService.hashpw(userDTO.getPassword(), BCrypt.gensalt()) , userDTO.getSurname(),userDTO.getName(), EnumRole.ADMIN.getRole() )
                                );
                        response = new ResponseAuth(userDTO.getUsername(), "OK" );
                        responseJson = objectMapper.writeValueAsString(response);

                        // Configurer la réponse HTTP
                        exchange.getResponseHeaders().set("Content-Type", "application/json");
                        exchange.sendResponseHeaders(200, responseJson.getBytes().length);

                        // Écrire la réponse JSON dans le corps de la réponse
                        OutputStream os = exchange.getResponseBody();
                        os.write(responseJson.getBytes());
                        os.close();
                    }

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

    // Methode for endpoint /api/auth/login
    public static class loginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            logger.info("New request on /api/auth/login");
            if ("POST".equals(exchange.getRequestMethod())) {

                try {
                    InputStream is = exchange.getRequestBody();
                    ObjectMapper objectMapper = new ObjectMapper();
                    UserDTO userDTO = objectMapper.readValue(is, UserDTO.class);

                    User user = userService.getUserByUsername(userDTO.getUsername());
                    String responseJson ;
                    ResponseAuth response ;

                    if(user!= null && passwordService.checkpw(userDTO.getPassword(), user.getPassword())){
                        response = new ResponseAuth(userDTO.getUsername(), "Bearer " + JwtUtil.generateToken(userDTO.getUsername()));
                        logger.info("User created");
                    }
                    else {
                        //user exist
                        response = new ResponseAuth(userDTO.getUsername(), "" );
                        logger.info("An user exist with the same username");
                    }
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
            } else {
                logger.info("Method request Not allowed");
                exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
            }
        }

    }

}
