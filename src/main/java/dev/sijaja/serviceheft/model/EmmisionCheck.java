package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Check;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class EmmisionCheck {
    @Enumerated(EnumType.STRING)
    private Check exhaust;
    @Enumerated(EnumType.STRING)
    private Check catalytic;
    @Enumerated(EnumType.STRING)
    private Check o2Sensors;

    public EmmisionCheck() {
    }

    public EmmisionCheck(Check catalytic, Check exhaust, Check o2Sensors) {
        this.catalytic = catalytic;
        this.exhaust = exhaust;
        this.o2Sensors = o2Sensors;
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

}
