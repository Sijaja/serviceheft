package dev.sijaja.serviceheft.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.model.User;
import dev.sijaja.serviceheft.model.Workshop;
import dev.sijaja.serviceheft.model.enums.UserRole;
import dev.sijaja.serviceheft.repository.OwnerRepository;
import dev.sijaja.serviceheft.repository.UserRepository;
import dev.sijaja.serviceheft.repository.WorkshopRepository;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    private final UserRepository userRepo;
    private final OwnerRepository ownerRepo;
    private final WorkshopRepository workshopRepo;

    public UserController(UserRepository userRepo, OwnerRepository ownerRepo, WorkshopRepository workshopRepo) {
        this.userRepo = userRepo;
        this.ownerRepo = ownerRepo;
        this.workshopRepo = workshopRepo;
    }

    @GetMapping
    public ResponseEntity<?> getLoggedInUser(Principal principal) {

        // principal.getName() = email
        String email = principal.getName();

        User user = userRepo.findByEmail(email).orElseThrow();

        if (user.getRole() == UserRole.OWNER) {
            Owner owner = ownerRepo.findByUserUserId(user.getUserId()).orElseThrow();
            return ResponseEntity.ok(Map.of(
                    "type", "OWNER",
                    "id", owner.getOwnerId(),
                    "email", user.getEmail()
            ));
        } else {
            Workshop ws = workshopRepo.findByUserUserId(user.getUserId()).orElseThrow();
            return ResponseEntity.ok(Map.of(
                    "type", "WORKSHOP",
                    "id", ws.getWorkshopId(),
                    "email", user.getEmail()
            ));
        }
    }
}
