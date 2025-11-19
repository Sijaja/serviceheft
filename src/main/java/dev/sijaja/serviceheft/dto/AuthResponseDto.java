package dev.sijaja.serviceheft.dto;

import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.model.Workshop;

public class AuthResponseDto {

    private String userType;
    private Owner owner;
    private Workshop workshop;

    public AuthResponseDto(String userType, Owner owner, Workshop workshop) {
        this.userType = userType;
        this.owner = owner;
        this.workshop = workshop;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }
}
