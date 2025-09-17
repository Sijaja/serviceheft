package dev.sijaja.serviceheft.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int carId;
    
    @Column(name = "owner_id")
    private int ownerId;
    @Column(name = "vin_number")
    private String vinNumber;
    @Column(name = "car_color")
    private String carColor;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "model")
    private String model;
    @Column(name = "make_year")
    private int makeYear;
    @Column(name = "inspection_exp")
    private LocalDate inspectionExp;
    @Column(name = "mileage")
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