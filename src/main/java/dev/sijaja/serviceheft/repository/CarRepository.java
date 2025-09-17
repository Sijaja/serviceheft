package dev.sijaja.serviceheft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sijaja.serviceheft.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> { }
