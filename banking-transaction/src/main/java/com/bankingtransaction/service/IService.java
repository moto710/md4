package com.bankingtransaction.service;

import java.util.List;

public interface IService<T> {

    List<T> findAll();

    T findById(int id);

    void add(T t);

    void update(T t);

    void remove(int id);

    int findMaxId();
}
