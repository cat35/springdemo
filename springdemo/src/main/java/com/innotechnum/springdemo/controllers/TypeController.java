package com.innotechnum.springdemo.controllers;

import com.innotechnum.springdemo.entities.Machine;
import com.innotechnum.springdemo.entities.Type;
import com.innotechnum.springdemo.repository.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class TypeController {

    private final TypeRepo typeRepo;

    @Autowired
    public TypeController(TypeRepo typeRepo) {
        this.typeRepo = typeRepo;
    }

    @GetMapping("/type")
    public List<Type> getAll() {
        return (List<Type>) typeRepo.findAll();
    }


    @DeleteMapping("/types/{id}")
    public void deleteMachine(@PathVariable Long id){
        typeRepo.deleteById(id);
    }

    @PutMapping("/machines/{id}")
    public ResponseEntity<Object> updateMachines(@RequestBody Type type, @PathVariable Long id){
        Optional<Type> typeOptional = typeRepo.findById(id);

        if(!typeOptional.isPresent())
            return ResponseEntity.notFound().build();

        type.setId(id);

        typeRepo.save(type);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/machines")
    public ResponseEntity<Object> createMachine(@RequestBody Type type){
        Type savedType = typeRepo.save(type);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedType.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
