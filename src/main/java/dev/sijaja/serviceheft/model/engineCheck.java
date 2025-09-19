package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.model.enums.Level;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EngineCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int engineCheckId;
    private Level oilLevel;
    private Condition oilCondition;
    private boolean oilFilter;
    private boolean oilReplaced;
    private Level coolantLevel;
    private Condition coolantCondition;
    private Level brakeFluidLevel;
    private Condition brakeFluidColor;
    private Level steeringFluid;
    private Level gearFluid;
    private Level washFluid;
    private Condition engineStatus;

    public EngineCheck() {
    }

    public EngineCheck(int engineCheckId, Level oilLevel, Condition oilCondition, boolean oilFilter,
            boolean oilReplaced, Level coolantLevel, Condition coolantCondition, Level brakeFluidLevel,
            Condition brakeFluidColor, Level steeringFluid, Level gearFluid, Level washFluid, Condition engineStatus) {
        this.engineCheckId = engineCheckId;
        this.oilLevel = oilLevel;
        this.oilCondition = oilCondition;
        this.oilFilter = oilFilter;
        this.oilReplaced = oilReplaced;
        this.coolantLevel = coolantLevel;
        this.coolantCondition = coolantCondition;
        this.brakeFluidLevel = brakeFluidLevel;
        this.brakeFluidColor = brakeFluidColor;
        this.steeringFluid = steeringFluid;
        this.gearFluid = gearFluid;
        this.washFluid = washFluid;
        this.engineStatus = engineStatus;
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

    
}
