package com.customermanagement.service;

import java.util.List;

public interface IService<T> {
    void add(T t);
    void remove(T t);
    void edit(T t);
    List<T> findAll();
}
