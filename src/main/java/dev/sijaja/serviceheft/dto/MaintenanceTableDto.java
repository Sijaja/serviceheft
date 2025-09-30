package dev.sijaja.serviceheft.dto;

import java.time.LocalDate;

public class MaintenanceTableDto {
    private LocalDate date;
    private String description;
    private double cost;
    private int mileage;
    private String workshop;
    public MaintenanceTableDto(LocalDate date, String description, double cost, int mileage, String workshop) {
        this.date = date;
        this.description = description;
        this.cost = cost;
        this.mileage = mileage;
        this.workshop = workshop;
    }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }
    public double getCost() { return cost; }
    public int getMileage() { return mileage; }
    public String getWorkshop() { return workshop; }
}
