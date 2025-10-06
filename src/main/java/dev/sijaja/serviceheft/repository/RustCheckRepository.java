package dev.sijaja.serviceheft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.sijaja.serviceheft.model.RustCheck;

public interface RustCheckRepository extends JpaRepository<RustCheck, Integer>{

    @Query("""
        SELECT e FROM RustCheck e
        WHERE e.maintenance.car.id = :carId
        AND (
            e.windowSeals IN ('POOR','TOREPLACE') OR
           e.doorBottom IN ('POOR','TOREPLACE') OR
           e.exhaustArea IN ('POOR','TOREPLACE') OR
           e.fenders IN ('POOR','TOREPLACE') OR
           e.hoodEdges IN ('POOR','TOREPLACE') OR
           e.roofEdges IN ('POOR','TOREPLACE') OR
           e.sideSkirts IN ('POOR','TOREPLACE') OR
           e.suspension IN ('POOR','TOREPLACE') OR
           e.trunkFloor IN ('POOR','TOREPLACE') OR
           e.underbody IN ('POOR','TOREPLACE') OR
           e.wheelArches IN ('POOR','TOREPLACE')
        )
    """)
    List<RustCheck> findCriticalByCarId(@Param("carId") Integer carId);
}
