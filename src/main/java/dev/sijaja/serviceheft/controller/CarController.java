package dev.sijaja.serviceheft.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sijaja.serviceheft.model.Cars;
import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.model.User;
import dev.sijaja.serviceheft.service.CarService;
import dev.sijaja.serviceheft.service.OwnerService;
import dev.sijaja.serviceheft.service.UserService;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin
public class CarController {

    private final CarService service;
    private final UserService userService;
    private final OwnerService ownerService;

    public CarController(CarService service, UserService userAccountRepo, OwnerService ownerRepo) {
        this.service = service;
        this.userService = userAccountRepo;
        this.ownerService = ownerRepo;
    }

    @GetMapping
    public List<Cars> myCars(Principal principal) {
        return service.findCarsForOwner(principal.getName());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Cars c, Principal principal) {
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        Owner owner = ownerService.findByUserId(user.getUserId());
        c.setOwner(owner);
        Cars saved = service.save(c);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cars> getCarById(@PathVariable int id, Principal principal) {
        return service.findCarForOwner(id, principal.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping("/default")
    public ResponseEntity<Cars> getDefaultCar(Principal principal) {
        return ResponseEntity.ok(service.getDefaultCarForOwner(principal.getName()));
    }

    @PostMapping("/default/{carId}")
    public ResponseEntity<?> setDefaultCar(@PathVariable int carId, Principal principal) {
        service.setDefaultCar(principal.getName(), carId);
        return ResponseEntity.ok(Map.of("message", "Default car updated successfully"));
    }
}
