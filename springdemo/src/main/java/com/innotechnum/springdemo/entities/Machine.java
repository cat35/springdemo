package com.innotechnum.springdemo.entities;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Setter
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    private int price;


}
