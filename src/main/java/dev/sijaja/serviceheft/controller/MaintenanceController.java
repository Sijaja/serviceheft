package dev.sijaja.serviceheft.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sijaja.serviceheft.dto.AverageCostComparisonDto;
import dev.sijaja.serviceheft.dto.CostComparisonDto;
import dev.sijaja.serviceheft.dto.CostPerKmComparisonDto;
import dev.sijaja.serviceheft.dto.HealthScoreDTO;
import dev.sijaja.serviceheft.dto.MaintenanceCountComparisonDto;
import dev.sijaja.serviceheft.dto.MaintenanceTableDto;
import dev.sijaja.serviceheft.dto.MileageComparisonDto;
import dev.sijaja.serviceheft.dto.NextMaintenanceDto;
import dev.sijaja.serviceheft.dto.TotalCostComparisonDto;
import dev.sijaja.serviceheft.dto.TotalCostDto;
import dev.sijaja.serviceheft.dto.YearlyMaintenanceCostsDto;
import dev.sijaja.serviceheft.dto.addMaintenance.MaintenanceDTO;
import dev.sijaja.serviceheft.model.Cars;
import dev.sijaja.serviceheft.model.Maintenance;
import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.model.User;
import dev.sijaja.serviceheft.model.Workshop;
import dev.sijaja.serviceheft.service.CarService;
import dev.sijaja.serviceheft.service.MaintenanceService;
import dev.sijaja.serviceheft.service.OwnerService;
import dev.sijaja.serviceheft.service.UserService;
import dev.sijaja.serviceheft.service.WorkshopService;

@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin
public class MaintenanceController {

    private final MaintenanceService service;
    private final UserService userService;
    private final WorkshopService workshopService;
    private final CarService carService;
    private final OwnerService ownerService;

    public MaintenanceController(MaintenanceService service, UserService userService, WorkshopService workshopService, CarService carService, OwnerService ownerService) {
        this.service = service;
        this.userService = userService;
        this.workshopService = workshopService;
        this.carService = carService;
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<Maintenance> myMaintenances(Principal principal) {
        return service.findMaintenancesForOwner(principal.getName());
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<List<Maintenance>> getAllMaintenanceByCarId(@PathVariable("id") int carId, Principal principal) {
        return service.findMaintenanceByCarIdForOwner(carId, principal.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable int id, Principal principal) {
        return service.findMaintenanceForOwnerbyMtncId(id, principal.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @PostMapping
    public Maintenance create(@RequestBody Maintenance o) {
        return service.save(o);
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody MaintenanceDTO m, Principal principal) {
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        Workshop workshop = workshopService.findByUserId(user.getUserId());
        Cars car = carService.get(m.getCarId()).orElseThrow(() -> new RuntimeException("Car not found"));
        Maintenance maintenance = service.fromDto(m, car, workshop);
        maintenance.setWorkshop(workshop);
        Maintenance savedMtnc = service.save(maintenance);
        return ResponseEntity.ok(savedMtnc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

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
    public ResponseEntity<List<MaintenanceTableDto>> getMaintenanceTable(@PathVariable Integer carId, Principal principal) {
        return service.getMaintenanceTable(carId, principal.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @GetMapping("/costComparison/{carId}")
    public CostComparisonDto getCostComparison(@PathVariable int carId) {
        return service.getCostComparison(carId);
    }

    @GetMapping("/averageCostComparison/{carId}")
    public AverageCostComparisonDto getAverageCostComparison(@PathVariable int carId) {
        return service.getAverageCostComparison(carId);
    }

    @GetMapping("/workshop/{workshopId}")
    public Optional<List<MaintenanceTableDto>> getMaintenanceTableForWorkshop(@PathVariable int workshopId, Principal principal) {
        return service.getMaintenanceTableForWorkshop(workshopId, principal.getName());
    }

    @GetMapping("/carHealth/{carId}")
    public ResponseEntity<HealthScoreDTO> getBrakesAndTiresScore(@PathVariable Integer carId, Principal principal) {
        User user = userService.loadUserByEmail(principal.getName());
        Owner owner = ownerService.findByUserId(user.getUserId());
        return service.getHealthScore(carId, owner.getOwnerId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @GetMapping("/similarMtnc/{carId}")
    public Map<Integer, List<Maintenance>> getSimilarCarsMaintenance(@PathVariable Integer carId, Principal principal) {
        Cars car = carService.findCarForOwner(carId, principal.getName())
                .orElseThrow(() -> new RuntimeException("You can only view your own cars' data"));
        if (car == null) {
            throw new RuntimeException("Car not found but it is there");
        } else if (car.getOwner().getUser().getEmail() == null || !car.getOwner().getUser().getEmail().equals(principal.getName())) {
            throw new RuntimeException("Unauthorized access to car data through similar maintenance endpoint");
        }
        return service.getSimilarCarsMaintenance(carId);
    }

    @GetMapping("/costComparesion/{carId}")
    public ResponseEntity<Optional<List<Double>>> CostComparesion(@PathVariable Integer carId, Principal principal) {
        User user = userService.loadUserByEmail(principal.getName());
        Owner owner = ownerService.findByUserId(user.getUserId());
        Optional<List<Double>> costs = service.CostComparesion(carId, owner.getOwnerId());
        return ResponseEntity.ok(costs);
    }

    
    @GetMapping("/averageCostSimilar/{carId}")
    public ResponseEntity<Optional<List<Double>>> getAverageCostForSimilarCars(@PathVariable Integer carId, Principal principal) {
        User user = userService.loadUserByEmail(principal.getName());
        Optional<List<Double>> costs = service.getAverageCostForSimilarCars(carId);
        return ResponseEntity.ok(costs);
    }

    @GetMapping("/averageCostAll/{carId}")
    public ResponseEntity<Optional<List<Double>>> getAverageCostForAllCars(@PathVariable Integer carId, Principal principal) {
        User user = userService.loadUserByEmail(principal.getName());
        Optional<List<Double>> costs = service.getAverageCostForAllCars(carId);
        return ResponseEntity.ok(costs);
    }

    @GetMapping("/averageCostSameYear/{carId}")
    public ResponseEntity<Optional<List<Double>>> getAverageCostForSameYearCars(@PathVariable Integer carId, Principal principal) {
        User user = userService.loadUserByEmail(principal.getName());
        Optional<List<Double>> costs = service.getAverageCostForSameYearCars(carId);
        return ResponseEntity.ok(costs);
    }

    @GetMapping("/totalCostComparison/{carId}")
    public TotalCostComparisonDto getTotalCostComparison(@PathVariable int carId) {
        return service.compareTotalMaintenanceCosts(carId);
    }

    @GetMapping("/maintenanceCountComparison/{carId}")
    public MaintenanceCountComparisonDto getMaintenanceCountComparison(@PathVariable int carId) {
        return service.compareMaintenanceCounts(carId);
    }

    @GetMapping("/mileageComparison/{carId}")
    public MileageComparisonDto getMileageComparison(@PathVariable int carId) {
        return service.compareMileage(carId);
    }

    @GetMapping("/costPerKmComparison/{carId}")
    public CostPerKmComparisonDto getCostPerKmComparison(@PathVariable int carId) {
        return service.compareCostPerThousandKm(carId);
    }
}
