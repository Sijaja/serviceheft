package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Condition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HvacCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int hvacCheckId;
    private Condition acPerformance;
    private Condition heatPerformance;
    private Condition blowerMotor;

    public HvacCheck() {
    }

    public HvacCheck(Condition acPerformance, Condition blowerMotor, Condition heatPerformance, int hvacCheckId) {
        this.acPerformance = acPerformance;
        this.blowerMotor = blowerMotor;
        this.heatPerformance = heatPerformance;
        this.hvacCheckId = hvacCheckId;
    }

    public int getHvacCheckId() {
        return hvacCheckId;
    }

    public void setHvacCheckId(int hvacCheckId) {
        this.hvacCheckId = hvacCheckId;
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
