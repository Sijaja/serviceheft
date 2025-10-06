package dev.sijaja.serviceheft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.sijaja.serviceheft.model.TireCheck;

public interface TireCheckRepository extends JpaRepository<TireCheck, Integer> {

    @Query("""
        SELECT e FROM TireCheck e
        WHERE e.maintenance.car.id = :carId
        AND (
            e.shockAbsorbers IN ('POOR','TO_BE_REPLACED')
        )
    """)
    List<TireCheck> findCriticalByCarId(@Param("carId") Integer carId);
}
