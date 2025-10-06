package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Condition;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class HvacCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int hvacCheckId;

    @OneToOne
    @JoinColumn(name = "mtnc_id")
    private Maintenance maintenance;

    @Enumerated(EnumType.STRING)
    private Condition acPerformance;
    @Enumerated(EnumType.STRING)
    private Condition heatPerformance;
    @Enumerated(EnumType.STRING)
    private Condition blowerMotor;

    public HvacCheck() {
    }

    public HvacCheck(int hvacCheckId, Condition acPerformance, Condition blowerMotor, Maintenance maintenance, Condition heatPerformance) {
        this.hvacCheckId = hvacCheckId;
        this.acPerformance = acPerformance;
        this.blowerMotor = blowerMotor;
        this.maintenance = maintenance;
        this.heatPerformance = heatPerformance;
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

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }


}
