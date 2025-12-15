package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.model.enums.Pattern;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class TireCheck {

    private Double treadFrontLeft;
    private Double treadFrontRight;
    private Double treadRearLeft;
    private Double treadRearRight;
    private Double pressureFL;
    private Double pressureFR;
    private Double pressureRL;
    private Double pressureRR;
    @Enumerated(EnumType.STRING)
    private Pattern wearPattern;
    @Enumerated(EnumType.STRING)
    private Condition shockAbsorbers;

    public TireCheck() {
    }

    public TireCheck(Condition shockAbsorbers, Double pressureFL, Double pressureFR, Double pressureRL, Double pressureRR, Pattern wearPattern, Double treadFrontLeft, Double treadFrontRight, Double treadRearLeft, Double treadRearRight) {
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

    public Double getTreadFrontLeft() {
        return treadFrontLeft;
    }

    public void setTreadFrontLeft(Double treadFrontLeft) {
        this.treadFrontLeft = treadFrontLeft;
    }

    public Double getTreadFrontRight() {
        return treadFrontRight;
    }

    public void setTreadFrontRight(Double treadFrontRight) {
        this.treadFrontRight = treadFrontRight;
    }

    public Double getTreadRearLeft() {
        return treadRearLeft;
    }

    public void setTreadRearLeft(Double treadRearLeft) {
        this.treadRearLeft = treadRearLeft;
    }

    public Double getTreadRearRight() {
        return treadRearRight;
    }

    public void setTreadRearRight(Double treadRearRight) {
        this.treadRearRight = treadRearRight;
    }

    public Double getPressureFL() {
        return pressureFL;
    }

    public void setPressureFL(Double pressureFL) {
        this.pressureFL = pressureFL;
    }

    public Double getPressureFR() {
        return pressureFR;
    }

    public void setPressureFR(Double pressureFR) {
        this.pressureFR = pressureFR;
    }

    public Double getPressureRL() {
        return pressureRL;
    }

    public void setPressureRL(Double pressureRL) {
        this.pressureRL = pressureRL;
    }

    public Double getPressureRR() {
        return pressureRR;
    }

    public void setPressureRR(Double pressureRR) {
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
