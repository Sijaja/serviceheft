package dev.sijaja.serviceheft.dto;

public class HealthScoreDTO {
    private int brakesAndTires;
    private int drivetrain;
    private int belt;
    private int chasis;
    private int totalScore;

    public HealthScoreDTO(int brakesAndTires, int drivetrain, int belt, int chasis) {
        this.brakesAndTires = brakesAndTires;
        this.drivetrain = drivetrain;
        this.belt = belt;
        this.chasis = chasis;
        this.totalScore = (brakesAndTires + drivetrain + belt + chasis) / 4;
    }

    public HealthScoreDTO() {
    }

    public int getBrakesAndTires() {
        return brakesAndTires;
    }

    public void setBrakesAndTires(int brakesAndTires) {
        this.brakesAndTires = brakesAndTires;
    }

    public int getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(int drivetrain) {
        this.drivetrain = drivetrain;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getBelt() {
        return belt;
    }

    public void setBelt(int belt) {
        this.belt = belt;
    }

    public int getChasis() {
        return chasis;
    }

    public void setChasis(int chasis) {
        this.chasis = chasis;
    }

    
}
