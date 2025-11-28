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
import dev.sijaja.serviceheft.dto.TotalCostDto;
import dev.sijaja.serviceheft.dto.YearlyMaintenanceCostsDto;
import dev.sijaja.serviceheft.dto.addMaintenance.BeltHoseCheckDTO;
import dev.sijaja.serviceheft.dto.addMaintenance.BodyCheckDTO;
import dev.sijaja.serviceheft.dto.addMaintenance.BrakeCheckDTO;
import dev.sijaja.serviceheft.dto.addMaintenance.ElectricCheckDTO;
import dev.sijaja.serviceheft.dto.addMaintenance.EmmisionCheckDTO;
import dev.sijaja.serviceheft.dto.addMaintenance.EngineCheckDTO;
import dev.sijaja.serviceheft.dto.addMaintenance.FilterCheckDTO;
import dev.sijaja.serviceheft.dto.addMaintenance.HvacCheckDTO;
import dev.sijaja.serviceheft.dto.addMaintenance.MaintenanceDTO;
import dev.sijaja.serviceheft.dto.addMaintenance.RustCheckDTO;
import dev.sijaja.serviceheft.dto.addMaintenance.TireCheckDTO;
import dev.sijaja.serviceheft.model.BeltHoseCheck;
import dev.sijaja.serviceheft.model.BodyCheck;
import dev.sijaja.serviceheft.model.BrakeCheck;
import dev.sijaja.serviceheft.model.Cars;
import dev.sijaja.serviceheft.model.ElectricCheck;
import dev.sijaja.serviceheft.model.EmmisionCheck;
import dev.sijaja.serviceheft.model.EngineCheck;
import dev.sijaja.serviceheft.model.FilterCheck;
import dev.sijaja.serviceheft.model.HvacCheck;
import dev.sijaja.serviceheft.model.Maintenance;
import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.model.RustCheck;
import dev.sijaja.serviceheft.model.TireCheck;
import dev.sijaja.serviceheft.model.User;
import dev.sijaja.serviceheft.model.Workshop;
import dev.sijaja.serviceheft.repository.MaintenanceRepository;
import dev.sijaja.serviceheft.repository.OwnerRepository;
import dev.sijaja.serviceheft.repository.UserRepository;
import dev.sijaja.serviceheft.repository.WorkshopRepository;

@Service
public class MaintenanceService {

    private final MaintenanceRepository repo;
    private final OwnerRepository ownerRepo;
    private final UserRepository userRepo;
    private final WorkshopRepository workshopRepo;

    public MaintenanceService(MaintenanceRepository repo, OwnerRepository ownerRepo, UserRepository userRepo, WorkshopRepository workshopRepo) {
        this.repo = repo;
        this.ownerRepo = ownerRepo;
        this.userRepo = userRepo;
        this.workshopRepo = workshopRepo;
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
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Owner owner = ownerRepo.findByUserUserId(user.getUserId()).orElse(null);
        if (owner == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Owner not found");
        }
        return repo.findAllByOwnerId(owner.getOwnerId());
    }

    public Optional<List<Maintenance>> findMaintenanceByCarIdForOwner(int carId, String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Owner owner = ownerRepo.findByUserUserId(user.getUserId()).orElse(null);
        if (owner == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Owner not found");
        }
        List<Maintenance> list = repo.findByCarIdAndOwnerId(carId, owner.getOwnerId());
        return list.isEmpty() ? Optional.empty() : Optional.of(list);
    }

    public Optional<Maintenance> findMaintenanceForOwnerbyMtncId(int mtncId, String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Owner owner = ownerRepo.findByUserUserId(user.getUserId()).orElse(null);
        if (owner == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Owner not found");
        }
        return repo.findByMtncIdAndOwnerId(mtncId, owner.getOwnerId());
    }

