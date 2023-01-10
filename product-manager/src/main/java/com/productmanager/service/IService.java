package com.productmanager.service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    void save(T t);

    T findById(int id);

    void update(int id, T t);

    void remove(int id);
    int findMaxId();
    List<T> findByName(String name);
}
