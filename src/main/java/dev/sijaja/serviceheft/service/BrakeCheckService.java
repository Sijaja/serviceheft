package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.BrakeCheck;
import dev.sijaja.serviceheft.repository.BrakeCheckRepository;

@Service
public class BrakeCheckService {
    private final BrakeCheckRepository repo;

    public BrakeCheckService(BrakeCheckRepository repo) {
        this.repo = repo;
    }

    public List<BrakeCheck> getAll() {
        return repo.findAll();
    }

    public Optional<BrakeCheck> get(Integer id) {
        return repo.findById(id);
    }

    public BrakeCheck save(BrakeCheck check) {
        return repo.save(check);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

}
