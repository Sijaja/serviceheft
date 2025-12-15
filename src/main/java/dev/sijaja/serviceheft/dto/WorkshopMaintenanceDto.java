package dev.sijaja.serviceheft.dto;

import java.time.LocalDate;

import ch.qos.logback.core.status.Status;

public class WorkshopMaintenanceDto {

    private Integer workshopId;
    private Integer maintenanceId;
    private String ownersName;
    private Status status;
    private String description;
    private LocalDate startDate;

    public WorkshopMaintenanceDto() {
    }

    public WorkshopMaintenanceDto(Integer workshopId, Integer maintenanceId, String ownersName, Status status,
            String description, LocalDate startDate) {
        this.workshopId = workshopId;
        this.maintenanceId = maintenanceId;
        this.ownersName = ownersName;
        this.status = status;
        this.description = description;
        this.startDate = startDate;
    }

    public Integer getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(Integer workshopId) {
        this.workshopId = workshopId;
    }

    public Integer getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Integer maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public String getOwnersName() {
        return ownersName;
    }

    public void setOwnersName(String ownersName) {
        this.ownersName = ownersName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

}
