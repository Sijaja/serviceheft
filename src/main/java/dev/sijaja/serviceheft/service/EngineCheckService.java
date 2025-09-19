package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.EngineCheck;
import dev.sijaja.serviceheft.repository.EngineCheckRepository;

@Service
public class EngineCheckService {
    private final EngineCheckRepository repo;

    public EngineCheckService(EngineCheckRepository repo) {
        this.repo = repo;
    }

    public List<EngineCheck> getAll() {
        return repo.findAll();
    }

    public Optional<EngineCheck> get(Integer id) {
        return repo.findById(id);
    }

    public EngineCheck save(EngineCheck check) {
        return repo.save(check);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
