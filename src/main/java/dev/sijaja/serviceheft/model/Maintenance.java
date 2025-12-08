package dev.sijaja.serviceheft.model;

import java.time.LocalDate;

import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.model.enums.Status;
import dev.sijaja.serviceheft.model.enums.Type;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mtncId;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Cars car;
    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;
    @Enumerated(EnumType.STRING)
    private Condition carCondition;
    private String inspectionNotes;
    private LocalDate startDate;
    private LocalDate mtncDate;
    private Integer currentMileage;
    private Integer nextMileage;
    private LocalDate nextDate;
    private Double cost;
    @Enumerated(EnumType.STRING)
    private Type mtncType;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Embedded
    private BeltHoseCheck beltHoseCheck;
    @Embedded
    private BodyCheck bodyCheck;
    @Embedded
    private BrakeCheck brakeCheck;
    @Embedded
    private ElectricCheck electricCheck;
    @Embedded
    private EmmisionCheck emmisionCheck;
    @Embedded
    private EngineCheck engineCheck;
    @Embedded
    private FilterCheck filterCheck;
    @Embedded
    private HvacCheck hvacCheck;
    @Embedded
    private RustCheck rustCheck;
    @Embedded
    private TireCheck tireCheck;
    @Embedded
    private Costs costs;

    public Maintenance() {
    }

    public Maintenance(BeltHoseCheck beltHoseCheck, BodyCheck bodyCheck, BrakeCheck brakeCheck, Cars car, Condition carCondition, Double cost, Integer currentMileage, ElectricCheck electricCheck, EmmisionCheck emmisionCheck, EngineCheck engineCheck, FilterCheck filterCheck, HvacCheck hvacCheck, String inspectionNotes, LocalDate mtncDate, int mtncId, Type mtncType, LocalDate nextDate, Integer nextMileage, RustCheck rustCheck, LocalDate startDate, Status status, TireCheck tireCheck, Workshop workshop, Costs costs) {
        this.beltHoseCheck = beltHoseCheck;
        this.bodyCheck = bodyCheck;
        this.brakeCheck = brakeCheck;
        this.car = car;
        this.carCondition = carCondition;
        this.cost = cost;
        this.currentMileage = currentMileage;
        this.electricCheck = electricCheck;
        this.emmisionCheck = emmisionCheck;
        this.engineCheck = engineCheck;
        this.filterCheck = filterCheck;
        this.hvacCheck = hvacCheck;
        this.inspectionNotes = inspectionNotes;
        this.mtncDate = mtncDate;
        this.mtncId = mtncId;
        this.mtncType = mtncType;
        this.nextDate = nextDate;
        this.nextMileage = nextMileage;
        this.rustCheck = rustCheck;
        this.startDate = startDate;
        this.status = status;
        this.tireCheck = tireCheck;
        this.workshop = workshop;
        this.costs = costs;
    }

    public int getMtncId() {
        return mtncId;
    }

    public void setMtncId(int mtncId) {
        this.mtncId = mtncId;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public Condition getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(Condition carCondition) {
        this.carCondition = carCondition;
    }

    public String getInspectionNotes() {
        return inspectionNotes;
    }

    public void setInspectionNotes(String inspectionNotes) {
        this.inspectionNotes = inspectionNotes;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getMtncDate() {
        return mtncDate;
    }

    public void setMtncDate(LocalDate mtncDate) {
        this.mtncDate = mtncDate;
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

    public LocalDate getNextDate() {
        return nextDate;
    }

    public void setNextDate(LocalDate nextDate) {
        this.nextDate = nextDate;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Type getMtncType() {
        return mtncType;
    }

    public void setMtncType(Type mtncType) {
        this.mtncType = mtncType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BeltHoseCheck getBeltHoseCheck() {
        return beltHoseCheck;
    }

    public void setBeltHoseCheck(BeltHoseCheck beltHoseCheck) {
        this.beltHoseCheck = beltHoseCheck;
    }

    public BodyCheck getBodyCheck() {
        return bodyCheck;
    }

    public void setBodyCheck(BodyCheck bodyCheck) {
        this.bodyCheck = bodyCheck;
    }

    public BrakeCheck getBrakeCheck() {
        return brakeCheck;
    }

    public void setBrakeCheck(BrakeCheck brakeCheck) {
        this.brakeCheck = brakeCheck;
    }

    public ElectricCheck getElectricCheck() {
        return electricCheck;
    }

    public void setElectricCheck(ElectricCheck electricCheck) {
        this.electricCheck = electricCheck;
    }

    public EngineCheck getEngineCheck() {
        return engineCheck;
    }

    public void setEngineCheck(EngineCheck engineCheck) {
        this.engineCheck = engineCheck;
    }

    public FilterCheck getFilterCheck() {
        return filterCheck;
    }

    public void setFilterCheck(FilterCheck filterCheck) {
        this.filterCheck = filterCheck;
    }

    public HvacCheck getHvacCheck() {
        return hvacCheck;
    }

    public void setHvacCheck(HvacCheck hvacCheck) {
        this.hvacCheck = hvacCheck;
    }

    public RustCheck getRustCheck() {
        return rustCheck;
    }

    public void setRustCheck(RustCheck rustCheck) {
        this.rustCheck = rustCheck;
    }

    public TireCheck getTireCheck() {
        return tireCheck;
    }

    public void setTireCheck(TireCheck tireCheck) {
        this.tireCheck = tireCheck;
    }

    public EmmisionCheck getEmmisionCheck() {
        return emmisionCheck;
    }

    public void setEmmisionCheck(EmmisionCheck emmisionCheck) {
        this.emmisionCheck = emmisionCheck;
    }

    public Costs getCosts() {
        return costs;
    }

    public void setCosts(Costs costs) {
        this.costs = costs;
    }

}
