package com.projetetudiant.demo.controllers;

import com.projetetudiant.demo.DTOs.UserDTO;
import com.projetetudiant.demo.Enum.EnumRole;
import com.projetetudiant.demo.Exceptions.DBException;
import com.projetetudiant.demo.Utils.JwtTokenUtil;
import com.projetetudiant.demo.Utils.MapstringToRoleEnum;
import com.projetetudiant.demo.Utils.PasswordGenerator;
import com.projetetudiant.demo.entities.User;
import com.projetetudiant.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthentification {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordService ;

    private static final Logger logger = LoggerFactory.getLogger(ApiAuthentification.class);

    @PostMapping(value = "/createAdmin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createAdmin(@RequestBody UserDTO userDTO) {

        try{
            if (!userService.existsUser(userDTO.getUsername())){
                User user = new User(userDTO.getUsername(), passwordService.encode(userDTO.getPassword()) , EnumRole.ADMIN.getRole());
                userService.createUser(user);
                return ResponseEntity.status(HttpStatus.CREATED).body("Créé avec succès");
            }
            else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du traitement de la requête");
            }
        }
        catch (Exception e){
            logger.error("Impossible de creer un administrateur");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du traitement de la requête");
        }

    }


    @PostMapping(value = "/createAccount", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createAccount(@RequestBody UserDTO userDTO) {

        try {
            if (!userService.existsUser(userDTO.getUsername())) {
                String password = PasswordGenerator.generatePassword(12);
                System.out.println("mail :" + userDTO.getMail());
                System.out.println("mot de passe :" + password);
                User user = new User(userDTO.getUsername(), passwordService.encode(password), MapstringToRoleEnum.map(userDTO.getRole()), userDTO.getMail());
                userService.createUser(user);
                return ResponseEntity.status(HttpStatus.CREATED).body("Créé avec succès");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du traitement de la requête");
            }
        }
        catch(Exception e){
            logger.error("Impossible de creer un compte");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du traitement de la requête");
        }
    }


    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity< Map<String, String> > login(@RequestBody UserDTO userDTO) {

        Map<String, String> response = new HashMap<>();
        response.put("username",userDTO.getUsername());
        try{
            User user = userService.getUserByUsername(userDTO.getUsername());

            if ( user!=null && passwordService.matches(userDTO.getPassword() ,user.getPassword() )){
                String token = JwtTokenUtil.generateToken(user);
                response.put("token", token);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.put("token","");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
        catch(Exception e){
            response.put("token","");
            logger.error("Impossible de creer un compte");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
