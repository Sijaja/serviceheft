package dev.sijaja.serviceheft.dto.addMaintenance;

import dev.sijaja.serviceheft.model.enums.Condition;

public class BrakeCheckDTO {
    private Double fPadThickness;
    private Double rPadThickness;
    private Condition frontRotorsCon;
    private Condition rearRotorsCon;
    private Condition brakeLines;
    public BrakeCheckDTO() {
    }
    public BrakeCheckDTO(Condition brakeLines, Double fPadThickness, Condition frontRotorsCon, Double rPadThickness, Condition rearRotorsCon) {
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
