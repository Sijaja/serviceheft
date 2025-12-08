package dev.sijaja.serviceheft.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Costs {
    private double beltsHosesCost;
    private double brakesCost;
    private double engineCost;
    private double exhaustCost;
    private double filtersCost;
    private double electricCost;
    private double bodyPartsCost;
    private double rostCost;
    private double tiresCost;
    private double hvacCost;
    public Costs() {
        this.beltsHosesCost = 0.0;
        this.brakesCost = 0.0;
        this.engineCost = 0.0;
        this.exhaustCost = 0.0;
        this.filtersCost = 0.0;
        this.electricCost = 0.0;
        this.bodyPartsCost = 0.0;
        this.rostCost = 0.0;
        this.tiresCost = 0.0;
        this.hvacCost = 0.0;
    }
    public Costs(double beltsHosesCost, double brakesCost, double engineCost, double exhaustCost, double filtersCost,
            double electricCost, double bodyPartsCost, double rostCost, double tiresCost, double hvacCost) {
        this.beltsHosesCost = beltsHosesCost;
        this.brakesCost = brakesCost;
        this.engineCost = engineCost;
        this.exhaustCost = exhaustCost;
        this.filtersCost = filtersCost;
        this.electricCost = electricCost;
        this.bodyPartsCost = bodyPartsCost;
        this.rostCost = rostCost;
        this.tiresCost = tiresCost;
        this.hvacCost = hvacCost;
    }
    public double getBeltsHosesCost() {
        return beltsHosesCost;
    }
    public void setBeltsHosesCost(double beltsHosesCost) {
        this.beltsHosesCost = beltsHosesCost;
    }
    public double getBrakesCost() {
        return brakesCost;
    }
    public void setBrakesCost(double brakesCost) {
        this.brakesCost = brakesCost;
    }
    public double getEngineCost() {
        return engineCost;
    }
    public void setEngineCost(double engineCost) {
        this.engineCost = engineCost;
    }
    public double getExhaustCost() {
        return exhaustCost;
    }
    public void setExhaustCost(double exhaustCost) {
        this.exhaustCost = exhaustCost;
    }
    public double getFiltersCost() {
        return filtersCost;
    }
    public void setFiltersCost(double filtersCost) {
        this.filtersCost = filtersCost;
    }
    public double getElectricCost() {
        return electricCost;
    }
    public void setElectricCost(double electricCost) {
        this.electricCost = electricCost;
    }
    public double getBodyPartsCost() {
        return bodyPartsCost;
    }
    public void setBodyPartsCost(double bodyPartsCost) {
        this.bodyPartsCost = bodyPartsCost;
    }
    public double getRostCost() {
        return rostCost;
    }
    public void setRostCost(double rostCost) {
        this.rostCost = rostCost;
    }
    public double getTiresCost() {
        return tiresCost;
    }
    public void setTiresCost(double tiresCost) {
        this.tiresCost = tiresCost;
    }
    public double getHvacCost() {
        return hvacCost;
    }
    public void setHvacCost(double hvacCost) {
        this.hvacCost = hvacCost;
    }

}
