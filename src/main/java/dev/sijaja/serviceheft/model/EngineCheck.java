package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.model.enums.Level;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class EngineCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int engineCheckId;

    @OneToOne
    @JoinColumn(name = "mtnc_id")
    private Maintenance maintenance;

    @Enumerated(EnumType.STRING)
    private Level oilLevel;
    @Enumerated(EnumType.STRING)
    private Condition oilCondition;
    private boolean oilFilter;
    private boolean oilReplaced;
    @Enumerated(EnumType.STRING)
    private Level coolantLevel;
    @Enumerated(EnumType.STRING)
    private Condition coolantCondition;
    @Enumerated(EnumType.STRING)
    private Level brakeFluidLevel;
    @Enumerated(EnumType.STRING)
    private Condition brakeFluidColor;
    @Enumerated(EnumType.STRING)
    private Level steeringFluid;
    @Enumerated(EnumType.STRING)
    private Level gearFluid;
    @Enumerated(EnumType.STRING)
    private Level washFluid;
    @Enumerated(EnumType.STRING)
    private Condition engineStatus;

    public EngineCheck() {
    }

    public EngineCheck(int engineCheckId, Level brakeFluidLevel, Condition brakeFluidColor, Level coolantLevel, Condition coolantCondition, Level gearFluid, Condition oilCondition, Level oilLevel, boolean oilFilter, boolean oilReplaced, Condition engineStatus, Level steeringFluid, Level washFluid, Maintenance maintenance) {
        this.engineCheckId = engineCheckId;
        this.brakeFluidLevel = brakeFluidLevel;
        this.brakeFluidColor = brakeFluidColor;
        this.coolantLevel = coolantLevel;
        this.coolantCondition = coolantCondition;
        this.gearFluid = gearFluid;
        this.oilCondition = oilCondition;
        this.oilLevel = oilLevel;
        this.oilFilter = oilFilter;
        this.oilReplaced = oilReplaced;
        this.engineStatus = engineStatus;
        this.steeringFluid = steeringFluid;
        this.washFluid = washFluid;
        this.maintenance = maintenance;
    }

    public int getEngineCheckId() {
        return engineCheckId;
    }

    public void setEngineCheckId(int engineCheckId) {
        this.engineCheckId = engineCheckId;
    }

    public Level getOilLevel() {
        return oilLevel;
    }

    public void setOilLevel(Level oilLevel) {
        this.oilLevel = oilLevel;
    }

    public Condition getOilCondition() {
        return oilCondition;
    }

    public void setOilCondition(Condition oilCondition) {
        this.oilCondition = oilCondition;
    }

    public boolean isOilFilter() {
        return oilFilter;
    }

    public void setOilFilter(boolean oilFilter) {
        this.oilFilter = oilFilter;
    }

    public boolean isOilReplaced() {
        return oilReplaced;
    }

    public void setOilReplaced(boolean oilReplaced) {
        this.oilReplaced = oilReplaced;
    }

    public Level getCoolantLevel() {
        return coolantLevel;
    }

    public void setCoolantLevel(Level coolantLevel) {
        this.coolantLevel = coolantLevel;
    }

    public Condition getCoolantCondition() {
        return coolantCondition;
    }

    public void setCoolantCondition(Condition coolantCondition) {
        this.coolantCondition = coolantCondition;
    }

    public Level getBrakeFluidLevel() {
        return brakeFluidLevel;
    }

    public void setBrakeFluidLevel(Level brakeFluidLevel) {
        this.brakeFluidLevel = brakeFluidLevel;
    }

    public Condition getBrakeFluidColor() {
        return brakeFluidColor;
    }

    public void setBrakeFluidColor(Condition brakeFluidColor) {
        this.brakeFluidColor = brakeFluidColor;
    }

    public Level getSteeringFluid() {
        return steeringFluid;
    }

    public void setSteeringFluid(Level steeringFluid) {
        this.steeringFluid = steeringFluid;
    }

    public Level getGearFluid() {
        return gearFluid;
    }

    public void setGearFluid(Level gearFluid) {
        this.gearFluid = gearFluid;
    }

    public Level getWashFluid() {
        return washFluid;
    }

    public void setWashFluid(Level washFluid) {
        this.washFluid = washFluid;
    }

    public Condition getEngineStatus() {
        return engineStatus;
    }

    public void setEngineStatus(Condition engineStatus) {
        this.engineStatus = engineStatus;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    
}
