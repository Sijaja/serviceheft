package dev.sijaja.serviceheft.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.sijaja.serviceheft.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    Optional<Owner> findByUserUserId(int id);

    @Query("SELECT o.ownerId FROM Owner o JOIN Cars c ON o.ownerId = c.owner.ownerId WHERE c.carId = :carId")
    Integer findOwnerIdByCarId(@Param("carId") int carId);
}
