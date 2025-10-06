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
public class BeltHoseCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int beltHoseCheckId;

    @OneToOne
    @JoinColumn(name = "mtnc_id")
    private Maintenance maintenance;

    @Enumerated(EnumType.STRING)
    private Condition serpentineBelt;
    @Enumerated(EnumType.STRING)
    private Condition timingBelt;
    @Enumerated(EnumType.STRING)
    private Condition radiatorHoses;
    @Enumerated(EnumType.STRING)
    private Condition heaterHoses;

    public int getBeltHoseCheckId() {
        return beltHoseCheckId;
    }

    public void setBeltHoseCheckId(int beltHoseCheckId) {
        this.beltHoseCheckId = beltHoseCheckId;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    public Condition getSerpentineBelt() {
        return serpentineBelt;
    }

    public void setSerpentineBelt(Condition serpentineBelt) {
        this.serpentineBelt = serpentineBelt;
    }

    public Condition getTimingBelt() {
        return timingBelt;
    }

    public void setTimingBelt(Condition timingBelt) {
        this.timingBelt = timingBelt;
    }

    public Condition getRadiatorHoses() {
        return radiatorHoses;
    }

    public void setRadiatorHoses(Condition radiatorHoses) {
        this.radiatorHoses = radiatorHoses;
    }

    public Condition getHeaterHoses() {
        return heaterHoses;
    }

    public void setHeaterHoses(Condition heaterHoses) {
        this.heaterHoses = heaterHoses;
    }
}
