package dev.sijaja.serviceheft.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
    @NotBlank
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    private String phoneNumber;
    private String website;
    @NotBlank
    @Size(min = 8)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    public Workshop() {
    }

    public Workshop(int workshopId, String workshopName, String street, int houseNumber, String city, String email,
            String phoneNumber, String website, String password) {
        this.workshopId = workshopId;
        this.workshopName = workshopName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
