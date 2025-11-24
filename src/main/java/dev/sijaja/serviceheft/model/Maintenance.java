package dev.sijaja.serviceheft.model;


import java.time.LocalDate;

import dev.sijaja.serviceheft.model.enums.Status;
import dev.sijaja.serviceheft.model.enums.Condition;
import dev.sijaja.serviceheft.model.enums.Type;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="maintenance")
public class Maintenance {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int mtncId;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Cars car;
    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;
    @Enumerated(EnumType.STRING)
    private Condition carCondition;
    private String inspectionNotes;
    private LocalDate startDate;
    private LocalDate mtncDate;
    private int currentMileage;
    private int nextMileage;
    private LocalDate nextDate;
    private double cost;
    @Enumerated(EnumType.STRING)
    private Type mtncType;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Embedded
    private BeltHoseCheck beltHoseCheck;
    

    public Maintenance() {
    }


}
