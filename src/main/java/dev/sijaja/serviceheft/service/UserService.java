package dev.sijaja.serviceheft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.User;
import dev.sijaja.serviceheft.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository repo;
    public UserService(UserRepository repo) {
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repo.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));

        String role = switch (user.getRole()) {
            case OWNER -> "OWNER";
            case WORKSHOP -> "WORKSHOP";
        };

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(role)
                .build();      
    }

    public User loadUserByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
