package dev.sijaja.serviceheft.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.sijaja.serviceheft.dto.CriticalIssuesDto;
import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.model.enums.Part;
import dev.sijaja.serviceheft.repository.BeltHoseCheckRepository;
import dev.sijaja.serviceheft.repository.BrakeCheckRepository;
import dev.sijaja.serviceheft.repository.ElectricCheckRepository;
import dev.sijaja.serviceheft.repository.EngineCheckRepository;
import dev.sijaja.serviceheft.repository.HvacCheckRepository;
import dev.sijaja.serviceheft.repository.MaintenanceRepository;
import dev.sijaja.serviceheft.repository.RustCheckRepository;
import dev.sijaja.serviceheft.repository.TireCheckRepository;

@Service
public class CriticalIssuesService {
    BeltHoseCheckRepository beltRepo;
    BrakeCheckRepository brakeRepo;
    EngineCheckRepository engineRepo;
    MaintenanceRepository repo;
    ElectricCheckRepository electricRepo;
    HvacCheckRepository hvacRepo;
    RustCheckRepository rustRepo;
    TireCheckRepository tireRepo;

    public CriticalIssuesService(BeltHoseCheckRepository beltRepo, BrakeCheckRepository brakeRepo,
            EngineCheckRepository engineRepo, MaintenanceRepository repo, ElectricCheckRepository electricRepo,
            HvacCheckRepository hvacRepo, RustCheckRepository rustRepo, TireCheckRepository tireRepo) {
        this.beltRepo = beltRepo;
        this.brakeRepo = brakeRepo;
        this.engineRepo = engineRepo;
        this.repo = repo;
        this.electricRepo = electricRepo;
        this.hvacRepo = hvacRepo;
        this.rustRepo = rustRepo;
        this.tireRepo = tireRepo;
    }

    public List<CriticalIssuesDto> getCriticalIssues(int carId) {
        
        
        List<CriticalIssuesDto> issues = new ArrayList<>();

        beltRepo.findCriticalByCarId(carId).forEach(b -> {
            if (b.getHeaterHoses() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Heater Hoses", b.getHeaterHoses().toString()));
            }
            if (b.getRadiatorHoses() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Radiant Hoses", b.getRadiatorHoses().toString()));
            }
            if (b.getSerpentineBelt() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Serpentine Belt", b.getSerpentineBelt().toString()));
            }
            if (b.getTimingBelt() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Timing Belt", b.getTimingBelt().toString()));
            }
        });

        brakeRepo.findCriticalByCarId(carId).forEach(b -> {
            if (b.getBrakeLines() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Brake Lines", b.getBrakeLines().toString()));
            }
            if (b.getFrontRotorsCon() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Front Rotors", b.getFrontRotorsCon().toString()));
            }
            if (b.getRearRotorsCon() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Rear Rotors", b.getRearRotorsCon().toString()));
            }
        });

        engineRepo.findCriticalByCarId(carId).forEach(b -> {
            if (b.getBrakeFluidColor() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Brake FLuid", b.getBrakeFluidColor().toString()));
            }
            if (b.getCoolantCondition() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Coolant", b.getCoolantCondition().toString()));
            }
            if (b.getEngineStatus() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Engine", b.getEngineStatus().toString()));
            }
            if (b.getOilCondition() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Oil", b.getOilCondition().toString()));
            }
        });

        repo.findCriticalByCarId(carId).forEach(m ->
            issues.add(new CriticalIssuesDto(
                "Maintenance",
                m.getCarCondition().toString()
            ))
        );

        electricRepo.findCriticalByCarId(carId).forEach(b -> {
            if (b.getTerminals() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Terminals", b.getTerminals().toString()));
            }
        });

        hvacRepo.findCriticalByCarId(carId).forEach(b -> {
            if (b.getAcPerformance() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("AC Performance", b.getAcPerformance().toString()));
            }
            if (b.getBlowerMotor() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Blower Motor", b.getBlowerMotor().toString()));
            }
            if (b.getHeatPerformance() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Heat Performance", b.getHeatPerformance().toString()));
            }
        });

        rustRepo.findCriticalByCarId(carId).forEach(b -> {
            if (b.getWindowSeals() == Part.TOREPLACE) {
                issues.add(new CriticalIssuesDto("Window Seals", b.getWindowSeals().toString()));
            }
            if (b.getDoorBottom() == Part.TOREPLACE) {
                issues.add(new CriticalIssuesDto("Door bottom", b.getDoorBottom().toString()));
            }
            if (b.getExhaustArea() == Part.TOREPLACE) {
                issues.add(new CriticalIssuesDto("Exhaust Area", b.getExhaustArea().toString()));
            }
            if (b.getFenders() == Part.TOREPLACE) {
                issues.add(new CriticalIssuesDto("Fenders", b.getFenders().toString()));
            }
            if (b.getHoodEdges() == Part.TOREPLACE) {
                issues.add(new CriticalIssuesDto("Hood Edges", b.getHoodEdges().toString()));
            }
            if (b.getRoofEdges() == Part.TOREPLACE) {
                issues.add(new CriticalIssuesDto("Roof Edges", b.getRoofEdges().toString()));
            }
            if (b.getSideSkirts() == Part.TOREPLACE) {
                issues.add(new CriticalIssuesDto("Side Skirts", b.getSideSkirts().toString()));
            }
            if (b.getSuspension() == Part.TOREPLACE) {
                issues.add(new CriticalIssuesDto("Suspension", b.getSuspension().toString()));
            }
            if (b.getTrunkFloor() == Part.TOREPLACE) {
                issues.add(new CriticalIssuesDto("Trunk Floor", b.getTrunkFloor().toString()));
            }
            if (b.getUnderbody() == Part.TOREPLACE) {
                issues.add(new CriticalIssuesDto("Underbody", b.getUnderbody().toString()));
            }
            if (b.getWheelArches() == Part.TOREPLACE) {
                issues.add(new CriticalIssuesDto("Wheel Arches", b.getWheelArches().toString()));
            }
        });
        
        tireRepo.findCriticalByCarId(carId).forEach(b -> {
            if (b.getShockAbsorbers() == Condition.POOR) {
                issues.add(new CriticalIssuesDto("Shock Absorbers", b.getShockAbsorbers().toString()));
            }
        });



        return issues;
    }
}
