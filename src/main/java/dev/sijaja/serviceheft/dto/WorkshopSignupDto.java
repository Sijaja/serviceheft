package dev.sijaja.serviceheft.dto;

import dev.sijaja.serviceheft.model.enums.UserRole;

public class WorkshopSignupDto {
    private String workshopName;
    private String email;
    private String password;
    private String street;
    private int houseNumber;
    private String city;
    private String phoneNumber;
    private String website;
    private UserRole role;

    public WorkshopSignupDto() {
    }

    public WorkshopSignupDto(String city, String email, int houseNumber, String password, String phoneNumber, UserRole role, String street, String website, String workshopName) {
        this.city = city;
        this.email = email;
        this.houseNumber = houseNumber;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = UserRole.WORKSHOP;
        this.street = street;
        this.website = website;
        this.workshopName = workshopName;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    

}
