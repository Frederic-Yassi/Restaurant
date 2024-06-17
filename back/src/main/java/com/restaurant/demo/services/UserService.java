package com.projetetudiant.demo.services;

import com.projetetudiant.demo.entities.User;
import com.projetetudiant.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public Boolean existsUser(String username){return userRepository.existsByUsername(username);}

    public User getUserByUsername(String username) {
        return  userRepository.findByUsername(username);
    }

    // Autres méthodes pour obtenir, mettre à jour, supprimer, etc.
}
