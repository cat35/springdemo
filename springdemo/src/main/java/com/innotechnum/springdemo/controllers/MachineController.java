package com.innotechnum.springdemo.controllers;

import com.innotechnum.springdemo.entities.Machine;
import com.innotechnum.springdemo.repository.MachineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/machines")
public class MachineController {
    private final MachineRepo repo;

    @Autowired
    public MachineController(MachineRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Machine> getAllMachines() {
        return (List<Machine>) repo.findAll();
    }

    @DeleteMapping("/machines/{id}")
    public void deleteMachine(@PathVariable Long id){
        repo.deleteById(id);
    }

   @PutMapping
    public ResponseEntity<Object> updateMachines(@RequestBody Machine machine, @PathVariable Long id){
       Optional<Machine> machineOptional = repo.findById(id);

       if(!machineOptional.isPresent())
           return ResponseEntity.notFound().build();

       machine.setId(id);



       return ResponseEntity.ok(repo.save(machine));
   }

   @PostMapping
    public ResponseEntity<Object> createMachine(@RequestBody Machine machine){
        Machine savedMachine = repo.save(machine);

       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedMachine.getId()).toUri();

       return ResponseEntity.created(location).build();
   }
}
