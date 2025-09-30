package dev.sijaja.serviceheft.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sijaja.serviceheft.dto.MaintenanceTableDto;
import dev.sijaja.serviceheft.dto.NextMaintenanceDto;
import dev.sijaja.serviceheft.dto.TotalCostDto;
import dev.sijaja.serviceheft.dto.YearlyMaintenanceCostsDto;
import dev.sijaja.serviceheft.model.Maintenance;
import dev.sijaja.serviceheft.service.MaintenanceService;


@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin
public class MaintenanceController {
    private final MaintenanceService service;
    public MaintenanceController(MaintenanceService service) { this.service = service; }

    @GetMapping
    public List<Maintenance> getAll() { return service.getAll(); }

    @PostMapping
    public Maintenance create(@RequestBody Maintenance o) { return service.save(o); }

    @GetMapping("/{id}")
    public Maintenance get(@PathVariable Integer id) { return service.get(id).orElseThrow(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }

    @GetMapping("/{carId}/year/{year}")
    public YearlyMaintenanceCostsDto getYearlyCosts(
            @PathVariable Integer carId,
            @PathVariable int year
    ) {
        return service.getMaintenanceCostsByMonth(carId, year);
    }

    @GetMapping("/{carId}/years")
    public Map<Integer, Double> getYearlyTotals(@PathVariable int carId) {
    return service.getYearlyTotals(carId);
    }

    @GetMapping("/totals/{carId}")
    public TotalCostDto getTotalAndAverage(@PathVariable int carId) {
        return service.getTotalAndAverageCost(carId);
    }

    @GetMapping("/nextMTNC/{carId}")
    public NextMaintenanceDto getNextMaintenance(@PathVariable int carId) {
        return service.getNextMaintenance(carId);
    }
    
    @GetMapping("/table/{carId}")
    public List<MaintenanceTableDto> getMaintenanceTable(@PathVariable Integer carId) {
        return service.getMaintenanceTable(carId);
    }

}
