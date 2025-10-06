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
@Table(name = "body_check")
public class BodyCheck {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int bodyCheckId;

    @OneToOne
    @JoinColumn(name = "mtnc_id")
    private Maintenance maintenance;

    @Enumerated(EnumType.STRING)
    private Part hood;
    @Enumerated(EnumType.STRING)
    private Part frontBumper;
    @Enumerated(EnumType.STRING)
    private Part rearBumper;
    @Enumerated(EnumType.STRING)
    private Part leftFrontDoor;
    @Enumerated(EnumType.STRING)
    private Part leftRearDoor;
    @Enumerated(EnumType.STRING)
    private Part rightFrontDoor;
    @Enumerated(EnumType.STRING)
    private Part rightRearDoor;
    @Enumerated(EnumType.STRING)
    private Part leftFrontFender;
    @Enumerated(EnumType.STRING)
    private Part rightFrontFender;
    @Enumerated(EnumType.STRING)
    private Part leftRearFender;
    @Enumerated(EnumType.STRING)
    private Part rightRearFender;
    @Enumerated(EnumType.STRING)
    private Part roof;
    @Enumerated(EnumType.STRING)
    private Part leftMirror;
    @Enumerated(EnumType.STRING)
    private Part rightMirror;
    @Enumerated(EnumType.STRING)
    private Part leftSkirt;
    @Enumerated(EnumType.STRING)
    private Part rightSkirt;
    @Enumerated(EnumType.STRING)
    private Part trunk;
    @Enumerated(EnumType.STRING)
    private Part windshield;
    @Enumerated(EnumType.STRING)
    private Part rearWindow;
    @Enumerated(EnumType.STRING)
    private Part leftFrontLight;
    @Enumerated(EnumType.STRING)
    private Part rightFrontLight;
    @Enumerated(EnumType.STRING)
    private Part leftRearLight;
    @Enumerated(EnumType.STRING)
    private Part rightRearLight;

    public BodyCheck() {
    }

    public BodyCheck(int bodyCheckId, Part frontBumper, Part hood, Part leftFrontDoor, Part leftFrontFender, Part leftFrontLight, Part leftMirror, Part leftRearDoor, Part leftRearFender, Part leftRearLight, Part leftSkirt, Maintenance maintenance, Part rearBumper, Part rearWindow, Part rightFrontDoor, Part rightFrontFender, Part rightFrontLight, Part rightMirror, Part rightRearDoor, Part rightRearFender, Part rightRearLight, Part rightSkirt, Part roof, Part trunk, Part windshield) {
        this.bodyCheckId = bodyCheckId;
        this.frontBumper = frontBumper;
        this.hood = hood;
        this.leftFrontDoor = leftFrontDoor;
        this.leftFrontFender = leftFrontFender;
        this.leftFrontLight = leftFrontLight;
        this.leftMirror = leftMirror;
        this.leftRearDoor = leftRearDoor;
        this.leftRearFender = leftRearFender;
        this.leftRearLight = leftRearLight;
        this.leftSkirt = leftSkirt;
        this.maintenance = maintenance;
        this.rearBumper = rearBumper;
        this.rearWindow = rearWindow;
        this.rightFrontDoor = rightFrontDoor;
        this.rightFrontFender = rightFrontFender;
        this.rightFrontLight = rightFrontLight;
        this.rightMirror = rightMirror;
        this.rightRearDoor = rightRearDoor;
        this.rightRearFender = rightRearFender;
        this.rightRearLight = rightRearLight;
        this.rightSkirt = rightSkirt;
        this.roof = roof;
        this.trunk = trunk;
        this.windshield = windshield;
    }



    public int getBodyCheckId() {
        return bodyCheckId;
    }

    public void setBodyCheckId(int bodyCheckId) {
        this.bodyCheckId = bodyCheckId;
    }

    public Part getHood() {
        return hood;
    }

    public void setHood(Part hood) {
        this.hood = hood;
    }

