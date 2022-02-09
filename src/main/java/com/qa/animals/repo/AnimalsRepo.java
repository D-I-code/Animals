package com.qa.animals.repo;

import com.qa.animals.domain.Animals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalsRepo extends JpaRepository<Animals, Long> {

//    Object saveAndFlush();
}
