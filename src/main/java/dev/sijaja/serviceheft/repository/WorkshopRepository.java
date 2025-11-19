package dev.sijaja.serviceheft.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sijaja.serviceheft.model.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Integer>{
    Optional<Workshop> findByEmail(String email);
}
