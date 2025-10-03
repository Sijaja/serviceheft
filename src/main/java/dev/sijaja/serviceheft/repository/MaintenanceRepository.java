package dev.sijaja.serviceheft.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.sijaja.serviceheft.dto.MaintenanceTableDto;
import dev.sijaja.serviceheft.model.Maintenance;

public interface  MaintenanceRepository extends JpaRepository<Maintenance, Integer>{
       // Helper method to find maintenances by carId and year
       @Query("SELECT m FROM Maintenance m WHERE m.carId = :carId AND YEAR(m.mtncDate) = :year")
       List<Maintenance> findByCarIdAndYear(@Param("carId") Integer carId, @Param("year") int year);

       // Helper method to find all maintenances for a car
       @Query("SELECT m FROM Maintenance m WHERE m.carId = :carId")
       List<Maintenance> findByCarId(@Param("carId") Integer carId);

       // 1. Total cost of repairs
       @Query("SELECT SUM(m.cost) FROM Maintenance m WHERE m.carId = :carId")
       Double getTotalRepairCost(@Param("carId") Integer carId);

       // 2. Average cost per repair
       @Query("SELECT AVG(m.cost) FROM Maintenance m WHERE m.carId = :carId")
       Double getAverageRepairCost(@Param("carId") Integer carId);

       // 3. Next maintenance date (using nextDate field)
       @Query("SELECT MIN(m.nextDate) FROM Maintenance m WHERE m.carId = :carId AND m.nextDate > CURRENT_DATE")
       LocalDate getNextMaintenanceDate(@Param("carId") Integer carId);

       // 4. List of critical maintenance (carCondition POOR or TO_BE_REPLACED)
       @Query("SELECT m FROM Maintenance m WHERE m.carId = :carId AND m.carCondition IN ('POOR','TO_BE_REPLACED')")
       List<Maintenance> getCriticalMaintenances(@Param("carId") Integer carId);

       // 5. Most expensive repair
       @Query("SELECT m FROM Maintenance m WHERE m.carId = :carId ORDER BY m.cost DESC")
       List<Maintenance> getMostExpensiveRepair(@Param("carId") Integer carId);

       // 6. Repairs count per year
       @Query("SELECT YEAR(m.mtncDate) as yr, COUNT(m) FROM Maintenance m WHERE m.carId = :carId GROUP BY YEAR(m.mtncDate)")
       List<Object[]> getRepairsCountPerYear(@Param("carId") Integer carId);

       // 7. Total cost per year
       @Query("SELECT YEAR(m.mtncDate) as yr, SUM(m.cost) FROM Maintenance m WHERE m.carId = :carId GROUP BY YEAR(m.mtncDate)")
       List<Object[]> getTotalCostPerYear(@Param("carId") Integer carId);

       // 8. Average interval between maintenance (in days)
       @Query("SELECT AVG(DATEDIFF(m2.mtncDate, m1.mtncDate)) " +
              "FROM Maintenance m1, Maintenance m2 " +
              "WHERE m1.carId = :carId AND m2.carId = :carId AND m2.mtncDate > m1.mtncDate")
       Double getAverageIntervalBetweenMaintenance(@Param("carId") Integer carId);

       // 9. Frequent maintenance types
       @Query("SELECT m.mtncType, COUNT(m) as cnt FROM Maintenance m " +
              "WHERE m.carId = :carId GROUP BY m.mtncType ORDER BY cnt DESC")
       List<Object[]> getFrequentMaintenanceTypes(@Param("carId") Integer carId);

       // 10. Cost breakdown by maintenance type
       @Query("SELECT m.mtncType, SUM(m.cost) FROM Maintenance m WHERE m.carId = :carId GROUP BY m.mtncType")
       List<Object[]> getCostBreakdownByType(@Param("carId") Integer carId);

       // 11. Last recorded mileage
       @Query("SELECT m.currentMileage FROM Maintenance m WHERE m.carId = :carId ORDER BY m.mtncDate DESC")
       List<Integer> getLastRecordedMileage(@Param("carId") Integer carId);

       // 12. Maintenance history table
       @Query("SELECT new dev.sijaja.serviceheft.dto.MaintenanceTableDto(" +
              "m.mtncDate, m.inspectionNotes, m.cost, m.currentMileage, w.workshopName) " +
              "FROM Maintenance m JOIN Workshop w ON m.workshopId = w.workshopId " +
              "WHERE m.carId = :carId ORDER BY m.mtncDate DESC")
       List<MaintenanceTableDto> getMaintenanceTable(@Param("carId") Integer carId);

       // 13. Total maintenance cost for the car
       @Query("SELECT SUM(m.cost) FROM Maintenance m WHERE m.carId = :carId")
       Double getTotalCostForCar(@Param("carId") int carId);

       // 14. Average total maintenance cost for all other cars
       @Query(
       value = "SELECT AVG(c1.total) " +
              "FROM ( " +
              "   SELECT SUM(m.cost) AS total " +
              "   FROM maintenance m " +
              "   WHERE m.car_id <> :carId " +
              "   GROUP BY m.car_id " +
              ") c1",
       nativeQuery = true
       )
       Double getAverageCostOfOtherCars(@Param("carId") int carId);

       // 15. Average of average repair costs for all other cars
       @Query(value = "SELECT AVG(c1.average) " +
              "FROM ( " +
              "   SELECT AVG(m.cost) AS average " +
              "   FROM maintenance m " +
              "   WHERE m.car_id <> :carId " +
              "   GROUP BY m.car_id " +
              ") c1",
       nativeQuery = true)
       Double getAverageRepairCostForAll(@Param("carId") Integer carId);


       @Query("""
       SELECT e FROM Maintenance e 
       WHERE e.carId = :carId 
       AND (
           e.carCondition IN ('POOR','TO_BE_REPLACED')
       )
       """)
       List<Maintenance> getCriticalCar(@Param("carId") Integer carId);
}
