package dev.sijaja.serviceheft.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.repository.OwnerRepository;

@Service
public class AuthService {
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(OwnerRepository ownerRepository, PasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Owner login(String email, String password) {
        Owner owner = ownerRepository.findByEmail(email);
        if (owner == null || !passwordEncoder.matches(password, owner.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }
        return owner;
    }
}
