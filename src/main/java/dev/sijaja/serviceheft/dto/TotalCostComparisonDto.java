package dev.sijaja.serviceheft.dto;

public class TotalCostComparisonDto {
    private double myCarTotalCost;
    private double sameModelAndYearAverage;
    private double sameYearAverage;
    private int sameModelAndYearCount;
    private int sameYearCount;

    public TotalCostComparisonDto(double myCarTotalCost, double sameModelAndYearAverage,
                                   double sameYearAverage, int sameModelAndYearCount, int sameYearCount) {
        this.myCarTotalCost = myCarTotalCost;
        this.sameModelAndYearAverage = sameModelAndYearAverage;
        this.sameYearAverage = sameYearAverage;
        this.sameModelAndYearCount = sameModelAndYearCount;
        this.sameYearCount = sameYearCount;
    }

    public double getMyCarTotalCost() {
        return myCarTotalCost;
    }

    public void setMyCarTotalCost(double myCarTotalCost) {
        this.myCarTotalCost = myCarTotalCost;
    }

    public double getSameModelAndYearAverage() {
        return sameModelAndYearAverage;
    }

    public void setSameModelAndYearAverage(double sameModelAndYearAverage) {
        this.sameModelAndYearAverage = sameModelAndYearAverage;
    }

    public double getSameYearAverage() {
        return sameYearAverage;
    }

    public void setSameYearAverage(double sameYearAverage) {
        this.sameYearAverage = sameYearAverage;
    }

    public int getSameModelAndYearCount() {
        return sameModelAndYearCount;
    }

    public void setSameModelAndYearCount(int sameModelAndYearCount) {
        this.sameModelAndYearCount = sameModelAndYearCount;
    }

    public int getSameYearCount() {
        return sameYearCount;
    }

    public void setSameYearCount(int sameYearCount) {
        this.sameYearCount = sameYearCount;
    }
}
