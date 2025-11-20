package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.dto.OwnerSignupDto;
import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.model.User;
import dev.sijaja.serviceheft.model.enums.UserRole;
import dev.sijaja.serviceheft.repository.OwnerRepository;
import dev.sijaja.serviceheft.repository.UserRepository;

@Service
public class OwnerService {

    @Autowired
    private final OwnerRepository repo;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public OwnerService(OwnerRepository repo, UserRepository userRepo, PasswordEncoder encoder) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public List<Owner> getAll() {
        return repo.findAll();
    }

    public Optional<Owner> get(int id) {
        return repo.findById(id);
    }

    public Owner save(Owner owner) {
        return repo.save(owner);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public Owner findByUserId(int id) {
        return repo.findByUserUserId(id).orElse(null);
    }

    public Owner registerOwner(OwnerSignupDto dto) {

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(UserRole.OWNER);

        user = userRepo.save(user);

        Owner owner = new Owner();
        owner.setUser(user);
        owner.setUserName(dto.getUserName());
        owner.setFirstName(dto.getFirstName());
        owner.setLastName(dto.getLastName());
        owner.setStreet(dto.getStreet());
        owner.setHouseNumber(dto.getHouseNumber());
        owner.setCity(dto.getCity());
        owner.setDateOfBirth(dto.getDateOfBirth());

        return repo.save(owner);
    }
}
