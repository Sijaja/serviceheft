package dev.sijaja.serviceheft.dto.addMaintenance;

import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.model.enums.Pattern;

public class TireCheckDTO {

    private Double treadFrontLeft;
    private Double treadFrontRight;
    private Double treadRearLeft;
    private Double treadRearRight;
    private Double pressureFL;
    private Double pressureFR;
    private Double pressureRL;
    private Double pressureRR;
    private Pattern wearPattern;
    private Condition shockAbsorbers;

    public TireCheckDTO() {
    }

    public TireCheckDTO(Double treadFrontLeft, Double treadFrontRight, Double treadRearLeft, Double treadRearRight,
            Double pressureFL, Double pressureFR, Double pressureRL, Double pressureRR, Pattern wearPattern,
            Condition shockAbsorbers) {
        this.treadFrontLeft = treadFrontLeft;
        this.treadFrontRight = treadFrontRight;
        this.treadRearLeft = treadRearLeft;
        this.treadRearRight = treadRearRight;
        this.pressureFL = pressureFL;
        this.pressureFR = pressureFR;
        this.pressureRL = pressureRL;
        this.pressureRR = pressureRR;
        this.wearPattern = wearPattern;
        this.shockAbsorbers = shockAbsorbers;
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
