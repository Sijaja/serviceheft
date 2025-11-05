package dev.sijaja.serviceheft.controller;


import java.security.Principal;
import java.util.List;

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
import dev.sijaja.serviceheft.service.CarService;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin
public class CarController {

    private final CarService service;

    public CarController(CarService service) { 
        this.service = service; 
    }

    @GetMapping
    public List<Cars> myCars(Principal principal) { 
        return service.findCarsForOwner(principal.getName()); 
    }

    @PostMapping
    public Cars create(@RequestBody Cars c) { return service.save(c); }

    @GetMapping("/{id}")
    public ResponseEntity<Cars> getCarById(@PathVariable int id, Principal principal) { 
        return service.findCarForOwner(id, principal.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
     }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) { service.delete(id); }
}