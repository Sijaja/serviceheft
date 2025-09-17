package dev.sijaja.serviceheft.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int carId;
    
    private int ownerId;
    private String vinNumber;
    private String carColor;
    private String manufacturer;
    private String model;
    private int makeYear;
    private LocalDate inspectionExp;
    private int mileage;

    public Car() {
    }

    public Car(int carId, int ownerId, String vinNumber, String carColor, String manufacturer, String model,
            int makeYear, LocalDate inspectionExp, int mileage) {
        this.carId = carId;
        this.ownerId = ownerId;
        this.vinNumber = vinNumber;
        this.carColor = carColor;
        this.manufacturer = manufacturer;
        this.model = model;
        this.makeYear = makeYear;
        this.inspectionExp = inspectionExp;
        this.mileage = mileage;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMakeYear() {
        return makeYear;
    }

    public void setMakeYear(int makeYear) {
        this.makeYear = makeYear;
    }

    public LocalDate getInspectionExp() {
        return inspectionExp;
    }

    public void setInspectionExp(LocalDate inspectionExp) {
        this.inspectionExp = inspectionExp;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}