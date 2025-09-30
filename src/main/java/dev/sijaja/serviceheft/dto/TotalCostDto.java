package dev.sijaja.serviceheft.dto;

public class TotalCostDto {
    private double totalCost;
    private double averageCost;

    public TotalCostDto(double totalCost, double averageCost) {
        this.totalCost = totalCost;
        this.averageCost = averageCost;
    }

    public double getTotalCost() { return totalCost; }
    public double getAverageCost() { return averageCost; }
}
