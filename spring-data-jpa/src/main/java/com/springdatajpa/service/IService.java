package com.springdatajpa.service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
}
