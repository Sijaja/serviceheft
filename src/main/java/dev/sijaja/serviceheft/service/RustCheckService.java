package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.RustCheck;
import dev.sijaja.serviceheft.repository.RustCheckRepository;

@Service
public class RustCheckService {
    private final RustCheckRepository repo;
    public RustCheckService(RustCheckRepository repo) {
        this.repo = repo;
    }

        public List<RustCheck> getAll() {
        return repo.findAll();
    }

    public Optional<RustCheck> get(Integer id) {
        return repo.findById(id);
    }

    public RustCheck save(RustCheck check) {
        return repo.save(check);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
