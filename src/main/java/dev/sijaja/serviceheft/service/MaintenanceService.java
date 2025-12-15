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
import dev.sijaja.serviceheft.dto.BrakeTireRatingDTO;
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
import dev.sijaja.serviceheft.dto.addMaintenance.BeltHoseCheckDTO;
import dev.sijaja.serviceheft.dto.addMaintenance.BodyCheckDTO;
import dev.sijaja.serviceheft.dto.addMaintenance.BrakeCheckDTO;
import dev.sijaja.serviceheft.dto.addMaintenance.CostsDTO;
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
import dev.sijaja.serviceheft.model.Costs;
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
import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.model.enums.Level;
import dev.sijaja.serviceheft.model.enums.Part;
import dev.sijaja.serviceheft.repository.CarRepository;
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
    private final CarRepository carRepo;

    public MaintenanceService(MaintenanceRepository repo, OwnerRepository ownerRepo, UserRepository userRepo, WorkshopRepository workshopRepo, CarRepository carRepo) {
        this.repo = repo;
        this.ownerRepo = ownerRepo;
        this.userRepo = userRepo;
        this.workshopRepo = workshopRepo;
        this.carRepo = carRepo;
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
        mtnc.setCosts(toCosts(dto.getCosts()));
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

    private Costs toCosts(CostsDTO dto) {
        Costs check = new Costs();
        check.setBeltsHosesCost(dto.getBeltsHosesCost());
        check.setBodyPartsCost(dto.getBodyPartsCost());
        check.setBrakesCost(dto.getBrakesCost());
        check.setElectricCost(dto.getElectricCost());
        check.setEngineCost(dto.getEngineCost());
        check.setExhaustCost(dto.getExhaustCost());
        check.setFiltersCost(dto.getFiltersCost());
        check.setHvacCost(dto.getHvacCost());
        check.setRostCost(dto.getRostCost());
        check.setTiresCost(dto.getTiresCost());
        return check;
    }

    // Calculate tread penalty based on depth, this will be subtracted from tire score
    public int treadPenalty(Double treadDepth) {
        if (treadDepth == null) {
            return 40;
        }
        if (treadDepth >= 6) {
            return 0;
        }
        if (treadDepth >= 4) {
            return 5;
        }
        if (treadDepth >= 3) {
            return 15;
        }
        if (treadDepth >= 1.6) {
            return 25;
        }
        if (treadDepth < 1.6) {
            return 40;
        }
        return 40;
    }

    // Calculate penalty based on condition enum, this will be subtracted from the score
    public int conditionPenalty(Condition condition, int optimal, int fair, int poor) {
        if (condition.equals(Condition.REPLACED) || condition.equals(Condition.OPTIMAL)) {
            return optimal;
        }
        if (condition.equals(Condition.FAIR)) {
            return fair;
        }
        if (condition.equals(Condition.POOR)) {
            return poor;
        }
        return 0;
    }

    // Calculate penalty based on Level enum, this will be subtracted from the score
    public int levelPenalty(Level level, int optimal, int high, int low) {
        if (level.equals(Level.NOT_CHECKED) || level.equals(Level.OPTIMAL)) {
            return optimal;
        }
        if (level.equals(Level.HIGH)) {
            return high;
        }
        if (level.equals(Level.LOW)) {
            return low;
        }
        return 0;
    }

    public int partPenalty(Part part, int good, int okay, int toReplace, int replaced, int notChecked) {
        if (part.equals(Part.GOOD)) {
            return good;
        }
        if (part.equals(Part.OKAY)) {
            return okay;
        }
        if (part.equals(Part.TOREPLACE)) {
            return toReplace;
        }
        if (part.equals(Part.REPLACED)) {
            return replaced;
        }
        if (part.equals(Part.NOT_CHECKED)) {
            return notChecked;
        }
        return 0;
    }
    // Main method to calculate brakes and tires score
    public Optional<Integer> getBrakesAndTiresScore(Integer carId, Integer ownerId, List<Maintenance> maintenanceHistory) {
        BrakeTireRatingDTO dto = new BrakeTireRatingDTO();

        int fieldsFilled = 0;
        int tireScore = 40;
        int brakeScore = 60;

        // For each field, find the most recent non-null value
        for (Maintenance entry : maintenanceHistory) {
            if (dto.getTreadFrontLeft() == null && entry.getTireCheck().getTreadFrontLeft() != null) {
                dto.setTreadFrontLeft(entry.getTireCheck().getTreadFrontLeft());
                tireScore -= treadPenalty(dto.getTreadFrontLeft());
                if (tireScore < 0) {
                    tireScore = 0;
                }
                fieldsFilled++;
            }
            if (dto.getTreadFrontRight() == null && entry.getTireCheck().getTreadFrontRight() != null) {
                dto.setTreadFrontRight(entry.getTireCheck().getTreadFrontRight());
                tireScore -= treadPenalty(dto.getTreadFrontRight());
                if (tireScore < 0) {
                    tireScore = 0;
                }
                fieldsFilled++;
            }
            if (dto.getTreadRearLeft() == null && entry.getTireCheck().getTreadRearLeft() != null) {
                dto.setTreadRearLeft(entry.getTireCheck().getTreadRearLeft());
                tireScore -= treadPenalty(dto.getTreadRearLeft());
                if (tireScore < 0) {
                    tireScore = 0;
                }
                fieldsFilled++;
            }
            if (dto.getTreadRearRight() == null && entry.getTireCheck().getTreadRearRight() != null) {
                dto.setTreadRearRight(entry.getTireCheck().getTreadRearRight());
                tireScore -= treadPenalty(dto.getTreadRearRight());
                if (tireScore < 0) {
                    tireScore = 0;
                }
                fieldsFilled++;
            }
            if (dto.getfPadThickness() == null && entry.getBrakeCheck().getfPadThickness() != null) {
                dto.setfPadThickness(entry.getBrakeCheck().getfPadThickness());
                if (dto.getfPadThickness() >= 4) {
                    brakeScore -= 0;
                } else if (dto.getfPadThickness() >= 3) {
                    brakeScore -= 4;
                } else if (dto.getfPadThickness() >= 2) {
                    brakeScore -= 8;
                } else if (dto.getfPadThickness() < 2) {
                    brakeScore -= 12;
                }
                if (brakeScore < 0) {
                    brakeScore = 0;
                }
                fieldsFilled++;
            }
            if (dto.getrPadThickness() == null && entry.getBrakeCheck().getrPadThickness() != null) {
                dto.setrPadThickness(entry.getBrakeCheck().getrPadThickness());
                if (dto.getrPadThickness() >= 4) {
                    brakeScore -= 0;
                } else if (dto.getrPadThickness() >= 3) {
                    brakeScore -= 2;
                } else if (dto.getrPadThickness() >= 2) {
                    brakeScore -= 5;
                } else if (dto.getrPadThickness() < 2) {
                    brakeScore -= 8;
                }
                if (brakeScore < 0) {
                    brakeScore = 0;
                }
                fieldsFilled++;
            }
            if (dto.getFrontRotorsCon() == null && entry.getBrakeCheck().getFrontRotorsCon() != null) {
                dto.setFrontRotorsCon(entry.getBrakeCheck().getFrontRotorsCon());
                brakeScore -= conditionPenalty(dto.getFrontRotorsCon(), 0, 6, 12);
                if (brakeScore < 0) {
                    brakeScore = 0;
                }
                fieldsFilled++;
            }
            if (dto.getRearRotorsCon() == null && entry.getBrakeCheck().getRearRotorsCon() != null) {
                dto.setRearRotorsCon(entry.getBrakeCheck().getRearRotorsCon());
                brakeScore -= conditionPenalty(dto.getRearRotorsCon(), 0, 6, 12);
                if (brakeScore < 0) {
                    brakeScore = 0;
                }
                fieldsFilled++;
            }
            if (dto.getBrakeLines() == null && entry.getBrakeCheck().getBrakeLines() != null) {
                dto.setBrakeLines(entry.getBrakeCheck().getBrakeLines());
                brakeScore -= conditionPenalty(dto.getBrakeLines(), 0, 6, 12);
                if (brakeScore < 0) {
                    brakeScore = 0;
                }
                fieldsFilled++;
            }
            // Early exit if all fields are populated
            if (fieldsFilled == 9) {
                break;
            }
        }

        return Optional.of(brakeScore + tireScore);
    }

    public Optional<Integer> getDrivetrainScore(Integer carId, Integer ownerId, List<Maintenance> maintenanceHistory) {
        // Implementation similar to getBrakesAndTiresScore

        EngineCheck engineCheck = new EngineCheck();
        Maintenance m = new Maintenance();
        m.setEngineCheck(engineCheck);

        int fieldsFilled = 0;
        int critical = 60;
        int important = 30;
        int minor = 10;
        boolean oilReplaced = false;

        for (Maintenance entry : maintenanceHistory) {
            //Critical Issues
            if (m.getEngineCheck().getEngineStatus() == null && entry.getEngineCheck().getEngineStatus() != null) {
                m.getEngineCheck().setEngineStatus(entry.getEngineCheck().getEngineStatus());
                critical -= conditionPenalty(m.getEngineCheck().getEngineStatus(), 0, 15, 25);
                if (critical < 0) {
                    critical = 0;
                }
                fieldsFilled++;
            }
            if (m.getEngineCheck().getOilLevel() == null && entry.getEngineCheck().getOilLevel() != null) {
                m.getEngineCheck().setOilLevel(entry.getEngineCheck().getOilLevel());
                critical -= levelPenalty(m.getEngineCheck().getOilLevel(), 0, 5, 10);
                if (critical < 0) {
                    critical = 0;
                }
                fieldsFilled++;
            }
            if (m.getEngineCheck().getOilCondition() == null && entry.getEngineCheck().getOilCondition() != null) {
                m.getEngineCheck().setOilCondition(entry.getEngineCheck().getOilCondition());
                critical -= conditionPenalty(m.getEngineCheck().getOilCondition(), 0, 5, 10);
                if (critical < 0) {
                    critical = 0;
                }
                fieldsFilled++;
            }
            if (m.getEngineCheck().getCoolantLevel() == null && entry.getEngineCheck().getCoolantLevel() != null) {
                m.getEngineCheck().setCoolantLevel(entry.getEngineCheck().getCoolantLevel());
                critical -= levelPenalty(m.getEngineCheck().getCoolantLevel(), 0, 3, 8);
                if (critical < 0) {
                    critical = 0;
                }
                fieldsFilled++;
            }
            if (m.getEngineCheck().getBrakeFluidLevel() == null && entry.getEngineCheck().getBrakeFluidLevel() != null) {
                m.getEngineCheck().setBrakeFluidLevel(entry.getEngineCheck().getBrakeFluidLevel());
                critical -= levelPenalty(m.getEngineCheck().getBrakeFluidLevel(), 0, 2, 7);
                if (critical < 0) {
                    critical = 0;
                }
                fieldsFilled++;
            }
            //Important Issues
            if (oilReplaced == false && entry.getEngineCheck().isOilReplaced() == true) {
                m.getEngineCheck().setOilReplaced(true);
                oilReplaced = true;
                if (entry.getEngineCheck().isOilFilter() == false) {
                    important -= 8;
                }
                if (important < 0) {
                    important = 0;
                }
                fieldsFilled++;
            }
            if (m.getEngineCheck().getCoolantCondition() == null && entry.getEngineCheck().getCoolantCondition() != null) {
                m.getEngineCheck().setCoolantCondition(entry.getEngineCheck().getCoolantCondition());
                important -= conditionPenalty(m.getEngineCheck().getCoolantCondition(), 0, 3, 6);
                if (important < 0) {
                    important = 0;
                }
                fieldsFilled++;
            }
            if (m.getEngineCheck().getGearFluid() == null && entry.getEngineCheck().getGearFluid() != null) {
                m.getEngineCheck().setGearFluid(entry.getEngineCheck().getGearFluid());
                important -= levelPenalty(m.getEngineCheck().getGearFluid(), 0, 2, 7);
                if (important < 0) {
                    important = 0;
                }
                fieldsFilled++;
            }
            if (m.getEngineCheck().getSteeringFluid() == null && entry.getEngineCheck().getSteeringFluid() != null) {
                m.getEngineCheck().setSteeringFluid(entry.getEngineCheck().getSteeringFluid());
                important -= levelPenalty(m.getEngineCheck().getSteeringFluid(), 0, 1, 4);
                if (important < 0) {
                    important = 0;
                }
                fieldsFilled++;
            }
            //Minor Issues
            if (m.getEngineCheck().getBrakeFluidColor() == null && entry.getEngineCheck().getBrakeFluidColor() != null) {
                m.getEngineCheck().setBrakeFluidColor(entry.getEngineCheck().getBrakeFluidColor());
                minor -= conditionPenalty(m.getEngineCheck().getBrakeFluidColor(), 0, 3, 5);
                if (minor < 0) {
                    minor = 0;
                }
                fieldsFilled++;
            }
            if (m.getEngineCheck().getWashFluid() == null && entry.getEngineCheck().getWashFluid() != null) {
                m.getEngineCheck().setWashFluid(entry.getEngineCheck().getWashFluid());
                minor -= levelPenalty(m.getEngineCheck().getWashFluid(), 0, 1, 5);
                if (minor < 0) {
                    minor = 0;
                }
                fieldsFilled++;
            }
            // Early exit if all relevant fields are populated
            if (fieldsFilled == 11) {
                break; // total drivetrain fields

            }
        }

        return Optional.of(critical + important + minor);
    }

    public Optional<Integer> getChasisScore(Integer carId, Integer ownerId, List<Maintenance> maintenanceHistory) {
        RustCheck rustCheck = new RustCheck();
        Maintenance m = new Maintenance();
        m.setRustCheck(rustCheck);

        int fieldsFilled = 0;
        int score = 100;

        for (Maintenance entry : maintenanceHistory) {
            if (m.getRustCheck().getWheelArches() == null && entry.getRustCheck().getWheelArches() != null) {
                m.getRustCheck().setWheelArches(entry.getRustCheck().getWheelArches());
                score -= partPenalty(m.getRustCheck().getWheelArches(), 0, 5, 10, 0, 3);
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            if (m.getRustCheck().getUnderbody() == null && entry.getRustCheck().getUnderbody() != null) {
                m.getRustCheck().setUnderbody(entry.getRustCheck().getUnderbody());
                score -= partPenalty(m.getRustCheck().getUnderbody(), 0, 8, 15, 0, 10);
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            if (m.getRustCheck().getSuspension() == null && entry.getRustCheck().getSuspension() != null) {
                m.getRustCheck().setSuspension(entry.getRustCheck().getSuspension());
                score -= partPenalty(m.getRustCheck().getSuspension(), 0, 8, 15, 0, 8);
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            if (m.getRustCheck().getTrunkFloor() == null && entry.getRustCheck().getTrunkFloor() != null) {
                m.getRustCheck().setTrunkFloor(entry.getRustCheck().getTrunkFloor());
                score -= partPenalty(m.getRustCheck().getTrunkFloor(), 0, 5, 10, 0, 3);
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            if (m.getRustCheck().getFenders() == null && entry.getRustCheck().getFenders() != null) {
                m.getRustCheck().setFenders(entry.getRustCheck().getFenders());
                score -= partPenalty(m.getRustCheck().getFenders(), 0, 5, 10, 0, 2);
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            if (m.getRustCheck().getDoorBottom() == null && entry.getRustCheck().getDoorBottom() != null) {
                m.getRustCheck().setDoorBottom(entry.getRustCheck().getDoorBottom());
                score -= partPenalty(m.getRustCheck().getDoorBottom(), 0, 4, 8, 0, 2);
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            if (m.getRustCheck().getRoofEdges() == null && entry.getRustCheck().getRoofEdges() != null) {
                m.getRustCheck().setRoofEdges(entry.getRustCheck().getRoofEdges());
                score -= partPenalty(m.getRustCheck().getRoofEdges(), 0, 4, 7, 0, 2);
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            if (m.getRustCheck().getWindowSeals() == null && entry.getRustCheck().getWindowSeals() != null) {
                m.getRustCheck().setWindowSeals(entry.getRustCheck().getWindowSeals());
                score -= partPenalty(m.getRustCheck().getWindowSeals(), 0, 3, 5, 0, 2);
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            if (m.getRustCheck().getHoodEdges() == null && entry.getRustCheck().getHoodEdges() != null) {
                m.getRustCheck().setHoodEdges(entry.getRustCheck().getHoodEdges());
                score -= partPenalty(m.getRustCheck().getHoodEdges(), 0, 3, 5, 0, 1);
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            if (m.getRustCheck().getSideSkirts() == null && entry.getRustCheck().getSideSkirts() != null) {
                m.getRustCheck().setSideSkirts(entry.getRustCheck().getSideSkirts());
                score -= partPenalty(m.getRustCheck().getSideSkirts(), 0, 3, 5, 0, 1);
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            if (m.getRustCheck().getExhaustArea() == null && entry.getRustCheck().getExhaustArea() != null) {
                m.getRustCheck().setExhaustArea(entry.getRustCheck().getExhaustArea());
                score -= partPenalty(m.getRustCheck().getExhaustArea(), 0, 3, 5, 0, 1);
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            // Early exit if all relevant fields are populated
            if (fieldsFilled == 11) {
                break;

            }
        }

        return Optional.of(score);
    }

    public Optional<Integer> getBeltScore(Integer carId, Integer ownerId, List<Maintenance> maintenanceHistory) {
        BeltHoseCheck beltCheck = new BeltHoseCheck();
        Maintenance m = new Maintenance();
        m.setBeltHoseCheck(beltCheck);

        int fieldsFilled = 0;
        int score = 100;
        for (Maintenance entry : maintenanceHistory) {
            if (m.getBeltHoseCheck().getSerpentineBelt() == null && entry.getBeltHoseCheck().getSerpentineBelt() != null) {
                m.getBeltHoseCheck().setSerpentineBelt(entry.getBeltHoseCheck().getSerpentineBelt());
                score -= conditionPenalty(m.getBeltHoseCheck().getSerpentineBelt(), 0, 15, 30);
                if (m.getBeltHoseCheck().getSerpentineBelt().equals(Condition.NOT_CHECKED)) {
                    score -= 10;
                }
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            if (m.getBeltHoseCheck().getTimingBelt() == null && entry.getBeltHoseCheck().getTimingBelt() != null) {
                m.getBeltHoseCheck().setTimingBelt(entry.getBeltHoseCheck().getTimingBelt());
                score -= conditionPenalty(m.getBeltHoseCheck().getTimingBelt(), 0, 25, 40);
                if (m.getBeltHoseCheck().getTimingBelt().equals(Condition.NOT_CHECKED)) {
                    score -= 20;
                }
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            if (m.getBeltHoseCheck().getRadiatorHoses() == null && entry.getBeltHoseCheck().getRadiatorHoses() != null) {
                m.getBeltHoseCheck().setRadiatorHoses(entry.getBeltHoseCheck().getRadiatorHoses());
                score -= conditionPenalty(m.getBeltHoseCheck().getRadiatorHoses(), 0, 10, 20);
                if (m.getBeltHoseCheck().getRadiatorHoses().equals(Condition.NOT_CHECKED)) {
                    score -= 5;
                }
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            if (m.getBeltHoseCheck().getHeaterHoses() == null && entry.getBeltHoseCheck().getHeaterHoses() != null) {
                m.getBeltHoseCheck().setHeaterHoses(entry.getBeltHoseCheck().getHeaterHoses());
                score -= conditionPenalty(m.getBeltHoseCheck().getHeaterHoses(), 0, 5, 10);
                if (m.getBeltHoseCheck().getHeaterHoses().equals(Condition.NOT_CHECKED)) {
                    score -= 2;
                }
                if (score < 0) {
                    score = 0;
                }
                fieldsFilled++;
            }
            // Early exit if all relevant fields are populated
            if (fieldsFilled == 4) {
                break;

            }
        }
        return Optional.of(score);
    }

    public Optional<HealthScoreDTO> getHealthScore(Integer carId, Integer ownerId) {
        List<Maintenance> maintenanceHistory = repo
                .findByCarIdAndOwnerId(carId, ownerId);
        Collections.reverse(maintenanceHistory);
        Optional<Integer> brakesAndTiresOpt = getBrakesAndTiresScore(carId, ownerId, maintenanceHistory);
        Optional<Integer> drivetrainOpt = getDrivetrainScore(carId, ownerId, maintenanceHistory);
        Optional<Integer> getBeltOpt = getBeltScore(carId, ownerId, maintenanceHistory);
        Optional<Integer> chasisOpt = getChasisScore(carId, ownerId, maintenanceHistory);

        if (brakesAndTiresOpt.isPresent() && drivetrainOpt.isPresent() && getBeltOpt.isPresent() && chasisOpt.isPresent()) {
            int brakesAndTires = brakesAndTiresOpt.get();
            int drivetrain = drivetrainOpt.get();
            int belt = getBeltOpt.get();
            int chasis = chasisOpt.get();
            HealthScoreDTO healthScore = new HealthScoreDTO(brakesAndTires, drivetrain, belt, chasis);
            return Optional.of(healthScore);
        } else {
            return Optional.empty();
        }
    }

    //method to find similar cars
    public Map<Integer, List<Maintenance>> getSimilarCarsMaintenance(int carId) {
        Cars car = carRepo.findById(carId).orElse(null);
        if (car == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }
        List<Integer> similarCarIds = carRepo.findSimilarCarIds(
                car.getManufacturer(),
                car.getModel(),
                car.getMakeYear()
        );
        Map<Integer, List<Maintenance>> similarCarsMaintenance = new HashMap<>();
        for (Integer similarCarId : similarCarIds) {
            List<Maintenance> maintenances = repo.findByCarId(similarCarId);
            similarCarsMaintenance.put(similarCarId, maintenances);
        }
        return similarCarsMaintenance;
    }

    public Optional<List<Double>> CostComparesion(Integer carId, Integer ownerId) {

        List<Maintenance> maintenanceHistory
                = repo.findByCarIdAndOwnerId(carId, ownerId);

        Double motorCost = 0.0;
        Double beltCost = 0.0;
        Double brakeCost = 0.0;
        Double bodyCost = 0.0;
        Double electricCost = 0.0;

        for (Maintenance entry : maintenanceHistory) {
            motorCost += entry.getCosts().getEngineCost();
            beltCost += entry.getCosts().getBeltsHosesCost();
            brakeCost += entry.getCosts().getBrakesCost();
            bodyCost += entry.getCosts().getBodyPartsCost();
            electricCost += entry.getCosts().getElectricCost();
        }
        List<Double> score = Arrays.asList(motorCost, beltCost, brakeCost, bodyCost, electricCost);
        return Optional.of(score);
    }


    public Optional<List<Double>> getAverageCostForSimilarCars(int carId) {
        Cars car = carRepo.findById(carId).orElse(null);
        if (car == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }
        List<Integer> similarCarIds = carRepo.findSimilarCarIds(
                car.getManufacturer(),
                car.getModel(),
                car.getMakeYear()
        );

        // Exclude the current car from comparison
        similarCarIds.remove(Integer.valueOf(carId));

        Double totalMotorCost = 0.0;
        Double totalBeltCost = 0.0;
        Double totalBrakeCost = 0.0;
        Double totalBodyCost = 0.0;
        Double totalElectricCost = 0.0;
        int count = 0;
        for (Integer similarCarId : similarCarIds) {
            List<Maintenance> maintenances = repo.findByCarId(similarCarId);
            if (!maintenances.isEmpty()) {
                count++;
                for (Maintenance entry : maintenances) {
                    if (entry.getCosts() == null) {
                        continue;
                    }
                    totalMotorCost += entry.getCosts().getEngineCost();
                    totalBeltCost += entry.getCosts().getBeltsHosesCost();
                    totalBrakeCost += entry.getCosts().getBrakesCost();
                    totalBodyCost += entry.getCosts().getBodyPartsCost();
                    totalElectricCost += entry.getCosts().getElectricCost();
                }
            }
        }

        if (count == 0) {
            return Optional.of(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
        }

        List<Double> score = Arrays.asList(totalMotorCost/count, totalBeltCost/count, totalBrakeCost/count, totalBodyCost/count, totalElectricCost/count);
        return Optional.of(score);
    }

    public Optional<List<Double>> getAverageCostForAllCars(int carId) {
        List<Maintenance> allMaintenances = repo.findAll();
        Double totalMotorCost = 0.0;
        Double totalBeltCost = 0.0;
        Double totalBrakeCost = 0.0;
        Double totalBodyCost = 0.0;
        Double totalElectricCost = 0.0;
        int count = 0;
        for (Maintenance entry : allMaintenances) {
            if (entry.getCosts() == null) {
                continue;
            }
            count++;
            totalMotorCost += entry.getCosts().getEngineCost();
            totalBeltCost += entry.getCosts().getBeltsHosesCost();
            totalBrakeCost += entry.getCosts().getBrakesCost();
            totalBodyCost += entry.getCosts().getBodyPartsCost();
            totalElectricCost += entry.getCosts().getElectricCost();
        }
        List<Double> score = Arrays.asList(totalMotorCost/count, totalBeltCost/count, totalBrakeCost/count, totalBodyCost/count, totalElectricCost/count);
        return Optional.of(score);
    }

    public Optional<List<Double>> getAverageCostForSameYearCars(int carId) {
        Cars car = carRepo.findById(carId).orElse(null);
        if (car == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }
        List<Integer> sameYearCarIds = carRepo.findCarIdsByMakeYear(car.getMakeYear());

        // Exclude the current car from comparison
        sameYearCarIds.remove(Integer.valueOf(carId));

        Double totalMotorCost = 0.0;
        Double totalBeltCost = 0.0;
        Double totalBrakeCost = 0.0;
        Double totalBodyCost = 0.0;
        Double totalElectricCost = 0.0;
        int count = 0;

        for (Integer yearCarId : sameYearCarIds) {
            List<Maintenance> maintenances = repo.findByCarId(yearCarId);
            if (!maintenances.isEmpty()) {
                count++;
                for (Maintenance entry : maintenances) {
                    if (entry.getCosts() == null) {
                        continue;
                    }
                    totalMotorCost += entry.getCosts().getEngineCost();
                    totalBeltCost += entry.getCosts().getBeltsHosesCost();
                    totalBrakeCost += entry.getCosts().getBrakesCost();
                    totalBodyCost += entry.getCosts().getBodyPartsCost();
                    totalElectricCost += entry.getCosts().getElectricCost();
                }
            }
        }

        if (count == 0) {
            return Optional.of(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
        }

        List<Double> score = Arrays.asList(
            totalMotorCost/count,
            totalBeltCost/count,
            totalBrakeCost/count,
            totalBodyCost/count,
            totalElectricCost/count
        );
        return Optional.of(score);
    }

    public TotalCostComparisonDto compareTotalMaintenanceCosts(int carId) {
        Cars car = carRepo.findById(carId).orElse(null);
        if (car == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }

        // Get total cost for the current car
        List<Maintenance> myCarMaintenances = repo.findByCarId(carId);
        double myCarTotalCost = myCarMaintenances.stream()
                .mapToDouble(Maintenance::getCost)
                .sum();

        // Compare to cars with same model and make year
        List<Integer> sameModelAndYearCarIds = carRepo.findSimilarCarIds(
                car.getManufacturer(),
                car.getModel(),
                car.getMakeYear()
        );

        // Exclude the current car from comparison
        sameModelAndYearCarIds.remove(Integer.valueOf(carId));

        double sameModelAndYearTotal = 0.0;
        int sameModelAndYearCount = 0;

        for (Integer similarCarId : sameModelAndYearCarIds) {
            List<Maintenance> maintenances = repo.findByCarId(similarCarId);
            if (!maintenances.isEmpty()) {
                double carTotal = maintenances.stream()
                        .mapToDouble(Maintenance::getCost)
                        .sum();
                sameModelAndYearTotal += carTotal;
                sameModelAndYearCount++;
            }
        }

        double sameModelAndYearAverage = sameModelAndYearCount > 0
                ? sameModelAndYearTotal / sameModelAndYearCount
                : 0.0;

        // Compare to cars with same make year only
        List<Integer> sameYearCarIds = carRepo.findCarIdsByMakeYear(car.getMakeYear());

        // Exclude the current car from comparison
        sameYearCarIds.remove(Integer.valueOf(carId));

        double sameYearTotal = 0.0;
        int sameYearCount = 0;

        for (Integer yearCarId : sameYearCarIds) {
            List<Maintenance> maintenances = repo.findByCarId(yearCarId);
            if (!maintenances.isEmpty()) {
                double carTotal = maintenances.stream()
                        .mapToDouble(Maintenance::getCost)
                        .sum();
                sameYearTotal += carTotal;
                sameYearCount++;
            }
        }

        double sameYearAverage = sameYearCount > 0
                ? sameYearTotal / sameYearCount
                : 0.0;

        return new TotalCostComparisonDto(
                myCarTotalCost,
                sameModelAndYearAverage,
                sameYearAverage,
                sameModelAndYearCount,
                sameYearCount
        );
    }

    public MaintenanceCountComparisonDto compareMaintenanceCounts(int carId) {
        Cars car = carRepo.findById(carId).orElse(null);
        if (car == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }

        // Get maintenance count for the current car
        List<Maintenance> myCarMaintenances = repo.findByCarId(carId);
        int myCarMaintenanceCount = myCarMaintenances.size();

        // Compare to cars with same model and make year
        List<Integer> sameModelAndYearCarIds = carRepo.findSimilarCarIds(
                car.getManufacturer(),
                car.getModel(),
                car.getMakeYear()
        );

        // Exclude the current car from comparison
        sameModelAndYearCarIds.remove(Integer.valueOf(carId));

        int sameModelAndYearTotalCount = 0;
        int sameModelAndYearCarCount = 0;

        for (Integer similarCarId : sameModelAndYearCarIds) {
            List<Maintenance> maintenances = repo.findByCarId(similarCarId);
            if (!maintenances.isEmpty()) {
                sameModelAndYearTotalCount += maintenances.size();
                sameModelAndYearCarCount++;
            }
        }

        double sameModelAndYearAverage = sameModelAndYearCarCount > 0
                ? (double) sameModelAndYearTotalCount / sameModelAndYearCarCount
                : 0.0;

        // Compare to cars with same make year only
        List<Integer> sameYearCarIds = carRepo.findCarIdsByMakeYear(car.getMakeYear());

        // Exclude the current car from comparison
        sameYearCarIds.remove(Integer.valueOf(carId));

        int sameYearTotalCount = 0;
        int sameYearCarCount = 0;

        for (Integer yearCarId : sameYearCarIds) {
            List<Maintenance> maintenances = repo.findByCarId(yearCarId);
            if (!maintenances.isEmpty()) {
                sameYearTotalCount += maintenances.size();
                sameYearCarCount++;
            }
        }

        double sameYearAverage = sameYearCarCount > 0
                ? (double) sameYearTotalCount / sameYearCarCount
                : 0.0;

        return new MaintenanceCountComparisonDto(
                myCarMaintenanceCount,
                sameModelAndYearAverage,
                sameYearAverage,
                sameModelAndYearCarCount,
                sameYearCarCount
        );
    }

    public MileageComparisonDto compareMileage(int carId) {
        Cars car = carRepo.findById(carId).orElse(null);
        if (car == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }

        // Get mileage for the current car
        int myCarMileage = car.getMileage() != null ? car.getMileage() : 0;

        // Compare to cars with same model and make year
        List<Integer> sameModelAndYearCarIds = carRepo.findSimilarCarIds(
                car.getManufacturer(),
                car.getModel(),
                car.getMakeYear()
        );

        // Exclude the current car from comparison
        sameModelAndYearCarIds.remove(Integer.valueOf(carId));

        int sameModelAndYearTotalMileage = 0;
        int sameModelAndYearCarCount = 0;

        for (Integer similarCarId : sameModelAndYearCarIds) {
            Cars similarCar = carRepo.findById(similarCarId).orElse(null);
            if (similarCar != null && similarCar.getMileage() != null) {
                sameModelAndYearTotalMileage += similarCar.getMileage();
                sameModelAndYearCarCount++;
            }
        }

        double sameModelAndYearAverage = sameModelAndYearCarCount > 0
                ? (double) sameModelAndYearTotalMileage / sameModelAndYearCarCount
                : 0.0;

        // Compare to cars with same make year only
        List<Integer> sameYearCarIds = carRepo.findCarIdsByMakeYear(car.getMakeYear());

        // Exclude the current car from comparison
        sameYearCarIds.remove(Integer.valueOf(carId));

        int sameYearTotalMileage = 0;
        int sameYearCarCount = 0;

        for (Integer yearCarId : sameYearCarIds) {
            Cars yearCar = carRepo.findById(yearCarId).orElse(null);
            if (yearCar != null && yearCar.getMileage() != null) {
                sameYearTotalMileage += yearCar.getMileage();
                sameYearCarCount++;
            }
        }

        double sameYearAverage = sameYearCarCount > 0
                ? (double) sameYearTotalMileage / sameYearCarCount
                : 0.0;

        return new MileageComparisonDto(
                myCarMileage,
                sameModelAndYearAverage,
                sameYearAverage,
                sameModelAndYearCarCount,
                sameYearCarCount
        );
    }

    public CostPerKmComparisonDto compareCostPerThousandKm(int carId) {
        Cars car = carRepo.findById(carId).orElse(null);
        if (car == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }

        // Calculate cost per 1000 km for the current car
        List<Maintenance> myCarMaintenances = repo.findByCarId(carId);
        double myCarTotalCost = myCarMaintenances.stream()
                .mapToDouble(Maintenance::getCost)
                .sum();
        int myCarMileage = car.getMileage() != null ? car.getMileage() : 0;
        double myCarCostPerThousandKm = myCarMileage > 0
                ? (myCarTotalCost / myCarMileage) * 1000
                : 0.0;

        // Compare to cars with same model and make year
        List<Integer> sameModelAndYearCarIds = carRepo.findSimilarCarIds(
                car.getManufacturer(),
                car.getModel(),
                car.getMakeYear()
        );

        // Exclude the current car from comparison
        sameModelAndYearCarIds.remove(Integer.valueOf(carId));

        double sameModelAndYearTotalCostPerKm = 0.0;
        int sameModelAndYearCarCount = 0;

        for (Integer similarCarId : sameModelAndYearCarIds) {
            Cars similarCar = carRepo.findById(similarCarId).orElse(null);
            if (similarCar != null && similarCar.getMileage() != null && similarCar.getMileage() > 0) {
                List<Maintenance> maintenances = repo.findByCarId(similarCarId);
                if (!maintenances.isEmpty()) {
                    double totalCost = maintenances.stream()
                            .mapToDouble(Maintenance::getCost)
                            .sum();
                    double costPerThousandKm = (totalCost / similarCar.getMileage()) * 1000;
                    sameModelAndYearTotalCostPerKm += costPerThousandKm;
                    sameModelAndYearCarCount++;
                }
            }
        }

        double sameModelAndYearAverage = sameModelAndYearCarCount > 0
                ? sameModelAndYearTotalCostPerKm / sameModelAndYearCarCount
                : 0.0;

        // Compare to cars with same make year only
        List<Integer> sameYearCarIds = carRepo.findCarIdsByMakeYear(car.getMakeYear());

        // Exclude the current car from comparison
        sameYearCarIds.remove(Integer.valueOf(carId));

        double sameYearTotalCostPerKm = 0.0;
        int sameYearCarCount = 0;

        for (Integer yearCarId : sameYearCarIds) {
            Cars yearCar = carRepo.findById(yearCarId).orElse(null);
            if (yearCar != null && yearCar.getMileage() != null && yearCar.getMileage() > 0) {
                List<Maintenance> maintenances = repo.findByCarId(yearCarId);
                if (!maintenances.isEmpty()) {
                    double totalCost = maintenances.stream()
                            .mapToDouble(Maintenance::getCost)
                            .sum();
                    double costPerThousandKm = (totalCost / yearCar.getMileage()) * 1000;
                    sameYearTotalCostPerKm += costPerThousandKm;
                    sameYearCarCount++;
                }
            }
        }

        double sameYearAverage = sameYearCarCount > 0
                ? sameYearTotalCostPerKm / sameYearCarCount
                : 0.0;

        return new CostPerKmComparisonDto(
                myCarCostPerThousandKm,
                sameModelAndYearAverage,
                sameYearAverage,
                sameModelAndYearCarCount,
                sameYearCarCount
        );
    }

}

    