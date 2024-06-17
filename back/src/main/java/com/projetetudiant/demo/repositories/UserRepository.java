package com.projetetudiant.demo.repositories;

import com.projetetudiant.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String pseudo, String pwd);
    Boolean existsByUsername(String pseudo);

    User findByUsername(String pseudo);

}
