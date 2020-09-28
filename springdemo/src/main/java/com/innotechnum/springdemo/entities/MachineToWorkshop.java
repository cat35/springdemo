package com.innotechnum.springdemo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MachineToWorkshop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate dateIn;

    private LocalDate dateOut = LocalDate.ofYearDay(3000, 1);

//    @ManyToOne
//    @JoinColumn(name = "machine_id")
//    private Machine machine;
//
//
//    @ManyToOne
//    @JoinColumn(name = "workshop_id")
//    private Workshop workshop;

    private Long idMachine;

    private Long idWorkshop;

}
