package com.qa.animals.service;

import java.util.List;

public interface crudInterface<T> {

    T create(T createI);

    List<T> read();

    T update(Long id,T updateI);

    // boolean delete(Long id);
    T delete(Long id);

    T readOne(Long id);
}