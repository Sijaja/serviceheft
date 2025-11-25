package dev.sijaja.serviceheft.dto.addMaintenance;

import dev.sijaja.serviceheft.model.enums.Check;

public class FilterCheckDTO {
    private Check airFilter;
    private Check cabinFilter;
    private Check fuelFilter;
    public FilterCheckDTO() {
    }
    public FilterCheckDTO(Check airFilter, Check cabinFilter, Check fuelFilter) {
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
