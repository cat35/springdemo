package com.innotechnum.springdemo.controllers;

import com.innotechnum.springdemo.entities.Type;
import com.innotechnum.springdemo.entities.Workshop;
import com.innotechnum.springdemo.repository.WorkshopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class WorkshopController {

    private final WorkshopRepo workshopRepo;

    @Autowired
    public WorkshopController(WorkshopRepo workshopRepo) {
        this.workshopRepo = workshopRepo;
    }

    @GetMapping("/workshops")
    public List<Workshop> getAll() {
        return (List<Workshop>) workshopRepo.findAll();
    }

    @DeleteMapping("/workshops/{id}")
    public void deleteMachine(@PathVariable Long id){
        workshopRepo.deleteById(id);
    }

    @PutMapping("/machines/{id}")
    public ResponseEntity<Object> updateMachines(@RequestBody Workshop workshop, @PathVariable Long id){
        Optional<Workshop> typeOptional = workshopRepo.findById(id);

        if(!typeOptional.isPresent())
            return ResponseEntity.notFound().build();

        workshop.setId(id);

        workshopRepo.save(workshop);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/machines")
    public ResponseEntity<Object> createMachine(@RequestBody Workshop workshop){
        Workshop savedWorkshop = workshopRepo.save(workshop);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedWorkshop.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
