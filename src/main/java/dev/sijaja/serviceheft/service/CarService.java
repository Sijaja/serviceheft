package dev.sijaja.serviceheft.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.model.Car;
import dev.sijaja.serviceheft.repository.CarRepository;

@Service
public class CarService {
    private final CarRepository repo;
    public CarService(CarRepository repo) { this.repo = repo; }
    public List<Car> getAll() { return repo.findAll(); }
    public Optional<Car> get(Integer id) { return repo.findById(id); }
    public Car save(Car car) { return repo.save(car); }
    public void delete(Integer id) { repo.deleteById(id); }
}