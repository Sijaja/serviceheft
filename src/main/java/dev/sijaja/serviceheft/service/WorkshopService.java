package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.Workshop;
import dev.sijaja.serviceheft.repository.WorkshopRepository;

@Service
public class WorkshopService {
    private final WorkshopRepository repo;
    public WorkshopService(WorkshopRepository repo) {
        this.repo = repo;
    }
    public List<Workshop> getAll() { return repo.findAll(); }
    public Optional<Workshop> get(Integer id) { return repo.findById(id); }
    public Workshop save(Workshop workshop) { return repo.save(workshop); }
    public void delete(Integer id) { repo.deleteById(id); }
}
