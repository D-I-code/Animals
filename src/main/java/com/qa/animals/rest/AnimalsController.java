package com.qa.animals.rest;

import com.qa.animals.domain.Animals;
import com.qa.animals.service.AnimalsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnimalsController {

    private AnimalsService service;

    public AnimalsController(AnimalsService service) {
        super();
        this.service = service;
    }

    @PostMapping("/createAnimal")
    public ResponseEntity<Animals> createAnimal(@RequestBody Animals al) {
        return new ResponseEntity<>(this.service.create(al), HttpStatus.CREATED);
    }

    @GetMapping("/getAnimals")
    public List<Animals> getAnimal() {
        return this.service.read();
    }

    @GetMapping("/getOne/{id}")
    public Animals getOne(@PathVariable Long id) {
        return this.service.readOne(id);
    }

    @PutMapping("/updateAnimal/{id}")
    public Animals updateAnimal(@PathVariable Long id, @RequestBody Animals al) {
        return this.service.update(id,al);
    }

    @DeleteMapping("/removeAnimal/{id}")
    public Animals removeAnimal(@PathVariable Long id) {
        return this.service.delete(id);
    }
}






