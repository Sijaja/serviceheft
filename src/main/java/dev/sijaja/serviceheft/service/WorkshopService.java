package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.dto.WorkshopSignupDto;
import dev.sijaja.serviceheft.model.User;
import dev.sijaja.serviceheft.model.Workshop;
import dev.sijaja.serviceheft.model.enums.UserRole;
import dev.sijaja.serviceheft.repository.UserRepository;
import dev.sijaja.serviceheft.repository.WorkshopRepository;

@Service
public class WorkshopService {
    private final WorkshopRepository repo;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public WorkshopService(WorkshopRepository repo, UserRepository userRepo, PasswordEncoder encoder) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public Workshop registerWorkshop(WorkshopSignupDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(UserRole.WORKSHOP);

        user = userRepo.save(user);
        Workshop ws = new Workshop();
        ws.setWorkshopName(dto.getWorkshopName());
        ws.setStreet(dto.getStreet());
        ws.setHouseNumber(dto.getHouseNumber());
        ws.setCity(dto.getCity());
        ws.setPhoneNumber(dto.getPhoneNumber());
        ws.setWebsite(dto.getWebsite());
        ws.setUser(user);
        
        return repo.save(ws);
    }
    public List<Workshop> getAll() { return repo.findAll(); }
    public Optional<Workshop> get(Integer id) { return repo.findById(id); }
    public Workshop save(Workshop ws) { 
        return repo.save(ws);
     }
    public void delete(Integer id) { repo.deleteById(id); }
}
