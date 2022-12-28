package com.customer.service;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    void insert(T t);
    T select(int id);
    List<T> selectAll();
    boolean delete(int id);
    boolean update(T t);
    int findMaxId();
}
