package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Check;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FilterCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int filterCheckId;
    private Check airFilter;
    private Check cabinFilter;
    private Check fuelFilter;

    public FilterCheck() {
    }

    public FilterCheck(Check airFilter, Check cabinFilter, int filterCheckId, Check fuelFilter) {
        this.airFilter = airFilter;
        this.cabinFilter = cabinFilter;
        this.filterCheckId = filterCheckId;
        this.fuelFilter = fuelFilter;
    }

    public int getFilterCheckId() {
        return filterCheckId;
    }

    public void setFilterCheckId(int filterCheckId) {
        this.filterCheckId = filterCheckId;
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
