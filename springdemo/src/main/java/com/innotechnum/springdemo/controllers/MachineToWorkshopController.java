package com.innotechnum.springdemo.controllers;


import com.innotechnum.springdemo.entities.MachineToWorkshop;
import com.innotechnum.springdemo.repository.MachineToWorkshopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("machineToWorkshop")
public class MachineToWorkshopController {
    private final MachineToWorkshopRepo machineToWorkshopRepo;

    @Autowired
    public MachineToWorkshopController(MachineToWorkshopRepo machineToWorkshopRepo) {
        this.machineToWorkshopRepo = machineToWorkshopRepo;
    }

    @GetMapping
    public List<MachineToWorkshop> getAll() {
        return (List<MachineToWorkshop>) machineToWorkshopRepo.findAll();
    }

    @GetMapping("hello")
    public String getIt() {
        return "hello";
    }
}
