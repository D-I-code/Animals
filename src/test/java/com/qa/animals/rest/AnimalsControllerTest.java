package com.qa.animals.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.animals.domain.Animals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:animals-schema.sql","classpath:animals-data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")


public class AnimalsControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper mapper;


    @Test
    public void testCreateCont() throws Exception{

        Animals newA = new Animals("Viper", "reptiles", 170.2, 8.2, 0, "Carnivores");
        String newAJSON = this.mapper.writeValueAsString(newA);
//
//        RequestBuilder mockRequest = (RequestBuilder) post("/createAnimal").contentType(MediaType.APPLICATION_JSON).content(newAJSON);

        Animals savedA = new Animals(2L,"Viper", "reptiles", 170.2, 8.2, 0, "Carnivores");

        String savedAJSON = this.mapper.writeValueAsString(savedA);

//        ResultMatcher matchStatus = status().isCreated();
//        ResultMatcher matchBody = (ResultMatcher) content().json(savedAJSON);

        this.mock.perform(post("/createAnimal").contentType(MediaType.APPLICATION_JSON).content(newAJSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(savedAJSON));
    }
    @Test
    public void testGetById() throws Exception {
        Animals savedAnimal = new Animals(1L, "Red Kangaroo", "Mammal", 160.3, 39.5, 2, "Herbivores");
        String savedAnimalJSON = this.mapper.writeValueAsString(savedAnimal);

//        RequestBuilder request = (RequestBuilder) post("/getAnimal/" + savedAnimal.getId());
//
//        ResultMatcher checkStatus = status().isOk();
//        ResultMatcher checkContent = (ResultMatcher) content().json(savedAnimalJSON);

        this.mock.perform(get("/getOne/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(savedAnimalJSON));
    }

    @Test
    public void updateTest() throws Exception {
        Animals updateAnimal = new Animals("Blue Whale", "Fish", 2655.0, 130500.0, 0, "Carnivores");
        String updateAnimalJSON = this.mapper.writeValueAsString(updateAnimal);
        Long updateId = 1L;

        RequestBuilder updateReq = (RequestBuilder) put("/updateAnimal/" + updateId).contentType(MediaType.APPLICATION_JSON).content(updateAnimalJSON);

        Animals retUpdatedAnimals = new Animals(1L, "Blue Whale", "Fish", 2655.0, 130500.0, 0, "Carnivores");
        String retUpdatedChocoJSON = this.mapper.writeValueAsString(retUpdatedAnimals);

        ResultMatcher retStatus = status().isOk();
        ResultMatcher retBody = (ResultMatcher) content().json(retUpdatedChocoJSON);

        this.mock.perform(updateReq).andExpect(retStatus).andExpect(retBody);
    }

    @Test
    public void deleteTest() throws Exception {
        Animals deleteAnimal = new Animals(1L, "Red Kangaroo", "Mammal", 160.3, 39.5, 2, "Herbivores");
        String deleteAnimalsJSON = this.mapper.writeValueAsString(deleteAnimal);

        Long remId = 1L;
        RequestBuilder delRequest = delete("/removeAnimal/" + remId);
        ResultMatcher Status = status().isOk();
        ResultMatcher Body = (ResultMatcher) content().json(deleteAnimalsJSON);

        this.mock.perform(delRequest).andExpect(Status).andExpect(Body);
    }









}
