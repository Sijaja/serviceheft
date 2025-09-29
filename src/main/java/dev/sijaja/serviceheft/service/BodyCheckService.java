package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.BodyCheck;
import dev.sijaja.serviceheft.repository.BodyCheckRepository;

@Service
public class BodyCheckService {
    private final BodyCheckRepository repo;
    public BodyCheckService(BodyCheckRepository repo) {
        this.repo = repo;
    }

    public List<BodyCheck> getAll() {
        return repo.findAll();
    }

    public Optional<BodyCheck> get(Integer id) {
        return repo.findById(id);
    }

    public BodyCheck save(BodyCheck check) {
        return repo.save(check);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
