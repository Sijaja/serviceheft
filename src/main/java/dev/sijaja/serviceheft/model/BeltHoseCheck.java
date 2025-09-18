package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Condition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BeltHoseCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int beltHoseCheckId;
    private Condition serpentineBelt;
    private Condition timingBelt;
    private Condition radiatorHoses;
    private Condition heaterHoses;

    public BeltHoseCheck(int beltHoseCheckId, Condition heaterHoses, Condition radiatorHoses, Condition serpentineBelt, Condition timingBelt) {
        this.beltHoseCheckId = beltHoseCheckId;
        this.heaterHoses = heaterHoses;
        this.radiatorHoses = radiatorHoses;
        this.serpentineBelt = serpentineBelt;
        this.timingBelt = timingBelt;
    }

    public BeltHoseCheck() {
    }

    public int getBeltHoseCheckId() {
        return beltHoseCheckId;
    }

    public void setBeltHoseCheckId(int beltHoseCheckId) {
        this.beltHoseCheckId = beltHoseCheckId;
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
