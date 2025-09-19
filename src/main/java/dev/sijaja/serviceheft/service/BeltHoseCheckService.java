package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.BeltHoseCheck;
import dev.sijaja.serviceheft.repository.BeltHoseCheckRepository;

@Service
public class BeltHoseCheckService {
    private final BeltHoseCheckRepository repo;

    public BeltHoseCheckService(BeltHoseCheckRepository repo) {
        this.repo = repo;
    }

    public List<BeltHoseCheck> getAll() {
        return repo.findAll();
    }

    public Optional<BeltHoseCheck> get(Integer id) {
        return repo.findById(id);
    }

    public BeltHoseCheck save(BeltHoseCheck check) {
        return repo.save(check);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

}
