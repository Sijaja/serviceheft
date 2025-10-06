package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Check;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class FilterCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int filterCheckId;

    @OneToOne
    @JoinColumn(name = "mtnc_id")
    private Maintenance maintenance;

    @Enumerated(EnumType.STRING)
    private Check airFilter;
    @Enumerated(EnumType.STRING)
    private Check cabinFilter;
    @Enumerated(EnumType.STRING)
    private Check fuelFilter;

    public FilterCheck() {
    }

    public FilterCheck(int filterCheckId, Check airFilter, Check cabinFilter, Maintenance maintenance, Check fuelFilter) {
        this.filterCheckId = filterCheckId;
        this.airFilter = airFilter;
        this.cabinFilter = cabinFilter;
        this.maintenance = maintenance;
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

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }


}
