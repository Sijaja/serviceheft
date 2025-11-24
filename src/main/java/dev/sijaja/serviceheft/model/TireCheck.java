package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.model.enums.Pattern;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class TireCheck {

    private double treadFrontLeft;
    private double treadFrontRight;
    private double treadRearLeft;
    private double treadRearRight;
    private double pressureFL;
    private double pressureFR;
    private double pressureRL;
    private double pressureRR;
    @Enumerated(EnumType.STRING)
    private Pattern wearPattern;
    @Enumerated(EnumType.STRING)
    private Condition shockAbsorbers;

    public TireCheck() {
    }

    public TireCheck(Condition shockAbsorbers, double pressureFL, double pressureFR, double pressureRL, double pressureRR, Pattern wearPattern, double treadFrontLeft, double treadFrontRight, double treadRearLeft, double treadRearRight) {
        this.shockAbsorbers = shockAbsorbers;
        this.pressureFL = pressureFL;
        this.pressureFR = pressureFR;
        this.pressureRL = pressureRL;
        this.pressureRR = pressureRR;
        this.wearPattern = wearPattern;
        this.treadFrontLeft = treadFrontLeft;
        this.treadFrontRight = treadFrontRight;
        this.treadRearLeft = treadRearLeft;
        this.treadRearRight = treadRearRight;
    }

    public double getTreadFrontLeft() {
        return treadFrontLeft;
    }

    public void setTreadFrontLeft(double treadFrontLeft) {
        this.treadFrontLeft = treadFrontLeft;
    }

    public double getTreadFrontRight() {
        return treadFrontRight;
    }

    public void setTreadFrontRight(double treadFrontRight) {
        this.treadFrontRight = treadFrontRight;
    }

    public double getTreadRearLeft() {
        return treadRearLeft;
    }

    public void setTreadRearLeft(double treadRearLeft) {
        this.treadRearLeft = treadRearLeft;
    }

    public double getTreadRearRight() {
        return treadRearRight;
    }

    public void setTreadRearRight(double treadRearRight) {
        this.treadRearRight = treadRearRight;
    }

    public double getPressureFL() {
        return pressureFL;
    }

    public void setPressureFL(double pressureFL) {
        this.pressureFL = pressureFL;
    }

    public double getPressureFR() {
        return pressureFR;
    }

    public void setPressureFR(double pressureFR) {
        this.pressureFR = pressureFR;
    }

    public double getPressureRL() {
        return pressureRL;
    }

    public void setPressureRL(double pressureRL) {
        this.pressureRL = pressureRL;
    }

    public double getPressureRR() {
        return pressureRR;
    }

    public void setPressureRR(double pressureRR) {
        this.pressureRR = pressureRR;
    }

    public Pattern getWearPattern() {
        return wearPattern;
    }

    public void setWearPattern(Pattern wearPattern) {
        this.wearPattern = wearPattern;
    }

    public Condition getShockAbsorbers() {
        return shockAbsorbers;
    }

    public void setShockAbsorbers(Condition shockAbsorbers) {
        this.shockAbsorbers = shockAbsorbers;
    }

}
