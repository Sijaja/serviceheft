package dev.sijaja.serviceheft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sijaja.serviceheft.model.ElectricCheck;

public interface  ElectricCheckRepository extends JpaRepository<ElectricCheck, Integer> {

}
