package dev.sijaja.serviceheft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.sijaja.serviceheft.model.Maintenance;

public interface  MaintenanceRepository extends JpaRepository<Maintenance, Integer>{
    
    @Query("SELECT m FROM Maintenance m WHERE m.carId = :carId AND YEAR(m.mtncDate) = :year")
    List<Maintenance> findByCarIdAndYear(@Param("carId") Integer carId, @Param("year") int year);

    @Query("SELECT m FROM Maintenance m WHERE m.carId = :carId")
    List<Maintenance> findByCarId(@Param("carId") Integer carId);
}
