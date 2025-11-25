package dev.sijaja.serviceheft.dto.addMaintenance;

import dev.sijaja.serviceheft.model.enums.Condition;

public class HvacCheckDTO {

    private Condition acPerformance;
    private Condition heatPerformance;
    private Condition blowerMotor;

    public HvacCheckDTO() {
    }

    public HvacCheckDTO(Condition acPerformance, Condition heatPerformance, Condition blowerMotor) {
        this.acPerformance = acPerformance;
        this.heatPerformance = heatPerformance;
        this.blowerMotor = blowerMotor;
    }

    public Condition getAcPerformance() {
        return acPerformance;
    }

    public void setAcPerformance(Condition acPerformance) {
        this.acPerformance = acPerformance;
    }

    public Condition getHeatPerformance() {
        return heatPerformance;
    }

    public void setHeatPerformance(Condition heatPerformance) {
        this.heatPerformance = heatPerformance;
    }

    public Condition getBlowerMotor() {
        return blowerMotor;
    }

    public void setBlowerMotor(Condition blowerMotor) {
        this.blowerMotor = blowerMotor;
    }

    
}
