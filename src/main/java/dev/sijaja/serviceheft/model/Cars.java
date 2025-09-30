package dev.sijaja.serviceheft.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cars")
public class Cars {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int carId;
    private int photoId;
    private int ownerId;
    private String vinNumber;
    private String carColor;
    private String manufacturer;
    private String model;
    private int makeYear;
    private LocalDate inspectionExp;
    private int mileage;

    public Cars() {
    }

    public Cars(String carColor, int carId, LocalDate inspectionExp, int makeYear, String manufacturer, int mileage, String model, int ownerId, int photoId, String vinNumber) {
        this.carColor = carColor;
        this.carId = carId;
        this.inspectionExp = inspectionExp;
        this.makeYear = makeYear;
        this.manufacturer = manufacturer;
        this.mileage = mileage;
        this.model = model;
        this.ownerId = ownerId;
        this.photoId = photoId;
        this.vinNumber = vinNumber;
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

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}