package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Check;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class EmmisionCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int emmisionCheckId;

    @OneToOne
    @JoinColumn(name = "mtnc_id")
    private Maintenance maintenance;

    @Enumerated(EnumType.STRING)
    private Check exhaust;
    @Enumerated(EnumType.STRING)
    private Check catalytic;
    @Enumerated(EnumType.STRING)
    private Check o2Sensors;

    public EmmisionCheck() {
    }

    public EmmisionCheck(int emmisionCheckId, Check catalytic, Check exhaust, Maintenance maintenance, Check o2Sensors) {
        this.emmisionCheckId = emmisionCheckId;
        this.catalytic = catalytic;
        this.exhaust = exhaust;
        this.maintenance = maintenance;
        this.o2Sensors = o2Sensors;
    }
    
    public int getEmmisionCheckId() {
        return emmisionCheckId;
    }

    public void setEmmisionCheckId(int emmisionCheckId) {
        this.emmisionCheckId = emmisionCheckId;
    }

    public Check getExhaust() {
        return exhaust;
    }

    public void setExhaust(Check exhaust) {
        this.exhaust = exhaust;
    }

    public Check getCatalytic() {
        return catalytic;
    }

    public void setCatalytic(Check catalytic) {
        this.catalytic = catalytic;
    }

    public Check getO2Sensors() {
        return o2Sensors;
    }

    public void setO2Sensors(Check o2Sensors) {
        this.o2Sensors = o2Sensors;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }


}
