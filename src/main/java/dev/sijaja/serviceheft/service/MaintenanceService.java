package dev.sijaja.serviceheft.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.dto.YearlyMaintenanceCostsDto;
import dev.sijaja.serviceheft.model.Maintenance;
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
            int monthIndex = m.getDate().getMonthValue() - 1;
            monthlyTotals.set(monthIndex, monthlyTotals.get(monthIndex) + m.getCost());

            // breakdown by type
            typeBreakdown.putIfAbsent(m.getType(), new ArrayList<>(Collections.nCopies(12, 0.0)));
            List<Double> typeCosts = typeBreakdown.get(m.getType());
            typeCosts.set(monthIndex, typeCosts.get(monthIndex) + m.getCost());
        }

        return new YearlyMaintenanceCostsDto(months, monthlyTotals, typeBreakdown);
    }
}
