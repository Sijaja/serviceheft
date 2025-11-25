package dev.sijaja.serviceheft.dto.addMaintenance;

import dev.sijaja.serviceheft.model.enums.Condition;

public class BeltHoseCheckDTO {
    private Condition serpentineBelt;
    private Condition timingBelt;
    private Condition radiatorHoses;
    private Condition heaterHoses;
    public BeltHoseCheckDTO() {
    }
    public BeltHoseCheckDTO(Condition serpentineBelt, Condition timingBelt, Condition radiatorHoses,
            Condition heaterHoses) {
        this.serpentineBelt = serpentineBelt;
        this.timingBelt = timingBelt;
        this.radiatorHoses = radiatorHoses;
        this.heaterHoses = heaterHoses;
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
