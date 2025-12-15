package dev.sijaja.serviceheft.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.sijaja.serviceheft.model.Cars;

public interface CarRepository extends JpaRepository<Cars, Integer> {

    List<Cars> findAllByOwnerOwnerId(int ownerId);

    Optional<Cars> findByCarIdAndOwnerOwnerId(int carId, int ownerId);

    Optional<Cars> findByVinNumber(String vinNumber);

    // A method to find the ID's of all similar cars based on make, model, and year
    @Query("SELECT c.carId FROM Cars c WHERE c.manufacturer = :manufacturer AND c.model = :model AND c.makeYear = :makeYear")
    List<Integer> findSimilarCarIds(@Param("manufacturer") String manufacturer, @Param("model") String model, @Param("makeYear") int makeYear);

    // A method to find the ID's of all cars with the same make year only
    @Query("SELECT c.carId FROM Cars c WHERE c.makeYear = :makeYear")
    List<Integer> findCarIdsByMakeYear(@Param("makeYear") int makeYear);
}