    public YearlyMaintenanceCostsDto getMaintenanceCostsByMonth(Integer carId, int year) {
        List<Maintenance> maintenances = repo.findByCarIdAndYear(carId, year);

        // Initialize month names
        List<String> months = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
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

    public Optional<List<MaintenanceTableDto>> getMaintenanceTable(Integer carId, String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Owner owner = ownerRepo.findByUserUserId(user.getUserId()).orElse(null);
        if (owner.getOwnerId() != ownerRepo.findOwnerIdByCarId(carId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Owner not found");
        }
        List<MaintenanceTableDto> maintenanceTable = repo.getMaintenanceTable(carId);
        return maintenanceTable.isEmpty() ? Optional.empty() : Optional.of(maintenanceTable);
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

    public Optional<List<MaintenanceTableDto>> getMaintenanceTableForWorkshop(Integer workshopId, String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Workshop ws = workshopRepo.findByUserUserId(user.getUserId()).orElse(null);
        if (ws == null || ws.getWorkshopId() != workshopId) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Workshop not found");
        }
        List<MaintenanceTableDto> maintenanceTable = repo.findAllByWorkshopId(workshopId);
        return maintenanceTable.isEmpty() ? Optional.empty() : Optional.of(maintenanceTable);
    }

    public Maintenance fromDto(MaintenanceDTO dto, Cars car, Workshop workshop) {
        Maintenance mtnc = new Maintenance();
        mtnc.setCar(car);
        mtnc.setWorkshop(workshop);
        mtnc.setCarCondition(dto.getCarCondition());
        mtnc.setMtncDate(dto.getMtncDate());
        mtnc.setNextDate(dto.getNextDate());
        mtnc.setCurrentMileage(dto.getCurrentMileage());
        mtnc.setNextMileage(dto.getNextMileage());
        mtnc.setCost(dto.getCost());
        mtnc.setMtncType(dto.getMtncType());
        mtnc.setInspectionNotes(dto.getInspectionNotes());
        mtnc.setBeltHoseCheck(toBeltHoseCheck(dto.getBeltHoseCheck()));
        mtnc.setBodyCheck(toBodyCheck(dto.getBodyCheck()));
        mtnc.setBrakeCheck(toBrakeCheck(dto.getBrakeCheck()));
        mtnc.setElectricCheck(toElectricCheck(dto.getElectricCheck()));
        mtnc.setEmmisionCheck(toEmmisionCheck(dto.getEmmisionCheck()));
        mtnc.setEngineCheck(toEngineCheck(dto.getEngineCheck()));
        mtnc.setFilterCheck(toFilterCheck(dto.getFilterCheck()));
        mtnc.setHvacCheck(toHvacCheck(dto.getHvacCheck()));
        mtnc.setRustCheck(toRustCheck(dto.getRustCheck()));
        mtnc.setTireCheck(toTireCheck(dto.getTireCheck()));
        return mtnc;
    }

    private BeltHoseCheck toBeltHoseCheck(BeltHoseCheckDTO dto) {
        BeltHoseCheck check = new BeltHoseCheck();
        check.setSerpentineBelt(dto.getSerpentineBelt());
        check.setTimingBelt(dto.getTimingBelt());
        check.setRadiatorHoses(dto.getRadiatorHoses());
        check.setHeaterHoses(dto.getHeaterHoses());
        return check;
    }

    private BodyCheck toBodyCheck(BodyCheckDTO dto) {
        BodyCheck check = new BodyCheck();
        check.setHood(dto.getHood());
        check.setFrontBumper(dto.getFrontBumper());
        check.setRearBumper(dto.getRearBumper());
        check.setLeftFrontDoor(dto.getLeftFrontDoor());
        check.setRightFrontDoor(dto.getRightFrontDoor());
        check.setLeftRearDoor(dto.getLeftRearDoor());
        check.setRightRearDoor(dto.getRightRearDoor());
        check.setTrunk(dto.getTrunk());
        check.setRoof(dto.getRoof());
        check.setLeftFrontFender(dto.getLeftFrontFender());
        check.setRightFrontFender(dto.getRightFrontFender());
        check.setLeftRearFender(dto.getLeftRearFender());
        check.setRightRearFender(dto.getRightRearFender());
        check.setWindshield(dto.getWindshield());
        check.setRearWindow(dto.getRearWindow());
        return check;
    }

    private BrakeCheck toBrakeCheck(BrakeCheckDTO dto) {
        BrakeCheck check = new BrakeCheck();
        check.setfPadThickness(dto.getfPadThickness());
        check.setrPadThickness(dto.getrPadThickness());
        check.setFrontRotorsCon(dto.getFrontRotorsCon());
        check.setRearRotorsCon(dto.getRearRotorsCon());
        check.setBrakeLines(dto.getBrakeLines());
        return check;
    }

    private ElectricCheck toElectricCheck(ElectricCheckDTO dto) {
        ElectricCheck check = new ElectricCheck();
        check.setVoltage(dto.getVoltage());
        check.setAlternatorOutput(dto.getAlternatorOutput());
        check.setTerminals(dto.getTerminals());
        check.setAge(dto.getAge());
        check.setHeadLights(dto.getHeadLights());
        check.setTailLight(dto.getTailLight());
        check.setTurnSignals(dto.getTurnSignals());
        return check;
    }

    private EmmisionCheck toEmmisionCheck(EmmisionCheckDTO dto) {
        EmmisionCheck check = new EmmisionCheck();
        check.setExhaust(dto.getExhaust());
        check.setCatalytic(dto.getCatalytic());
        check.setO2Sensors(dto.getO2Sensors());
        return check;
    }

    private EngineCheck toEngineCheck(EngineCheckDTO dto) {
        EngineCheck check = new EngineCheck();
        check.setEngineStatus(dto.getEngineStatus());
        check.setOilLevel(dto.getOilLevel());
        check.setOilCondition(dto.getOilCondition());
        check.setOilFilter(dto.isOilFilter());
        check.setOilReplaced(dto.isOilReplaced());
        check.setSteeringFluid(dto.getSteeringFluid());
        check.setCoolantLevel(dto.getCoolantLevel());
        check.setCoolantCondition(dto.getCoolantCondition());
        check.setBrakeFluidLevel(dto.getBrakeFluidLevel());
        check.setBrakeFluidColor(dto.getBrakeFluidColor());
        check.setGearFluid(dto.getGearFluid());
        check.setWashFluid(dto.getWashFluid());
        return check;
    }

    private FilterCheck toFilterCheck(FilterCheckDTO dto) {
        FilterCheck check = new FilterCheck();
        check.setAirFilter(dto.getAirFilter());
        check.setFuelFilter(dto.getFuelFilter());
        check.setCabinFilter(dto.getCabinFilter());
        return check;
    }

    private HvacCheck toHvacCheck(HvacCheckDTO dto) {
        HvacCheck check = new HvacCheck();
        check.setAcPerformance(dto.getAcPerformance());
        check.setHeatPerformance(dto.getHeatPerformance());
        check.setBlowerMotor(dto.getBlowerMotor());
        return check;
    }

    private RustCheck toRustCheck(RustCheckDTO dto) {
        RustCheck check = new RustCheck();
        check.setWheelArches(dto.getWheelArches());
        check.setSideSkirts(dto.getSideSkirts());
        check.setDoorBottom(dto.getDoorBottom());
        check.setTrunkFloor(dto.getTrunkFloor());
        check.setHoodEdges(dto.getHoodEdges());
        check.setRoofEdges(dto.getRoofEdges());
        check.setFenders(dto.getFenders());
        check.setExhaustArea(dto.getExhaustArea());
        check.setUnderbody(dto.getUnderbody());
        check.setWindowSeals(dto.getWindowSeals());
        check.setSuspension(dto.getSuspension());
        return check;
    }

    private TireCheck toTireCheck(TireCheckDTO dto) {
        TireCheck check = new TireCheck();
        check.setTreadFrontLeft(dto.getTreadFrontLeft());
        check.setTreadFrontRight(dto.getTreadFrontRight());
        check.setTreadRearLeft(dto.getTreadRearLeft());
        check.setTreadRearRight(dto.getTreadRearRight());
        check.setPressureFL(dto.getPressureFL());
        check.setPressureFR(dto.getPressureFR());
        check.setPressureRL(dto.getPressureRL());
        check.setPressureRR(dto.getPressureRR());
        check.setWearPattern(dto.getWearPattern());
        check.setShockAbsorbers(dto.getShockAbsorbers());
        return check;
    }
}
