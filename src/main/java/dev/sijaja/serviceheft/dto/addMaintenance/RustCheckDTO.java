package dev.sijaja.serviceheft.dto.addMaintenance;

import dev.sijaja.serviceheft.model.enums.Part;

public class RustCheckDTO {
    private Part wheelArches;
    private Part sideSkirts;
    private Part doorBottom;
    private Part trunkFloor;
    private Part hoodEdges;
    private Part roofEdges;
    private Part fenders;
    private Part exhaustArea;
    private Part underbody;
    private Part windowSeals;
    private Part suspension;
    public RustCheckDTO() {
    }
    public RustCheckDTO(Part wheelArches, Part sideSkirts, Part doorBottom, Part trunkFloor, Part hoodEdges,
            Part roofEdges, Part fenders, Part exhaustArea, Part underbody, Part windowSeals, Part suspension) {
        this.wheelArches = wheelArches;
        this.sideSkirts = sideSkirts;
        this.doorBottom = doorBottom;
        this.trunkFloor = trunkFloor;
        this.hoodEdges = hoodEdges;
        this.roofEdges = roofEdges;
        this.fenders = fenders;
        this.exhaustArea = exhaustArea;
        this.underbody = underbody;
        this.windowSeals = windowSeals;
        this.suspension = suspension;
    }
    public Part getWheelArches() {
        return wheelArches;
    }
    public void setWheelArches(Part wheelArches) {
        this.wheelArches = wheelArches;
    }
    public Part getSideSkirts() {
        return sideSkirts;
    }
    public void setSideSkirts(Part sideSkirts) {
        this.sideSkirts = sideSkirts;
    }
    public Part getDoorBottom() {
        return doorBottom;
    }
    public void setDoorBottom(Part doorBottom) {
        this.doorBottom = doorBottom;
    }
    public Part getTrunkFloor() {
        return trunkFloor;
    }
    public void setTrunkFloor(Part trunkFloor) {
        this.trunkFloor = trunkFloor;
    }
    public Part getHoodEdges() {
        return hoodEdges;
    }
    public void setHoodEdges(Part hoodEdges) {
        this.hoodEdges = hoodEdges;
    }
    public Part getRoofEdges() {
        return roofEdges;
    }
    public void setRoofEdges(Part roofEdges) {
        this.roofEdges = roofEdges;
    }
    public Part getFenders() {
        return fenders;
    }
    public void setFenders(Part fenders) {
        this.fenders = fenders;
    }
    public Part getExhaustArea() {
        return exhaustArea;
    }
    public void setExhaustArea(Part exhaustArea) {
        this.exhaustArea = exhaustArea;
    }
    public Part getUnderbody() {
        return underbody;
    }
    public void setUnderbody(Part underbody) {
        this.underbody = underbody;
    }
    public Part getWindowSeals() {
        return windowSeals;
    }
    public void setWindowSeals(Part windowSeals) {
        this.windowSeals = windowSeals;
    }
    public Part getSuspension() {
        return suspension;
    }
    public void setSuspension(Part suspension) {
        this.suspension = suspension;
    }
    
}
