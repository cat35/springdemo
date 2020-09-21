package com.innotechnum.springdemo.controllers;

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
@RequestMapping
public class WorkshopController {

    private final WorkshopRepo workshopRepo;

    @Autowired
    public WorkshopController(WorkshopRepo workshopRepo) {
        this.workshopRepo = workshopRepo;
    }

    @GetMapping("/workshops")
    public List<Workshop> getAllWorkshops() {
        return (List<Workshop>) workshopRepo.findAll();
    }

    @DeleteMapping("/workshops/{id}")
    public void deleteWorkshop(@PathVariable Long id){
        workshopRepo.deleteById(id);
    }

    @PutMapping("/workshops/{id}")
    public ResponseEntity<Object> updateWorkshop(@RequestBody Workshop workshop, @PathVariable Long id){
        Optional<Workshop> typeOptional = workshopRepo.findById(id);

        if(!typeOptional.isPresent())
            return ResponseEntity.notFound().build();

        workshop.setId(id);

        workshopRepo.save(workshop);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/workshops")
    public ResponseEntity<Object> createWorkshop(@RequestBody Workshop workshop){
        Workshop savedWorkshop = workshopRepo.save(workshop);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedWorkshop.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
