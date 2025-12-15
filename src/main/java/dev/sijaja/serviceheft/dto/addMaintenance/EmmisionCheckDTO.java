package dev.sijaja.serviceheft.dto.addMaintenance;

import dev.sijaja.serviceheft.model.enums.Check;

public class EmmisionCheckDTO {
    private Check exhaust;
    private Check catalytic;
    private Check o2Sensors;
    public EmmisionCheckDTO() {
    }
    public EmmisionCheckDTO(Check catalytic, Check exhaust, Check o2Sensors) {
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
