package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Check;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class FilterCheck {

    @Enumerated(EnumType.STRING)
    private Check airFilter;
    @Enumerated(EnumType.STRING)
    private Check cabinFilter;
    @Enumerated(EnumType.STRING)
    private Check fuelFilter;

    public FilterCheck() {
    }

    public FilterCheck(Check airFilter, Check cabinFilter, Check fuelFilter) {
        this.airFilter = airFilter;
        this.cabinFilter = cabinFilter;
        this.fuelFilter = fuelFilter;
    }

    public Check getAirFilter() {
        return airFilter;
    }

    public void setAirFilter(Check airFilter) {
        this.airFilter = airFilter;
    }

    public Check getCabinFilter() {
        return cabinFilter;
    }

    public void setCabinFilter(Check cabinFilter) {
        this.cabinFilter = cabinFilter;
    }

    public Check getFuelFilter() {
        return fuelFilter;
    }

    public void setFuelFilter(Check fuelFilter) {
        this.fuelFilter = fuelFilter;
    }

}
