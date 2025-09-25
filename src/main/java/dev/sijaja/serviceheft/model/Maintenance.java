package dev.sijaja.serviceheft.model;


import java.time.LocalDate;

import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.model.enums.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="maintenance")
public class Maintenance {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int mtncId;
    private int carId;
    private int workshopId;
    private int engineCheckId;
    private int beltHoseCheckId;
    private int brakeCheckId;
    private int tireCheckId;
    private int electricCheckID;
    private int filterCheckId;
    private int emmisionCheckId;
    private int hvacCheckId;
    @Enumerated(EnumType.STRING)
    private Condition carCondition;
    private String inspectionNotes;
    private LocalDate mtncDate;
    private int currentMileage;
    private int nextMileage;
    private LocalDate nextDate;
    private double cost;
    @Enumerated(EnumType.STRING)
    private Type mtncType;

    public Maintenance() {
    }

    public Maintenance(int beltHoseCheckId, int brakeCheckId, int carId, Condition condition, double cost, int currentMileage, int electricCheckID, int emmisionCheckId, int engineCheckId, int filterCheckId, int hvacCheckId, String inspectionNotes, LocalDate mtncDate, int mtncId, Type mtncType, LocalDate nextDate, int nextMileage, int tireCheckId, int workshopId) {
        this.beltHoseCheckId = beltHoseCheckId;
        this.brakeCheckId = brakeCheckId;
        this.carId = carId;
        this.carCondition = condition;
        this.cost = cost;
        this.currentMileage = currentMileage;
        this.electricCheckID = electricCheckID;
        this.emmisionCheckId = emmisionCheckId;
        this.engineCheckId = engineCheckId;
        this.filterCheckId = filterCheckId;
        this.hvacCheckId = hvacCheckId;
        this.inspectionNotes = inspectionNotes;
        this.mtncDate = mtncDate;
        this.mtncId = mtncId;
        this.mtncType = mtncType;
        this.nextDate = nextDate;
        this.nextMileage = nextMileage;
        this.tireCheckId = tireCheckId;
        this.workshopId = workshopId;
    }

    public int getMtncId() {
        return mtncId;
    }

    public void setMtncId(int mtncId) {
        this.mtncId = mtncId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(int workshopId) {
        this.workshopId = workshopId;
    }

    public int getEngineCheckId() {
        return engineCheckId;
    }

    public void setEngineCheckId(int engineCheckId) {
        this.engineCheckId = engineCheckId;
    }

    public int getBeltHoseCheckId() {
        return beltHoseCheckId;
    }

    public void setBeltHoseCheckId(int beltHoseCheckId) {
        this.beltHoseCheckId = beltHoseCheckId;
    }

    public int getBrakeCheckId() {
        return brakeCheckId;
    }

    public void setBrakeCheckId(int brakeCheckId) {
        this.brakeCheckId = brakeCheckId;
    }

    public int getTireCheckId() {
        return tireCheckId;
    }

    public void setTireCheckId(int tireCheckId) {
        this.tireCheckId = tireCheckId;
    }

    public int getElectricCheckID() {
        return electricCheckID;
    }

    public void setElectricCheckID(int electricCheckID) {
        this.electricCheckID = electricCheckID;
    }

    public int getFilterCheckId() {
        return filterCheckId;
    }

    public void setFilterCheckId(int filterCheckId) {
        this.filterCheckId = filterCheckId;
    }

    public int getEmmisionCheckId() {
        return emmisionCheckId;
    }

    public void setEmmisionCheckId(int emmisionCheckId) {
        this.emmisionCheckId = emmisionCheckId;
    }

    public int getHvacCheckId() {
        return hvacCheckId;
    }

    public void setHvacCheckId(int hvacCheckId) {
        this.hvacCheckId = hvacCheckId;
    }



    public String getInspectionNotes() {
        return inspectionNotes;
    }

    public void setInspectionNotes(String inspectionNotes) {
        this.inspectionNotes = inspectionNotes;
    }

    public LocalDate getMtncDate() {
        return mtncDate;
    }

    public void setMtncDate(LocalDate mtncDate) {
        this.mtncDate = mtncDate;
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

    public Condition getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(Condition carCondition) {
        this.carCondition = carCondition;
    }


}
