package dev.sijaja.serviceheft.model;

import dev.sijaja.serviceheft.model.enums.Part;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rust_check")
public class RustCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int rustCheckId;
    private Part wheelArches;
    private Part sideSkirts;
    private Part doorBottom;
    private Part trunkFloor;
    private Part hoodEdges;
    private Part roofEdges;
    private Part fenders;
    private Part exhaustArea;
    private Part underbody;
    private Part WindowSeals;
    private Part suspension;

    public RustCheck() {
    }

    public RustCheck(Part WindowSeals, Part doorBottom, Part exhaustArea, Part fenders, Part hoodEdges, Part roofEdges, int rustCheckId, Part sideSkirts, Part suspension, Part trunkFloor, Part underbody, Part wheelArches) {
        this.WindowSeals = WindowSeals;
        this.doorBottom = doorBottom;
        this.exhaustArea = exhaustArea;
        this.fenders = fenders;
        this.hoodEdges = hoodEdges;
        this.roofEdges = roofEdges;
        this.rustCheckId = rustCheckId;
        this.sideSkirts = sideSkirts;
        this.suspension = suspension;
        this.trunkFloor = trunkFloor;
        this.underbody = underbody;
        this.wheelArches = wheelArches;
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
        return WindowSeals;
    }

    public void setWindowSeals(Part WindowSeals) {
        this.WindowSeals = WindowSeals;
    }

    public Part getSuspension() {
        return suspension;
    }

    public void setSuspension(Part suspension) {
        this.suspension = suspension;
    }

}
