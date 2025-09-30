package dev.sijaja.serviceheft.dto;

import java.time.LocalDate;

public class NextMaintenanceDto {
    private LocalDate nextDate;
    private Integer nextMileage;

    public NextMaintenanceDto(LocalDate nextDate, Integer nextMileage) {
        this.nextDate = nextDate;
        this.nextMileage = nextMileage;
    }

    public LocalDate getNextDate() { return nextDate; }
    public Integer getNextMileage() { return nextMileage; }
}
