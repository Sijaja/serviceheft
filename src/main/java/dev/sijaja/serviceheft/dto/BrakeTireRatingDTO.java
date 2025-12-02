package dev.sijaja.serviceheft.dto;

import dev.sijaja.serviceheft.model.enums.Condition;

public class BrakeTireRatingDTO {
    private Double treadFrontLeft;
    private Double treadFrontRight;
    private Double treadRearLeft;
    private Double treadRearRight;
    private Double fPadThickness;
    private Double rPadThickness;
    private Condition frontRotorsCon;
    private Condition rearRotorsCon;
    private Condition brakeLines;

    public BrakeTireRatingDTO(Double treadFrontLeft, Double treadFrontRight, Double treadRearLeft, Double treadRearRight,
                              Double fPadThickness, Double rPadThickness, Condition frontRotorsCon,
                              Condition rearRotorsCon, Condition brakeLines) {
        this.treadFrontLeft = treadFrontLeft;
        this.treadFrontRight = treadFrontRight;
        this.treadRearLeft = treadRearLeft;
        this.treadRearRight = treadRearRight;
        this.fPadThickness = fPadThickness;
        this.rPadThickness = rPadThickness;
        this.frontRotorsCon = frontRotorsCon;
        this.rearRotorsCon = rearRotorsCon;
        this.brakeLines = brakeLines;
    }

    public BrakeTireRatingDTO() {
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

    public Double getfPadThickness() {
        return fPadThickness;
    }

    public void setfPadThickness(Double fPadThickness) {
        this.fPadThickness = fPadThickness;
    }

    public Double getrPadThickness() {
        return rPadThickness;
    }

    public void setrPadThickness(Double rPadThickness) {
        this.rPadThickness = rPadThickness;
    }

    public Condition getFrontRotorsCon() {
        return frontRotorsCon;
    }

    public void setFrontRotorsCon(Condition frontRotorsCon) {
        this.frontRotorsCon = frontRotorsCon;
    }

    public Condition getRearRotorsCon() {
        return rearRotorsCon;
    }

    public void setRearRotorsCon(Condition rearRotorsCon) {
        this.rearRotorsCon = rearRotorsCon;
    }

    public Condition getBrakeLines() {
        return brakeLines;
    }

    public void setBrakeLines(Condition brakeLines) {
        this.brakeLines = brakeLines;
    }
    
}
