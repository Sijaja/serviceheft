package dev.sijaja.serviceheft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.sijaja.serviceheft.model.BeltHoseCheck;

public interface BeltHoseCheckRepository extends JpaRepository<BeltHoseCheck, Integer> {

    @Query("""
        SELECT e FROM BeltHoseCheck e
        WHERE e.maintenance.car.id = :carId
        AND (
            e.heaterHoses IN ('POOR','TO_BE_REPLACED') OR
            e.radiatorHoses IN ('POOR','TO_BE_REPLACED') OR
            e.serpentineBelt IN ('POOR','TO_BE_REPLACED') OR
            e.timingBelt IN ('POOR','TO_BE_REPLACED')
        )
    """)
    List<BeltHoseCheck> findCriticalByCarId(@Param("carId") Integer carId);

}