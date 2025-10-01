package dev.sijaja.serviceheft.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.dto.AverageCostComparisonDto;
import dev.sijaja.serviceheft.dto.CostComparisonDto;
import dev.sijaja.serviceheft.dto.MaintenanceTableDto;
import dev.sijaja.serviceheft.dto.NextMaintenanceDto;
import dev.sijaja.serviceheft.dto.ToBeReplacedDto;
import dev.sijaja.serviceheft.dto.TotalCostDto;
import dev.sijaja.serviceheft.dto.YearlyMaintenanceCostsDto;
import dev.sijaja.serviceheft.model.Maintenance;
import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.repository.MaintenanceRepository;

@Service
public class MaintenanceService {
    private final MaintenanceRepository repo;

    public MaintenanceService(MaintenanceRepository repo) {
        this.repo = repo;
    }

    public List<Maintenance> getAll() {
        return repo.findAll();
    }

    public Optional<Maintenance> get(Integer id) {
        return repo.findById(id);
    }

    public Maintenance save(Maintenance check) {
        return repo.save(check);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public YearlyMaintenanceCostsDto getMaintenanceCostsByMonth(Integer carId, int year) {
        List<Maintenance> maintenances = repo.findByCarIdAndYear(carId, year);

        // Initialize month names
        List<String> months = Arrays.asList("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
        List<Double> monthlyTotals = new ArrayList<>(Collections.nCopies(12, 0.0));
        Map<String, List<Double>> typeBreakdown = new HashMap<>();

        for (Maintenance m : maintenances) {
            int monthIndex = m.getMtncDate().getMonthValue() - 1;
            monthlyTotals.set(monthIndex, monthlyTotals.get(monthIndex) + m.getCost());

            // breakdown by type
            typeBreakdown.putIfAbsent(m.getMtncType().name(), new ArrayList<>(Collections.nCopies(12, 0.0)));
            List<Double> typeCosts = typeBreakdown.get(m.getMtncType().name());
            typeCosts.set(monthIndex, typeCosts.get(monthIndex) + m.getCost());
        }

        return new YearlyMaintenanceCostsDto(months, monthlyTotals, typeBreakdown);
    }

    public Map<Integer, Double> getYearlyTotals(int carId) {
    List<Maintenance> maintenances = repo.findByCarId(carId);
    Map<Integer, Double> yearlyTotals = new HashMap<>();

    for (Maintenance m : maintenances) {
        int year = m.getMtncDate().getYear();
        yearlyTotals.merge(year, m.getCost(), Double::sum);
    }
    return yearlyTotals;
    }

    public TotalCostDto getTotalAndAverageCost(int carId) {
        List<Maintenance> maintenances = repo.findByCarId(carId);
        double total = maintenances.stream().mapToDouble(Maintenance::getCost).sum();
        double avg = maintenances.isEmpty() ? 0 : total / maintenances.size();
        return new TotalCostDto(total, avg);
    }

    public NextMaintenanceDto getNextMaintenance(int carId) {
        LocalDate today = LocalDate.now();
        return repo.findByCarId(carId).stream()
            .filter(m -> m.getNextDate() != null && !m.getNextDate().isBefore(today)) // only future dates
            .min(Comparator.comparing(Maintenance::getNextDate))
            .map(m -> new NextMaintenanceDto(m.getNextDate(), m.getNextMileage()))
            .orElse(new NextMaintenanceDto(null, null)); // nothing upcoming
    }

    public List<ToBeReplacedDto> getToBeReplacedItems(int carId) {
        List<Maintenance> maintenances = repo.findByCarId(carId);
        List<ToBeReplacedDto> result = new ArrayList<>();

        for (Maintenance m : maintenances) {
            checkField("Engine", m.getEngineCheckId(), m.getCarCondition(), result);
            checkField("Belt & Hose", m.getBeltHoseCheckId(), m.getCarCondition(), result);
            checkField("Brake", m.getBrakeCheckId(), m.getCarCondition(), result);
            checkField("Tire", m.getTireCheckId(), m.getCarCondition(), result);
            checkField("Electric", m.getElectricCheckID(), m.getCarCondition(), result);
            checkField("Filter", m.getFilterCheckId(), m.getCarCondition(), result);
            checkField("Emission", m.getEmmisionCheckId(), m.getCarCondition(), result);
            checkField("HVAC", m.getHvacCheckId(), m.getCarCondition(), result);
        }

        return result;
    }

    private void checkField(String name, int value, Condition cond, List<ToBeReplacedDto> result) {
        if (cond == Condition.POOR) {
            result.add(new ToBeReplacedDto(name, cond.name()));
        }
    }

    public List<MaintenanceTableDto> getMaintenanceTable(Integer carId) {
        return repo.getMaintenanceTable(carId);
    }

    public CostComparisonDto getCostComparison(int carId) {
        Double myCarTotal = repo.getTotalCostForCar(carId);
        Double othersAvg = repo.getAverageCostOfOtherCars(carId);

        return new CostComparisonDto(
            myCarTotal != null ? myCarTotal : 0.0,
            othersAvg != null ? othersAvg : 0.0
        );
    }

    public AverageCostComparisonDto getAverageCostComparison(int carId) {
        Double myCarTotal = repo.getAverageRepairCost(carId);
        Double othersAvg = repo.getAverageRepairCostForAll(carId);

        return new AverageCostComparisonDto(
            myCarTotal != null ? myCarTotal : 0.0,
            othersAvg != null ? othersAvg : 0.0
        );
    }
}
