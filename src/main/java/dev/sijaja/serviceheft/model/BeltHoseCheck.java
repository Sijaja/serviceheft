package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Condition;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class BeltHoseCheck {
    @Enumerated(EnumType.STRING)
    private Condition serpentineBelt;
    @Enumerated(EnumType.STRING)
    private Condition timingBelt;
    @Enumerated(EnumType.STRING)
    private Condition radiatorHoses;
    @Enumerated(EnumType.STRING)
    private Condition heaterHoses;

    public BeltHoseCheck(Condition serpentineBelt, Condition timingBelt, Condition radiatorHoses,
            Condition heaterHoses) {
        this.serpentineBelt = serpentineBelt;
        this.timingBelt = timingBelt;
        this.radiatorHoses = radiatorHoses;
        this.heaterHoses = heaterHoses;
    }
    public BeltHoseCheck() {
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
