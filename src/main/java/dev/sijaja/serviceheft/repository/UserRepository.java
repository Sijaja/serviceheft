package dev.sijaja.serviceheft.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sijaja.serviceheft.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    
}
