package com.springdatajpa.repository;

import java.util.List;

public interface IRepository<T> {
    List<T> findAll();
}
