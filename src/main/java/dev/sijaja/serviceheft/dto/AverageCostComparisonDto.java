package dev.sijaja.serviceheft.dto;

public class AverageCostComparisonDto {
    private double myCarAverage;
    private double otherCarsAverage;

    public AverageCostComparisonDto(double myCarAverage, double otherCarsAverage) {
        this.myCarAverage = myCarAverage;
        this.otherCarsAverage = otherCarsAverage;
    }

    public double getMyCarAverage() { return myCarAverage; }
    public double getOtherCarsAverage() { return otherCarsAverage; }
}