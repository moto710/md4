package com.shoppingcart.service;


import java.util.Optional;

public interface IProductService<T> {

    Iterable<T> findAll();

    Optional<T> findById(Long id);
}
