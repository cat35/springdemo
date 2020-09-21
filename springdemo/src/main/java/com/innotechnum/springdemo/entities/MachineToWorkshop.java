package com.innotechnum.springdemo.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class MachineToWorkshop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate dateIn;

    private LocalDate dateOut;

    @ManyToOne
    @JoinColumn(name = "machine")
    private Machine machine;


    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

}
