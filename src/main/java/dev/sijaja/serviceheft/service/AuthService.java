package dev.sijaja.serviceheft.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.dto.AuthResponseDto;
import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.model.Workshop;
import dev.sijaja.serviceheft.repository.OwnerRepository;
import dev.sijaja.serviceheft.repository.WorkshopRepository;

@Service
public class AuthService {

    private final OwnerRepository ownerRepository;
    private final WorkshopRepository workshopRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(OwnerRepository ownerRepository,
            WorkshopRepository workshopRepository,
            PasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.workshopRepository = workshopRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponseDto login(String email, String password) {
        System.out.println(">>> AuthService.login called for email=" + email);
        Workshop workshop = workshopRepository.findByEmail(email).orElse(null);
        if (workshop != null && passwordEncoder.matches(password, workshop.getPassword())) {
            return new AuthResponseDto("workshop", null, workshop);
        }
        Owner owner = ownerRepository.findByEmail(email).orElse(null);
        if (owner != null && passwordEncoder.matches(password, owner.getPassword())) {
            return new AuthResponseDto("owner", owner, null);
        }
        throw new BadCredentialsException("Invalid email or password");
    }
}
