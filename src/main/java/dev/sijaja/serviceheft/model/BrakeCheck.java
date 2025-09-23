package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Condition;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BrakeCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int brakeCheckId;
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

    public BrakeCheck(int brakeCheckId, Condition brakeLines, double fPadThickness, Condition frontRotorsCon, double rPadThickness, Condition rearRotorsCon) {
        this.brakeCheckId = brakeCheckId;
        this.brakeLines = brakeLines;
        this.fPadThickness = fPadThickness;
        this.frontRotorsCon = frontRotorsCon;
        this.rPadThickness = rPadThickness;
        this.rearRotorsCon = rearRotorsCon;
    }

    public int getBrakeCheckId() {
        return brakeCheckId;
    }

    public void setBrakeCheckId(int brakeCheckId) {
        this.brakeCheckId = brakeCheckId;
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
