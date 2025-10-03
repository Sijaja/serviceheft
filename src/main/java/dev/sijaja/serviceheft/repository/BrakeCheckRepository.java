package dev.sijaja.serviceheft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.sijaja.serviceheft.model.BrakeCheck;

public interface BrakeCheckRepository extends JpaRepository<BrakeCheck, Integer> {
    @Query("""
       SELECT e FROM BrakeCheck e 
       WHERE e.carId = :carId 
       AND (
           e.brakeLines IN ('POOR','TO_BE_REPLACED') OR
           e.frontRotorsCon IN ('POOR','TO_BE_REPLACED') OR
           e.rearRotorsCon IN ('POOR','TO_BE_REPLACED')
       )
       """)
    List<BrakeCheck> getCriticalBrake(@Param("carId") Integer carId);
}
