package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Condition;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class HvacCheck {
    @Enumerated(EnumType.STRING)
    private Condition acPerformance;
    @Enumerated(EnumType.STRING)
    private Condition heatPerformance;
    @Enumerated(EnumType.STRING)
    private Condition blowerMotor;

    public HvacCheck() {
    }

    public HvacCheck(Condition acPerformance, Condition blowerMotor, Condition heatPerformance) {
        this.acPerformance = acPerformance;
        this.blowerMotor = blowerMotor;
        this.heatPerformance = heatPerformance;
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
