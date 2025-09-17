package dev.sijaja.serviceheft.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="workshops")
public class Workshop {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int workshopId;
    private String workshopName;
    private String street;
    private int houseNumber;
    private String city;

    public Workshop() {
    }

    public Workshop(String city, int houseNumber, String street, int workshopId, String workshopName) {
        this.city = city;
        this.houseNumber = houseNumber;
        this.street = street;
        this.workshopId = workshopId;
        this.workshopName = workshopName;
    }

    public int getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(int workshopId) {
        this.workshopId = workshopId;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    
}
