package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.ElectricCheck;
import dev.sijaja.serviceheft.repository.ElectricCheckRepository;

@Service
public class ElectricCheckService {
    private final ElectricCheckRepository repo;

    public ElectricCheckService(ElectricCheckRepository repo) {
        this.repo = repo;
    }

    public List<ElectricCheck> getAll() {
        return repo.findAll();
    }

    public Optional<ElectricCheck> get(Integer id) {
        return repo.findById(id);
    }

    public ElectricCheck save(ElectricCheck check) {
        return repo.save(check);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
