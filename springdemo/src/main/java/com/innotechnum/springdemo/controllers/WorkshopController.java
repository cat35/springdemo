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
@RequestMapping("/workshops")
public class WorkshopController {

    private final WorkshopRepo workshopRepo;

    @Autowired
    public WorkshopController(WorkshopRepo workshopRepo) {
        this.workshopRepo = workshopRepo;
    }

    @GetMapping
    public List<Workshop> getAllWorkshops() {
        return (List<Workshop>) workshopRepo.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteWorkshop(@PathVariable Long id) {
        workshopRepo.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<Workshop> updateWorkshop(@RequestBody Workshop workshop, @PathVariable Long id) {
        Optional<Workshop> typeOptional = workshopRepo.findById(id);

        if (!typeOptional.isPresent())
            return ResponseEntity.notFound().build();

        workshop.setId(id);


        return ResponseEntity.ok(workshopRepo.save(workshop));
    }

    @PostMapping
    public ResponseEntity<Workshop> createWorkshop(@RequestBody Workshop workshop) {
        Workshop savedWorkshop = workshopRepo.save(workshop);

                return ResponseEntity.ok(savedWorkshop);
    }

}
