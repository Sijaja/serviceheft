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
    private int workshopId;
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

    public Maintenance(Cars car, Condition carCondition, double cost, int currentMileage, String inspectionNotes, LocalDate mtncDate, int mtncId, Type mtncType, LocalDate nextDate, int nextMileage, int workshopId) {
        this.car = car;
        this.carCondition = carCondition;
        this.cost = cost;
        this.currentMileage = currentMileage;
        this.inspectionNotes = inspectionNotes;
        this.mtncDate = mtncDate;
        this.mtncId = mtncId;
        this.mtncType = mtncType;
        this.nextDate = nextDate;
        this.nextMileage = nextMileage;
        this.workshopId = workshopId;
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

    public int getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(int workshopId) {
        this.workshopId = workshopId;
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


}
