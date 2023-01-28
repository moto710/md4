package com.phonenumbermanagement.service;

import java.util.Optional;

public interface ISmartphoneService<T> {

    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void remove(Long id);
}
