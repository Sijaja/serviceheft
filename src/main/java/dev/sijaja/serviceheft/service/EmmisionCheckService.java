package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.EmmisionCheck;
import dev.sijaja.serviceheft.repository.EmmisionCheckRepository;

@Service
public class EmmisionCheckService {
    private final EmmisionCheckRepository repo;

    public EmmisionCheckService(EmmisionCheckRepository repo) {
        this.repo = repo;
    }

    public List<EmmisionCheck> getAll() {
        return repo.findAll();
    }

    public Optional<EmmisionCheck> get(Integer id) {
        return repo.findById(id);
    }

    public EmmisionCheck save(EmmisionCheck check) {
        return repo.save(check);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
