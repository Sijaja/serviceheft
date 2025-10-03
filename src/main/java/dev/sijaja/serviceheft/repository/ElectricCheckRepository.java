package dev.sijaja.serviceheft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.sijaja.serviceheft.model.ElectricCheck;

public interface  ElectricCheckRepository extends JpaRepository<ElectricCheck, Integer> {
    @Query("""
       SELECT e FROM ElectricCheck e 
       WHERE e.carId = :carId 
       AND (
           e.terminals IN ('POOR','TO_BE_REPLACED')
       )
       """)
    List<ElectricCheck> getCriticalElectric(@Param("carId") Integer carId);
}
