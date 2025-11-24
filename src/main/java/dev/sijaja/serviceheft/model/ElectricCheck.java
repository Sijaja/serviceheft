package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Age;
import dev.sijaja.serviceheft.model.enums.Check;
import dev.sijaja.serviceheft.model.enums.Condition;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class ElectricCheck {

    private double voltage;
    @Enumerated(EnumType.STRING)
    private Condition terminals;
    @Enumerated(EnumType.STRING)
    private Age age;
    private double alternatorOutput;
    @Enumerated(EnumType.STRING)
    private Check headLights;
    @Enumerated(EnumType.STRING)
    private Check tailLight;
    @Enumerated(EnumType.STRING)
    private Check turnSignals;

    public ElectricCheck() {
    }

    public ElectricCheck(Age age, Check headLights, Condition terminals, double alternatorOutput, double voltage, Check tailLight, Check turnSignals) {
        this.age = age;
        this.headLights = headLights;
        this.terminals = terminals;
        this.alternatorOutput = alternatorOutput;
        this.voltage = voltage;
        this.tailLight = tailLight;
        this.turnSignals = turnSignals;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
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

    public double getAlternatorOutput() {
        return alternatorOutput;
    }

    public void setAlternatorOutput(double alternatorOutput) {
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
