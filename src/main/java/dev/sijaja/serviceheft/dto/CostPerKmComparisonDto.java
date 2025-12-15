package dev.sijaja.serviceheft.dto;

public class CostPerKmComparisonDto {
    private double myCarCostPerThousandKm;
    private double sameModelAndYearAverage;
    private double sameYearAverage;
    private int sameModelAndYearCount;
    private int sameYearCount;

    public CostPerKmComparisonDto(double myCarCostPerThousandKm, double sameModelAndYearAverage,
                                   double sameYearAverage, int sameModelAndYearCount, int sameYearCount) {
        this.myCarCostPerThousandKm = myCarCostPerThousandKm;
        this.sameModelAndYearAverage = sameModelAndYearAverage;
        this.sameYearAverage = sameYearAverage;
        this.sameModelAndYearCount = sameModelAndYearCount;
        this.sameYearCount = sameYearCount;
    }

    public double getMyCarCostPerThousandKm() {
        return myCarCostPerThousandKm;
    }

    public void setMyCarCostPerThousandKm(double myCarCostPerThousandKm) {
        this.myCarCostPerThousandKm = myCarCostPerThousandKm;
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
