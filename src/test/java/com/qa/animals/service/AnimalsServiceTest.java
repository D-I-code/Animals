package com.qa.animals.service;


import com.qa.animals.domain.Animals;
import com.qa.animals.repo.AnimalsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class AnimalsServiceTest {

    private Animals newAnimal;
    private Animals savedAnimal;

    @Autowired
    private AnimalsService service;

    @MockBean
    private AnimalsRepo repo;

    @BeforeEach
    void setUp() {
        newAnimal = new Animals("Viper", "reptiles", 170.2, 8.2, 0, "Carnivores");
        savedAnimal = new Animals(1L, "Viper", "reptiles", 170.2, 8.2, 0, "Carnivores");
    }

    @Test
    void createTest() {
        Mockito.when(this.repo.save(newAnimal)).thenReturn(savedAnimal);

        assertThat(this.service.create(newAnimal)).isEqualTo(savedAnimal);

        Mockito.verify(this.repo, Mockito.times(1)).save(newAnimal);
    }

    @Test
    void updateTest() {
        Long id = 1L;
        Animals toUpdate = new Animals("Red Kangaroo", "Mammal", 160.3, 39.5, 2, "Herbivores");

        Optional<Animals> optAnimal = Optional.of(new Animals(id, null, null, null, 0.0, null, null));

        Animals updated = new Animals(id, toUpdate.getName(), toUpdate.getAnimalGroup(),
                toUpdate.getSize(), toUpdate.getWeight(), toUpdate.getNoOfLegs(), toUpdate.getDiet());

        Mockito.when(this.repo.findById(id)).thenReturn(optAnimal);
        Mockito.when(this.repo.saveAndFlush(updated)).thenReturn(updated);

        assertThat(this.service.update(id, toUpdate)).isEqualTo(updated);
       
        Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(updated);
        Mockito.verify(this.repo, Mockito.times(1)).findById(id);

    }

    @Test
    void deleteTest() {
        Long id = 1L;
        Optional<Animals> optChoco = Optional.of(new Animals(id, null, null, 0.0, 0.0, null, null));
        Animals deleted = optChoco.get();

        Mockito.when(this.repo.findById(id)).thenReturn(optChoco);
        assertThat(this.service.delete(id)).isEqualTo(deleted);

        Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
    }
}
