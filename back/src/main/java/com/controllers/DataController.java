package com.controllers;

import com.DTOs.ResponseAuth;
import com.DTOs.UserDTO;
import com.entites.User;
import com.enums.EnumRole;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.DatabaseConnection;
import com.services.ReservationService;
import com.services.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.utils.JwtUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;


public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    private static final UserService userService = new UserService(new DatabaseConnection());


    private static final ReservationService reservationService = new ReservationService();

    public DataController() {
    }

    // Methode for endpoint /api/data/getCreneaux
    public static class createAdminHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            logger.info("New request on /api/auth/createAdmin");
            if ("POST".equals(exchange.getRequestMethod())) {
                String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);
                    if (JwtUtil.validateToken(token)) {
                        String response = "{\"message\":\"Access granted\"}";
                        exchange.getResponseHeaders().set("Content-Type", "application/json");
                        exchange.sendResponseHeaders(200, response.length());
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    } else {
                        String response = "{\"error\":\"Invalid or expired token\"}";
                        exchange.sendResponseHeaders(401, response.length());
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }
                } else {
                    logger.info("Method request Not allowed");
                    exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
                }
            }
        }

    }

}
