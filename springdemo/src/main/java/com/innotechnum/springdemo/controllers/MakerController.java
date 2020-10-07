package com.innotechnum.springdemo.controllers;

import com.innotechnum.springdemo.entities.Maker;
import com.innotechnum.springdemo.repository.MakerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/maker")
public class MakerController {

    private final MakerRepo makerRepo;

    @Autowired
    public MakerController(MakerRepo makerRepo) {
        this.makerRepo = makerRepo;
    }

    @GetMapping
    public List<Maker> getAllMakers() {
        return (List<Maker>) makerRepo.findAll();
    }

    @DeleteMapping("/makers/{id}")
    public void deleteMaker(@PathVariable Long id){
        makerRepo.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<Maker> updateMaker(@RequestBody Maker maker, @PathVariable Long id){
        Optional<Maker> makerOptional = makerRepo.findById(id);

        if(!makerOptional.isPresent())
            return ResponseEntity.notFound().build();

        maker.setId(id);



        return ResponseEntity.ok(makerRepo.save(maker));
    }

    @PostMapping
    public ResponseEntity<Maker> createMaker(@RequestBody Maker maker){
        Maker savedMaker = makerRepo.save(maker);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedMaker.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
