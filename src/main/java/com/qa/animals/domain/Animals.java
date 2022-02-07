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


}
