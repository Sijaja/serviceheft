package dev.sijaja.serviceheft.dto;

public class MileageComparisonDto {
    private int myCarMileage;
    private double sameModelAndYearAverage;
    private double sameYearAverage;
    private int sameModelAndYearCount;
    private int sameYearCount;

    public MileageComparisonDto(int myCarMileage, double sameModelAndYearAverage,
                                 double sameYearAverage, int sameModelAndYearCount, int sameYearCount) {
        this.myCarMileage = myCarMileage;
        this.sameModelAndYearAverage = sameModelAndYearAverage;
        this.sameYearAverage = sameYearAverage;
        this.sameModelAndYearCount = sameModelAndYearCount;
        this.sameYearCount = sameYearCount;
    }

    public int getMyCarMileage() {
        return myCarMileage;
    }

    public void setMyCarMileage(int myCarMileage) {
        this.myCarMileage = myCarMileage;
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
