package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Condition;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Embeddable
public class BrakeCheck {
    private double fPadThickness;
    private double rPadThickness;
    @Enumerated(EnumType.STRING)
    private Condition frontRotorsCon;
    @Enumerated(EnumType.STRING)
    private Condition rearRotorsCon;
    @Enumerated(EnumType.STRING)
    private Condition brakeLines;

    public BrakeCheck() {
    }

    public BrakeCheck(Condition brakeLines, double fPadThickness, Condition frontRotorsCon, double rPadThickness, Condition rearRotorsCon) {
        this.brakeLines = brakeLines;
        this.fPadThickness = fPadThickness;
        this.frontRotorsCon = frontRotorsCon;
        this.rPadThickness = rPadThickness;
        this.rearRotorsCon = rearRotorsCon;
    }

    public double getfPadThickness() {
        return fPadThickness;
    }

    public void setfPadThickness(double fPadThickness) {
        this.fPadThickness = fPadThickness;
    }

    public double getrPadThickness() {
        return rPadThickness;
    }

    public void setrPadThickness(double rPadThickness) {
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
