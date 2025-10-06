package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Part;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rust_check")
public class RustCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int rustCheckId;

    @OneToOne
    @JoinColumn(name = "mtnc_id")
    private Maintenance maintenance;

    @Enumerated(EnumType.STRING)
    private Part wheelArches;
    @Enumerated(EnumType.STRING)
    private Part sideSkirts;
    @Enumerated(EnumType.STRING)
    private Part doorBottom;
    @Enumerated(EnumType.STRING)
    private Part trunkFloor;
    @Enumerated(EnumType.STRING)
    private Part hoodEdges;
    @Enumerated(EnumType.STRING)
    private Part roofEdges;
    @Enumerated(EnumType.STRING)
    private Part fenders;
    @Enumerated(EnumType.STRING)
    private Part exhaustArea;
    @Enumerated(EnumType.STRING)
    private Part underbody;
    @Enumerated(EnumType.STRING)
    private Part windowSeals;
    @Enumerated(EnumType.STRING)
    private Part suspension;

    public RustCheck() {
    }

    public RustCheck(int rustCheckId, Part doorBottom, Part exhaustArea, Maintenance maintenance, Part fenders, Part hoodEdges, Part roofEdges, Part sideSkirts, Part suspension, Part trunkFloor, Part underbody, Part wheelArches, Part windowSeals) {
        this.rustCheckId = rustCheckId;
        this.doorBottom = doorBottom;
        this.exhaustArea = exhaustArea;
        this.maintenance = maintenance;
        this.fenders = fenders;
        this.hoodEdges = hoodEdges;
        this.roofEdges = roofEdges;
        this.sideSkirts = sideSkirts;
        this.suspension = suspension;
        this.trunkFloor = trunkFloor;
        this.underbody = underbody;
        this.wheelArches = wheelArches;
        this.windowSeals = windowSeals;
    }

    public int getRustCheckId() {
        return rustCheckId;
    }

    public void setRustCheckId(int rustCheckId) {
        this.rustCheckId = rustCheckId;
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

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

}
