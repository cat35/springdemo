package com.innotechnum.springdemo.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Workshop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer number;

//   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Machine> list;

//    public Workshop(Long id) {
//        this.id = id;
//    }
}
