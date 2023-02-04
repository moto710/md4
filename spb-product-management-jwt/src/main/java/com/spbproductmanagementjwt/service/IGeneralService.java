package com.spbproductmanagementjwt.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void deactivate(Long id);

    void reactivate(Long id);
}
