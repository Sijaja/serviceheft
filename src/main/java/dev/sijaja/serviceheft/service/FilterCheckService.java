package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.FilterCheck;
import dev.sijaja.serviceheft.repository.FilterCheckRepository;

@Service
public class FilterCheckService {
    private final FilterCheckRepository repo;

    public FilterCheckService(FilterCheckRepository repo) {
        this.repo = repo;
    }

    public List<FilterCheck> getAll() {
        return repo.findAll();
    }

    public Optional<FilterCheck> get(Integer id) {
        return repo.findById(id);
    }

    public FilterCheck save(FilterCheck check) {
        return repo.save(check);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}