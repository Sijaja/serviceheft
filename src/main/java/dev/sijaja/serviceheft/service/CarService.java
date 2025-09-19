package dev.sijaja.serviceheft.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.Cars;
import dev.sijaja.serviceheft.repository.CarRepository;

@Service
public class CarService {
    private final CarRepository repo;
    public CarService(CarRepository repo) { this.repo = repo; }
    public List<Cars> getAll() { return repo.findAll(); }
    public Optional<Cars> get(Integer id) { return repo.findById(id); }
    public Cars save(Cars car) { return repo.save(car); }
    public void delete(Integer id) { repo.deleteById(id); }
}