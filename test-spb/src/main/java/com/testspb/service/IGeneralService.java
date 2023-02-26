package com.testspb.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {

    T save(T t);

    Optional<T> findById(Long id);

    List<T> findAllByDeletedIsFalse();

    void deactivate(Long id);

    void reactivate(Long id);
}
