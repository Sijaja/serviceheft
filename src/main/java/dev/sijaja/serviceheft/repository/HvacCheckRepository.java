package dev.sijaja.serviceheft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.sijaja.serviceheft.model.HvacCheck;

public interface HvacCheckRepository extends JpaRepository<HvacCheck, Integer>{
    @Query("""
       SELECT e FROM HvacCheck e 
       WHERE e.carId = :carId 
       AND (
           e.acPerformance IN ('POOR','TO_BE_REPLACED') OR
           e.blowerMotor IN ('POOR','TO_BE_REPLACED') OR
           e.heatPerformance IN ('POOR','TO_BE_REPLACED')
       )
       """)
    List<HvacCheck> getCriticalHvac(@Param("carId") Integer carId);
}
