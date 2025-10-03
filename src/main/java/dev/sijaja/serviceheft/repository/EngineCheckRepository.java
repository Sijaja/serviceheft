package dev.sijaja.serviceheft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.sijaja.serviceheft.model.EngineCheck;

public interface EngineCheckRepository extends JpaRepository<EngineCheck, Integer>{
    @Query("""
       SELECT e FROM EngineCheck e 
       WHERE e.carId = :carId 
       AND (
           e.brakeFluidColor IN ('POOR','TO_BE_REPLACED') OR
           e.coolantCondition IN ('POOR','TO_BE_REPLACED') OR
           e.engineStatus IN ('POOR','TO_BE_REPLACED') OR
           e.oilCondition IN ('POOR','TO_BE_REPLACED')
       )
       """)
    List<EngineCheck> getCriticalEngine(@Param("carId") Integer carId);
}
