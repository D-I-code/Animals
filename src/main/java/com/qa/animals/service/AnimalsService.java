package com.qa.animals.service;

import com.qa.animals.domain.Animals;
import com.qa.animals.repo.AnimalsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalsService implements crudInterface<Animals> {

    private AnimalsRepo repo;

    public AnimalsService(AnimalsRepo repo) {
        super();
        this.repo = repo;
    }

    @Override
    public Animals create(Animals createI) {
        return this.repo.save(createI);
    }

    @Override
    public List<Animals> read() {
        return this.repo.findAll();
    }

    @Override
    public Animals update(Long id, Animals updateI) {

        Animals existing = this.repo.findById(id).orElseThrow();
        existing.setName(updateI.getName());
        existing.setAnimalGroup(updateI.getAnimalGroup());
        existing.setSize(updateI.getSize());
        existing.setWeight(updateI.getWeight());
        existing.setNoOfLegs(updateI.getNoOfLegs());
        existing.setDiet(updateI.getDiet());
        return this.repo.saveAndFlush(existing);
    }

    @Override
    public Animals delete(Long id) {
        var toDelete = this.repo.findById(id);
        this.repo.deleteById(id);
        return toDelete.orElse(null);
    }

    @Override
    public Animals readOne(Long id) {
        var optionalC = this.repo.findById(id);
        return optionalC.orElse(null);
    }

}