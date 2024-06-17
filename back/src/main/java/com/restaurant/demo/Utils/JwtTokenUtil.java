package com.projetetudiant.demo.Utils;

import com.projetetudiant.demo.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;

public class JwtTokenUtil {

    public static final String SECRET_KEY = "secret"; // Clé secrète utilisée pour signer le token


    public static String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Durée de validité du token (10 jours)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static User parseToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        Claims body = claimsJws.getBody();

        // Extraire les informations de l'utilisateur des claims
        String username = body.getSubject(); // Nom d'utilisateur
        List<String> roles = body.get("roles", List.class); // Rôles de l'utilisateur

        // Créer et retourner un objet UserDetails
        return new User();
    }
}

