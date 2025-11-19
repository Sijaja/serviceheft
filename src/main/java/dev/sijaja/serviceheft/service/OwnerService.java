package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.repository.OwnerRepository;

@Service
public class OwnerService implements UserDetailsService {

    @Autowired
    private final OwnerRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Owner owner = repo.findByEmail(email).orElse(null);
        if (owner == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return User.builder()
                .username(owner.getEmail())
                .password(owner.getPassword())
                .roles("OWNER")
                .build();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Owner registerOwner(Owner owner) {
        String encodedPassword = passwordEncoder.encode(owner.getPassword());
        owner.setPassword(encodedPassword);
        return repo.save(owner);
    }

    public OwnerService(OwnerRepository repo) {
        this.repo = repo;
    }

    public List<Owner> getAll() {
        return repo.findAll();
    }

    public Optional<Owner> get(int id) {
        return repo.findById(id);
    }

    public Owner save(Owner owner) {
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        return repo.save(owner);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public Owner findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }
}
