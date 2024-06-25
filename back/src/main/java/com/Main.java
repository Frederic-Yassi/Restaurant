package com ;

import com.controllers.AuthController;
import com.controllers.DataController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        //add route for authentication
        server.createContext("/api/auth/createAdmin", new AuthController.createAdminHandler());
        server.createContext("/api/auth/login", new AuthController.loginHandler());


        //add others routes for authentication
        server.createContext("/api/data/getCreneaux", new DataController.getCreneauxHandler());
        server.createContext("/api/data/makeReservation", new DataController.makeReservationHandler());
        server.createContext("/api/data/makeReservationNow", new DataController.makeReservationHandlerNow());
        server.createContext("/api/data/getTablesUsed", new DataController.getTablesUsedNowHandler());

        //start server
        server.start();
        System.out.println("Server is listening on port 8080");
    }
}
