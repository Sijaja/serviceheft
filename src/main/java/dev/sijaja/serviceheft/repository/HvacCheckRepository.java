package dev.sijaja.serviceheft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sijaja.serviceheft.model.HvacCheck;

public interface HvacCheckRepository extends JpaRepository<HvacCheck, Integer>{

}
