package com.qa.animals.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class Animals {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String animalGroup;

    private Double size;

    private Double weight;

    private Integer noOfLegs;

    private String diet;


    //Generating Constructors
    public Animals() {
    }

    public Animals(Long id, String name, String animalGroup, Double size, Double weight, Integer noOfLegs, String diet) {
        this.id = id;
        this.name = name;
        this.animalGroup = animalGroup;
        this.size = size;
        this.weight = weight;
        this.noOfLegs = noOfLegs;
        this.diet = diet;
    }

    public Animals(String name, String animalGroup, Double size, Double weight, Integer noOfLegs, String diet) {
        this.name = name;
        this.animalGroup = animalGroup;
        this.size = size;
        this.weight = weight;
        this.noOfLegs = noOfLegs;
        this.diet = diet;
    }
}
