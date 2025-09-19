package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.TireCheck;
import dev.sijaja.serviceheft.repository.TireCheckRepository;

@Service
public class TireCheckService {
    private final TireCheckRepository repo;

    public TireCheckService(TireCheckRepository repo) {
        this.repo = repo;
    }

    public List<TireCheck> getAll() {
        return repo.findAll();
    }

    public Optional<TireCheck> get(Integer id) {
        return repo.findById(id);
    }

    public TireCheck save(TireCheck check) {
        return repo.save(check);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
