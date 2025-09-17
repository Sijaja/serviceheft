package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.repository.OwnerRepository;

@Service
public class OwnerService {
    private final OwnerRepository repo;
    public OwnerService(OwnerRepository repo) { this.repo = repo; }
    public List<Owner> getAll() { return repo.findAll(); }
    public Optional<Owner> get(Integer id) { return repo.findById(id); }
    public Owner save(Owner owner) { return repo.save(owner); }
    public void delete(Integer id) { repo.deleteById(id); }
}
