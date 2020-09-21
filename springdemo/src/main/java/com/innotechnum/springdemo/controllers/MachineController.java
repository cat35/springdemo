package com.innotechnum.springdemo.controllers;

import com.innotechnum.springdemo.entities.Machine;
import com.innotechnum.springdemo.repository.MachineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class MachineController {
    private final MachineRepo repo;

    @Autowired
    public MachineController(MachineRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/machines")
    public List<Machine> getAllMachines() {
        return (List<Machine>) repo.findAll();
    }

    @DeleteMapping("/machines/{id}")
    public void deleteMachine(@PathVariable Long id){
        repo.deleteById(id);
    }

   @PutMapping("/machines/{id}")
    public ResponseEntity<Object> updateMachines(@RequestBody Machine machine, @PathVariable Long id){
       Optional<Machine> machineOptional = repo.findById(id);

       if(!machineOptional.isPresent())
           return ResponseEntity.notFound().build();

       machine.setId(id);

       repo.save(machine);

       return ResponseEntity.noContent().build();
   }

   @PostMapping("/machines")
    public ResponseEntity<Object> createMachine(@RequestBody Machine machine){
        Machine savedMachine = repo.save(machine);

       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedMachine.getId()).toUri();

       return ResponseEntity.created(location).build();
   }
}
