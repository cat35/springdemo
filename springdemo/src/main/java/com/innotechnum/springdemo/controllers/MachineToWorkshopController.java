package com.innotechnum.springdemo.controllers;


import com.innotechnum.springdemo.entities.MachineToWorkshop;
import com.innotechnum.springdemo.entities.Workshop;
import com.innotechnum.springdemo.repository.MachineToWorkshopRepo;
import com.innotechnum.springdemo.services.MachineToWorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/machineToWorkshop")
public class MachineToWorkshopController {
    private final MachineToWorkshopRepo machineToWorkshopRepo;

    private final MachineToWorkshopService service;

    @Autowired
    public MachineToWorkshopController(MachineToWorkshopRepo machineToWorkshopRepo, MachineToWorkshopService service) {
        this.machineToWorkshopRepo = machineToWorkshopRepo;
        this.service = service;
    }

    @GetMapping
    public List<MachineToWorkshop> getAll() {
        return (List<MachineToWorkshop>) machineToWorkshopRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<MachineToWorkshop> create(@RequestBody MachineToWorkshop machineToWorkshop) {
        MachineToWorkshop savedMachineToWorkshop = machineToWorkshopRepo.save(machineToWorkshop);

        return ResponseEntity.ok(savedMachineToWorkshop);
    }

    @PostMapping("/change")
    public MachineToWorkshop processTransfer(@RequestBody MachineToWorkshop machineToWorkshop) {

        return service.transfer(machineToWorkshop.getDateIn(), machineToWorkshop.getIdMachine(), machineToWorkshop.getIdWorkshop());
    }
}
