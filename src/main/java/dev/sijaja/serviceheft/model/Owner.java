package dev.sijaja.serviceheft.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="owners")
public class Owner {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ownerId;
    @NotBlank
    private String userName;
    private Integer photoId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String street;
    private Integer houseNumber;
    private String city;
    private int defaultCarId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Owner() {
    }

    public Owner(int ownerId, String userName, Integer photoId, String firstName, String lastName, LocalDate dateOfBirth,
            String street, Integer houseNumber, String city, String email, String password, int defaultCarId) {
        this.ownerId = ownerId;
        this.userName = userName;
        this.photoId = photoId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.defaultCarId = defaultCarId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDefaultCarId() {
        return defaultCarId;
    }

    public void setDefaultCarId(int defaultCarId) {
        this.defaultCarId = defaultCarId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
