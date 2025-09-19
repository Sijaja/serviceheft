package dev.sijaja.serviceheft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sijaja.serviceheft.model.Cars;

public interface CarRepository extends JpaRepository<Cars, Integer> { }
