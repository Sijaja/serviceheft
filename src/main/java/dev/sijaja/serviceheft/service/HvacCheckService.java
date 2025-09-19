package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.HvacCheck;
import dev.sijaja.serviceheft.repository.HvacCheckRepository;

@Service
public class HvacCheckService {
    private final HvacCheckRepository repo;

    public HvacCheckService(HvacCheckRepository repo) {
        this.repo = repo;
    }

    public List<HvacCheck> getAll() {
        return repo.findAll();
    }

    public Optional<HvacCheck> get(Integer id) {
        return repo.findById(id);
    }

    public HvacCheck save(HvacCheck check) {
        return repo.save(check);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
