package com.customer.service;

import java.util.List;

public interface IDAO<T> {
    void insert(T t);
    T select(int id);
    List<T> selectAll();
    boolean delete(int id);
    boolean update(T t);
    int findMaxId();
}
