package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.Workshop;
import dev.sijaja.serviceheft.repository.WorkshopRepository;

@Service
public class WorkshopService {
    private final WorkshopRepository repo;
    public WorkshopService(WorkshopRepository repo) {
        this.repo = repo;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    public Workshop registerWorkshop(Workshop ws) {
        String encodedPassword = passwordEncoder.encode(ws.getPassword());
        ws.setPassword(encodedPassword);
        return repo.save(ws);
    }
    public List<Workshop> getAll() { return repo.findAll(); }
    public Optional<Workshop> get(Integer id) { return repo.findById(id); }
    public Workshop save(Workshop ws) { 
        ws.setPassword(passwordEncoder.encode(ws.getPassword()));
        return repo.save(ws);
     }
    public void delete(Integer id) { repo.deleteById(id); }
}
