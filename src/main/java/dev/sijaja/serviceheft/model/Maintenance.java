package dev.sijaja.serviceheft.model;


import java.time.LocalDate;

import ch.qos.logback.core.status.Status;
import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.model.enums.Type;
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
@Table(name="maintenance")
public class Maintenance {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int mtncId;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Cars car;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;
    @Enumerated(EnumType.STRING)
    private Condition carCondition;
    private String inspectionNotes;
    private LocalDate startDate;
    private LocalDate endDate;
    private int currentMileage;
    private int nextMileage;
    private LocalDate nextDate;
    private double cost;
    @Enumerated(EnumType.STRING)
    private Type mtncType;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Maintenance() {
    }

    public Maintenance(int mtncId, Cars car, Owner owner, Workshop workshop, Condition carCondition,
            String inspectionNotes, LocalDate startDate, LocalDate endDate, int currentMileage, int nextMileage,
            LocalDate nextDate, double cost, Type mtncType, Status status) {
        this.mtncId = mtncId;
        this.car = car;
        this.owner = owner;
        this.workshop = workshop;
        this.carCondition = carCondition;
        this.inspectionNotes = inspectionNotes;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentMileage = currentMileage;
        this.nextMileage = nextMileage;
        this.nextDate = nextDate;
        this.cost = cost;
        this.mtncType = mtncType;
        this.status = status;
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

    public int getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;
    }

    public int getNextMileage() {
        return nextMileage;
    }

    public void setNextMileage(int nextMileage) {
        this.nextMileage = nextMileage;
    }

    public LocalDate getNextDate() {
        return nextDate;
    }

    public void setNextDate(LocalDate nextDate) {
        this.nextDate = nextDate;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
