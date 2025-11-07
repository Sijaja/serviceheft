package dev.sijaja.serviceheft.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sijaja.serviceheft.model.Cars;

public interface CarRepository extends JpaRepository<Cars, Integer> {

    List<Cars> findAllByOwnerOwnerId(int ownerId);

    Optional<Cars> findByCarIdAndOwnerOwnerId(int carId, int ownerId);
}
