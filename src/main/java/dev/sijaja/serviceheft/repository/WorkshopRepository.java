package dev.sijaja.serviceheft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sijaja.serviceheft.model.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Integer>{
    java.util.Optional<Workshop> findByUserUserId(int id);
}
