package com.qa.animals.service;

import java.util.List;

public interface crudInterface<Animals> {

    Animals create(Animals createI);

    List<Animals> read();

    Animals update(Long id,Animals updateI);

    // boolean delete(Long id);
    Animals delete(Long id);

    Animals readOne(Long id);
}