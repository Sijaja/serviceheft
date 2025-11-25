package dev.sijaja.serviceheft.dto.addMaintenance;

import java.time.LocalDate;

import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.model.enums.Type;

public class MaintenanceDTO {
    private int carId;
    private Condition carCondition;
    private LocalDate mtncDate;
    private LocalDate nextDate;
    private Integer currentMileage;
    private Integer nextMileage;
    private double cost;
    private Type mtncType;
    private String inspectionNotes;

    private BeltHoseCheckDTO beltHoseCheck;
    private BodyCheckDTO bodyCheck;
    private BrakeCheckDTO brakeCheck;
    private ElectricCheckDTO electricCheck;
    private EmmisionCheckDTO emmisionCheck;
    private EngineCheckDTO engineCheck;
    private FilterCheckDTO filterCheck;
    private HvacCheckDTO hvacCheck;
    private RustCheckDTO rustCheck;
    private TireCheckDTO tireCheck;

    public MaintenanceDTO(int carId, Condition carCondition, LocalDate mtncDate, LocalDate nextDate,
            Integer currentMileage, Integer nextMileage, double cost, Type mtncType, String inspectionNotes,
            BeltHoseCheckDTO beltHoseCheck, BodyCheckDTO bodyCheck, BrakeCheckDTO brakeCheck,
            ElectricCheckDTO electricCheck, EmmisionCheckDTO emmisionCheck, EngineCheckDTO engineCheck,
            FilterCheckDTO filterCheck, HvacCheckDTO hvacCheck, RustCheckDTO rustCheck, TireCheckDTO tireCheck) {
        this.carId = carId;
        this.carCondition = carCondition;
        this.mtncDate = mtncDate;
        this.nextDate = nextDate;
        this.currentMileage = currentMileage;
        this.nextMileage = nextMileage;
        this.cost = cost;
        this.mtncType = mtncType;
        this.inspectionNotes = inspectionNotes;
        this.beltHoseCheck = beltHoseCheck;
        this.bodyCheck = bodyCheck;
        this.brakeCheck = brakeCheck;
        this.electricCheck = electricCheck;
        this.emmisionCheck = emmisionCheck;
        this.engineCheck = engineCheck;
        this.filterCheck = filterCheck;
        this.hvacCheck = hvacCheck;
        this.rustCheck = rustCheck;
        this.tireCheck = tireCheck;
    }

    public MaintenanceDTO() {
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Condition getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(Condition carCondition) {
        this.carCondition = carCondition;
    }

    public LocalDate getMtncDate() {
        return mtncDate;
    }

    public void setMtncDate(LocalDate mtncDate) {
        this.mtncDate = mtncDate;
    }

    public LocalDate getNextDate() {
        return nextDate;
    }

    public void setNextDate(LocalDate nextDate) {
        this.nextDate = nextDate;
    }

    public Integer getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(Integer currentMileage) {
        this.currentMileage = currentMileage;
    }

    public Integer getNextMileage() {
        return nextMileage;
    }

    public void setNextMileage(Integer nextMileage) {
        this.nextMileage = nextMileage;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Type getMtncType() {
        return mtncType;
    }

    public void setMtncType(Type mtncType) {
        this.mtncType = mtncType;
    }

    public String getInspectionNotes() {
        return inspectionNotes;
    }

    public void setInspectionNotes(String inspectionNotes) {
        this.inspectionNotes = inspectionNotes;
    }

    public BeltHoseCheckDTO getBeltHoseCheck() {
        return beltHoseCheck;
    }

    public void setBeltHoseCheck(BeltHoseCheckDTO beltHoseCheck) {
        this.beltHoseCheck = beltHoseCheck;
    }

    public BodyCheckDTO getBodyCheck() {
        return bodyCheck;
    }

    public void setBodyCheck(BodyCheckDTO bodyCheck) {
        this.bodyCheck = bodyCheck;
    }

    public BrakeCheckDTO getBrakeCheck() {
        return brakeCheck;
    }

    public void setBrakeCheck(BrakeCheckDTO brakeCheck) {
        this.brakeCheck = brakeCheck;
    }

    public ElectricCheckDTO getElectricCheck() {
        return electricCheck;
    }

    public void setElectricCheck(ElectricCheckDTO electricCheck) {
        this.electricCheck = electricCheck;
    }

    public EmmisionCheckDTO getEmmisionCheck() {
        return emmisionCheck;
    }

    public void setEmmisionCheck(EmmisionCheckDTO emmisionCheck) {
        this.emmisionCheck = emmisionCheck;
    }

    public EngineCheckDTO getEngineCheck() {
        return engineCheck;
    }

    public void setEngineCheck(EngineCheckDTO engineCheck) {
        this.engineCheck = engineCheck;
    }

    public FilterCheckDTO getFilterCheck() {
        return filterCheck;
    }

    public void setFilterCheck(FilterCheckDTO filterCheck) {
        this.filterCheck = filterCheck;
    }

    public HvacCheckDTO getHvacCheck() {
        return hvacCheck;
    }

    public void setHvacCheck(HvacCheckDTO hvacCheck) {
        this.hvacCheck = hvacCheck;
    }

    public RustCheckDTO getRustCheck() {
        return rustCheck;
    }

    public void setRustCheck(RustCheckDTO rustCheck) {
        this.rustCheck = rustCheck;
    }

    public TireCheckDTO getTireCheck() {
        return tireCheck;
    }

    public void setTireCheck(TireCheckDTO tireCheck) {
        this.tireCheck = tireCheck;
    }
    

}