    public Part getFrontBumper() {
        return frontBumper;
    }

    public void setFrontBumper(Part frontBumper) {
        this.frontBumper = frontBumper;
    }

    public Part getRearBumper() {
        return rearBumper;
    }

    public void setRearBumper(Part rearBumper) {
        this.rearBumper = rearBumper;
    }

    public Part getLeftFrontDoor() {
        return leftFrontDoor;
    }

    public void setLeftFrontDoor(Part leftFrontDoor) {
        this.leftFrontDoor = leftFrontDoor;
    }

    public Part getLeftRearDoor() {
        return leftRearDoor;
    }

    public void setLeftRearDoor(Part leftRearDoor) {
        this.leftRearDoor = leftRearDoor;
    }

    public Part getRightFrontDoor() {
        return rightFrontDoor;
    }

    public void setRightFrontDoor(Part rightFrontDoor) {
        this.rightFrontDoor = rightFrontDoor;
    }

    public Part getRightRearDoor() {
        return rightRearDoor;
    }

    public void setRightRearDoor(Part rightRearDoor) {
        this.rightRearDoor = rightRearDoor;
    }

    public Part getLeftFrontFender() {
        return leftFrontFender;
    }

    public void setLeftFrontFender(Part leftFrontFender) {
        this.leftFrontFender = leftFrontFender;
    }

    public Part getRightFrontFender() {
        return rightFrontFender;
    }

    public void setRightFrontFender(Part rightFrontFender) {
        this.rightFrontFender = rightFrontFender;
    }

    public Part getLeftRearFender() {
        return leftRearFender;
    }

    public void setLeftRearFender(Part leftRearFender) {
        this.leftRearFender = leftRearFender;
    }

    public Part getRightRearFender() {
        return rightRearFender;
    }

    public void setRightRearFender(Part rightRearFender) {
        this.rightRearFender = rightRearFender;
    }

    public Part getRoof() {
        return roof;
    }

    public void setRoof(Part roof) {
        this.roof = roof;
    }

    public Part getLeftMirror() {
        return leftMirror;
    }

    public void setLeftMirror(Part leftMirror) {
        this.leftMirror = leftMirror;
    }

    public Part getRightMirror() {
        return rightMirror;
    }

    public void setRightMirror(Part rightMirror) {
        this.rightMirror = rightMirror;
    }

    public Part getLeftSkirt() {
        return leftSkirt;
    }

    public void setLeftSkirt(Part leftSkirt) {
        this.leftSkirt = leftSkirt;
    }

    public Part getRightSkirt() {
        return rightSkirt;
    }

    public void setRightSkirt(Part rightSkirt) {
        this.rightSkirt = rightSkirt;
    }

    public Part getTrunk() {
        return trunk;
    }

    public void setTrunk(Part trunk) {
        this.trunk = trunk;
    }

    public Part getWindshield() {
        return windshield;
    }

    public void setWindshield(Part windshield) {
        this.windshield = windshield;
    }

    public Part getRearWindow() {
        return rearWindow;
    }

    public void setRearWindow(Part rearWindow) {
        this.rearWindow = rearWindow;
    }

    public Part getLeftFrontLight() {
        return leftFrontLight;
    }

    public void setLeftFrontLight(Part leftFrontLight) {
        this.leftFrontLight = leftFrontLight;
    }

    public Part getRightFrontLight() {
        return rightFrontLight;
    }

    public void setRightFrontLight(Part rightFrontLight) {
        this.rightFrontLight = rightFrontLight;
    }

    public Part getLeftRearLight() {
        return leftRearLight;
    }

    public void setLeftRearLight(Part leftRearLight) {
        this.leftRearLight = leftRearLight;
    }

    public Part getRightRearLight() {
        return rightRearLight;
    }

    public void setRightRearLight(Part rightRearLight) {
        this.rightRearLight = rightRearLight;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }


}
