package com.innotechnum.springdemo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Model {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   private String name;

   @ManyToOne
   @JoinColumn(name = "maker_id")
   private Maker maker;

   @ManyToOne
   @JoinColumn(name = "type_id")
   private Type type;

}
