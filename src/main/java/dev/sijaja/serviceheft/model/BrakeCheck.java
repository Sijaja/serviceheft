package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Condition;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Embeddable
public class BrakeCheck {
    private Double fPadThickness;
    private Double rPadThickness;
    @Enumerated(EnumType.STRING)
    private Condition frontRotorsCon;
    @Enumerated(EnumType.STRING)
    private Condition rearRotorsCon;
    @Enumerated(EnumType.STRING)
    private Condition brakeLines;

    public BrakeCheck() {
    }

    public BrakeCheck(Condition brakeLines, Double fPadThickness, Condition frontRotorsCon, Double rPadThickness, Condition rearRotorsCon) {
        this.brakeLines = brakeLines;
        this.fPadThickness = fPadThickness;
        this.frontRotorsCon = frontRotorsCon;
        this.rPadThickness = rPadThickness;
        this.rearRotorsCon = rearRotorsCon;
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
