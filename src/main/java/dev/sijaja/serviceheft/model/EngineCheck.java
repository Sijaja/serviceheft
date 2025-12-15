package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.model.enums.Level;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class EngineCheck {
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

    public EngineCheck(Level brakeFluidLevel, Condition brakeFluidColor, Level coolantLevel, Condition coolantCondition, Level gearFluid, Condition oilCondition, Level oilLevel, boolean oilFilter, boolean oilReplaced, Condition engineStatus, Level steeringFluid, Level washFluid) {
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

}
