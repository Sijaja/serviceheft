package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Age;
import dev.sijaja.serviceheft.model.enums.Check;
import dev.sijaja.serviceheft.model.enums.Condition;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ElectricCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int electricCheckId;
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

    public ElectricCheck(Age age, double alternatorOutput, int electricCheckId, Check headLights, Check tailLight, Condition terminals, Check turnSignals, double voltage) {
        this.age = age;
        this.alternatorOutput = alternatorOutput;
        this.electricCheckId = electricCheckId;
        this.headLights = headLights;
        this.tailLight = tailLight;
        this.terminals = terminals;
        this.turnSignals = turnSignals;
        this.voltage = voltage;
    }

    public int getElectricCheckId() {
        return electricCheckId;
    }

    public void setElectricCheckId(int electricCheckId) {
        this.electricCheckId = electricCheckId;
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
