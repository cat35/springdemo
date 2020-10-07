package com.innotechnum.springdemo.controllers;
import com.innotechnum.springdemo.entities.Machine;
import com.innotechnum.springdemo.entities.Model;
import com.innotechnum.springdemo.repository.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/models")
public class ModelController {

    private final ModelRepo modelRepo;

    @Autowired
    public ModelController(ModelRepo modelRepo) {
        this.modelRepo = modelRepo;
    }

    @GetMapping
    public List<Model> getAllModels(){
        return (List<Model>) modelRepo.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id){
        modelRepo.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<Model> updateModels(@RequestBody Model model, @PathVariable Long id){
        Optional<Model> modelOptional = modelRepo.findById(id);

        if(!modelOptional.isPresent())
            return ResponseEntity.notFound().build();

        model.setId(id);



        return ResponseEntity.ok(modelRepo.save(model));
    }

    @PostMapping
    public ResponseEntity<Model> createModels(@RequestBody Model model){
        Model savedModel = modelRepo.save(model);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedModel.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
