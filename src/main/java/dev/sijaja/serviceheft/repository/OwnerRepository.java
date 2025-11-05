package dev.sijaja.serviceheft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sijaja.serviceheft.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer>{
    Owner findByEmail(String email);
    
}
