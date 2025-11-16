package dev.sijaja.serviceheft.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;
    private int photoId;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    private String vinNumber;
    private String carColor;
    private String manufacturer;
    private String model;
    private int makeYear;
    private LocalDate inspectionExp;
    private int mileage;

    public Cars() {
    }

    public Cars(int carId, int photoId, Owner owner, String vinNumber, String carColor, String manufacturer,
            String model, int makeYear, LocalDate inspectionExp, int mileage) {
        this.carId = carId;
        this.photoId = photoId;
        this.owner = owner;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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
