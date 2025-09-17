package dev.sijaja.serviceheft.controller;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sijaja.serviceheft.model.Car;
import dev.sijaja.serviceheft.service.CarService;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin
public class CarController {
    private final CarService service;
    public CarController(CarService service) { this.service = service; }

    @GetMapping
    public List<Car> getAll() { return service.getAll(); }

    @PostMapping
    public Car create(@RequestBody Car c) { return service.save(c); }

    @GetMapping("/{id}")
    public Car get(@PathVariable Integer id) { return service.get(id).orElseThrow(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}