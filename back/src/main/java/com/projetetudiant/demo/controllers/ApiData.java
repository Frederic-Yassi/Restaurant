package com.projetetudiant.demo.controllers;

import com.projetetudiant.demo.entities.User;
import com.projetetudiant.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class ApiData {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ApiData.class);

    @GetMapping(value = "/profile", produces = "application/json")
    public ResponseEntity< Map<String, Object> > getProfile(@RequestParam Map<String, String> queryParams) {


        Map<String, Object> response = new HashMap<>();
        try {
            User user = userService.getUserByUsername(queryParams.get("username"));
            response.put("username", user.getUsername());
            response.put("mail", user.getMail());
            response.put("role", user.getRole());
            //response.put("queryParams", queryParams);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}
