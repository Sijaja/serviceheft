package dev.sijaja.serviceheft.dto.addMaintenance;

import dev.sijaja.serviceheft.model.enums.Age;
import dev.sijaja.serviceheft.model.enums.Check;
import dev.sijaja.serviceheft.model.enums.Condition;

public class ElectricCheckDTO {
    private Double voltage;
    private Condition terminals;
    private Age age;
    private Double alternatorOutput;
    private Check headLights;
    private Check tailLight;
    private Check turnSignals;
    public ElectricCheckDTO() {
    }
    public ElectricCheckDTO(Age age, Check headLights, Condition terminals, Double alternatorOutput, double voltage, Check tailLight, Check turnSignals) {
        this.age = age;
        this.headLights = headLights;
        this.terminals = terminals;
        this.alternatorOutput = alternatorOutput;
        this.voltage = voltage;
        this.tailLight = tailLight;
        this.turnSignals = turnSignals;
    }
    public Double getVoltage() {
        return voltage;
    }
    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }
    public Condition getTerminals() {
        return terminals;
    }
    public void setTerminals(Condition terminals) {
        this.terminals = terminals;
    }
    public Age getAge() {
        return age;
    }
    public void setAge(Age age) {
        this.age = age;
    }
    public Double getAlternatorOutput() {
        return alternatorOutput;
    }
    public void setAlternatorOutput(Double alternatorOutput) {
        this.alternatorOutput = alternatorOutput;
    }
    public Check getHeadLights() {
        return headLights;
    }
    public void setHeadLights(Check headLights) {
        this.headLights = headLights;
    }
    public Check getTailLight() {
        return tailLight;
    }
    public void setTailLight(Check tailLight) {
        this.tailLight = tailLight;
    }
    public Check getTurnSignals() {
        return turnSignals;
    }
    public void setTurnSignals(Check turnSignals) {
        this.turnSignals = turnSignals;
    }
    
}
