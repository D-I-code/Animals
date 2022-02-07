package com.qa.animals.rest;

import com.qa.animals.domain.Animals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AnimalsController {

    private List<Animals> animalList = new ArrayList<>();

    //Response entity -> will allow us to configure the status of the response

    //C-R-U-D

    //Create
    //Response to return instead of 200: 201 - created
    @PostMapping("/createAnimal")
    public ResponseEntity<Animals> createAnimal(@RequestBody Animals a) {
        this.animalList.add(a);
        Animals newAnimal = this.animalList.get(this.animalList.size() - 1);
        return new ResponseEntity<>(newAnimal, HttpStatus.CREATED);
    }

    //Read
    @GetMapping("/getAnimal")
    public List<Animals> getAnimal() {

        return this.animalList;
    }

    //Read by ID -Get
    @GetMapping("/geOne/{id}")
    public Animals getOne(@PathVariable int id){

        return this.animalList.get(id);
    }

    //Update
    @PutMapping("updateAnimal/{id}")
    public Animals updateAnimal(@PathVariable int id, @RequestBody Animals al) {
        this.animalList.set(id, al); //will remove existing animal and will add a new one in its place
        return this.animalList.get(id); //returning the updated animal
    }

    //Delete
    @DeleteMapping("/removeAnimal/{id}")
    public Animals removeAnimal(@PathVariable int id) {

        return this.animalList.remove(id);
    }
}





