package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.repository.OwnerRepository;

@Service
public class OwnerService {
    @Autowired
    private final OwnerRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public Owner registerOwner(Owner owner) {
        String encodedPassword = passwordEncoder.encode(owner.getPassword());
        owner.setPassword(encodedPassword);
        return repo.save(owner);
    }
    public OwnerService(OwnerRepository repo) { this.repo = repo; }
    public List<Owner> getAll() { return repo.findAll(); }
    public Optional<Owner> get(Integer id) { return repo.findById(id); }
    public Owner save(Owner owner) {
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        return repo.save(owner);
    }
    public void delete(Integer id) { repo.deleteById(id); }
}
