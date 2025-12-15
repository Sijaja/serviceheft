package dev.sijaja.serviceheft.dto;

public class MaintenanceCountComparisonDto {
    private int myCarMaintenanceCount;
    private double sameModelAndYearAverage;
    private double sameYearAverage;
    private int sameModelAndYearCount;
    private int sameYearCount;

    public MaintenanceCountComparisonDto(int myCarMaintenanceCount, double sameModelAndYearAverage,
                                          double sameYearAverage, int sameModelAndYearCount, int sameYearCount) {
        this.myCarMaintenanceCount = myCarMaintenanceCount;
        this.sameModelAndYearAverage = sameModelAndYearAverage;
        this.sameYearAverage = sameYearAverage;
        this.sameModelAndYearCount = sameModelAndYearCount;
        this.sameYearCount = sameYearCount;
    }

    public int getMyCarMaintenanceCount() {
        return myCarMaintenanceCount;
    }

    public void setMyCarMaintenanceCount(int myCarMaintenanceCount) {
        this.myCarMaintenanceCount = myCarMaintenanceCount;
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
