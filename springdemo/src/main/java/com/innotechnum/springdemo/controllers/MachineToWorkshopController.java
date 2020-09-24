package com.innotechnum.springdemo.controllers;


import com.innotechnum.springdemo.entities.MachineToWorkshop;
import com.innotechnum.springdemo.repository.MachineToWorkshopRepo;
import com.innotechnum.springdemo.services.MachineToWorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/machineToWorkshop")
public class MachineToWorkshopController {
    private final MachineToWorkshopRepo machineToWorkshopRepo;

    @Autowired
    private MachineToWorkshopService service;

    @Autowired
    public MachineToWorkshopController(MachineToWorkshopRepo machineToWorkshopRepo) {
        this.machineToWorkshopRepo = machineToWorkshopRepo;
    }



    @GetMapping
    public List<MachineToWorkshop> getAll() {
        return (List<MachineToWorkshop>) machineToWorkshopRepo.findAll();
    }

    @PostMapping
    public MachineToWorkshop processTransfer(Long idMachine, Long idWorkshop, LocalDate date){
        MachineToWorkshop machineToWorkshop = new MachineToWorkshop();
        try{

         machineToWorkshop = service.transfer(idMachine, idWorkshop, date);
        }
        catch(Exception e){

        }
        return machineToWorkshop;
    }
}
