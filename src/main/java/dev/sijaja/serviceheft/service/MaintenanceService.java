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

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.sijaja.serviceheft.dto.AverageCostComparisonDto;
import dev.sijaja.serviceheft.dto.CostComparisonDto;
import dev.sijaja.serviceheft.dto.MaintenanceTableDto;
import dev.sijaja.serviceheft.dto.NextMaintenanceDto;
import dev.sijaja.serviceheft.dto.ToBeReplacedDto;
import dev.sijaja.serviceheft.dto.TotalCostDto;
import dev.sijaja.serviceheft.dto.YearlyMaintenanceCostsDto;
import dev.sijaja.serviceheft.model.Maintenance;
import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.repository.BeltHoseCheckRepository;
import dev.sijaja.serviceheft.repository.BrakeCheckRepository;
import dev.sijaja.serviceheft.repository.ElectricCheckRepository;
import dev.sijaja.serviceheft.repository.EngineCheckRepository;
import dev.sijaja.serviceheft.repository.HvacCheckRepository;
import dev.sijaja.serviceheft.repository.MaintenanceRepository;
import dev.sijaja.serviceheft.repository.OwnerRepository;
import dev.sijaja.serviceheft.repository.TireCheckRepository;

@Service
public class MaintenanceService {
    private final MaintenanceRepository repo;
    private final OwnerRepository ownerRepo;
    private final EngineCheckRepository engineRepo;
    private final BeltHoseCheckRepository beltRepo;
    private final BrakeCheckRepository brakeRepo;
    private final TireCheckRepository tireRepo;
    private final ElectricCheckRepository electricRepo;
    private final HvacCheckRepository hvacRepo;

    public MaintenanceService(MaintenanceRepository repo, OwnerRepository ownerRepo, EngineCheckRepository engineRepo,
            BeltHoseCheckRepository beltRepo, BrakeCheckRepository brakeRepo, TireCheckRepository tireRepo,
            ElectricCheckRepository electricRepo, HvacCheckRepository hvacRepo) {
        this.repo = repo;
        this.ownerRepo = ownerRepo;
        this.engineRepo = engineRepo;
        this.beltRepo = beltRepo;
        this.brakeRepo = brakeRepo;
        this.tireRepo = tireRepo;
        this.electricRepo = electricRepo;
        this.hvacRepo = hvacRepo;
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

    public List<Maintenance> findMaintenancesForOwner(String email) {
        Owner owner = ownerRepo.findByEmail(email);
        if (owner == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Owner not found");
        }
        return repo.findAllByOwnerId(owner.getOwnerId());
    }

    public Optional<List<Maintenance>> findMaintenanceForOwner(int carId, String email) {
        Owner owner = ownerRepo.findByEmail(email);
        if (owner == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Owner not found");
        }
        List<Maintenance> list =  repo.findByCarIdAndOwnerId(carId, owner.getOwnerId());
        return list.isEmpty() ? Optional.empty() : Optional.of(list);
    }

    public Optional<Maintenance> findMaintenanceForOwnerbyMtncId(int mtncId, String email) {
        Owner owner = ownerRepo.findByEmail(email);
        if (owner == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Owner not found");
        }
        return repo.findByMtncIdAndOwnerId(mtncId, owner.getOwnerId());
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
        
        List<ToBeReplacedDto> result = new ArrayList<>();
        engineRepo.findCriticalByCarId(carId).forEach(e ->
            result.add(new ToBeReplacedDto("Engine"))
        );

        beltRepo.findCriticalByCarId(carId).forEach(b ->
            result.add(new ToBeReplacedDto("Belt & Hose"))
        );

        brakeRepo.findCriticalByCarId(carId).forEach(b ->
            result.add(new ToBeReplacedDto("Brakes"))
        );

        tireRepo.findCriticalByCarId(carId).forEach(t ->
            result.add(new ToBeReplacedDto("Tires"))
        );

        electricRepo.findCriticalByCarId(carId).forEach(e ->
            result.add(new ToBeReplacedDto("Electric"))
        );

        hvacRepo.findCriticalByCarId(carId).forEach(h ->
            result.add(new ToBeReplacedDto("HVAC"))
        );

        return result;
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
