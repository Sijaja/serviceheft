package dev.sijaja.serviceheft.dto;

public class CostComparisonDto {
    private double myCarTotal;
    private double otherCarsAverage;

    public CostComparisonDto(double myCarTotal, double otherCarsAverage) {
        this.myCarTotal = myCarTotal;
        this.otherCarsAverage = otherCarsAverage;
    }

    public double getMyCarTotal() { return myCarTotal; }
    public double getOtherCarsAverage() { return otherCarsAverage; }
}