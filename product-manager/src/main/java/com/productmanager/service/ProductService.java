package com.productmanager.service;

import com.productmanager.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IService<Product> {
    private static final Map<Integer, Product> productList;

    static {

        productList = new HashMap<>();
        productList.put(1, new Product(1, "Black Coffee", 15000, 99));
        productList.put(2, new Product(2, "Milk Coffee", 20000, 111));
        productList.put(3, new Product(3, "Orange Juice", 25000, 34));
        productList.put(4, new Product(4, "Matcha Latte", 29000, 48));
        productList.put(5, new Product(5, "Cappuccino", 33000, 15));
        productList.put(6, new Product(6, "Coke", 26000, 98));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productList.values());
    }

    @Override
    public void save(Product customer) {
        productList.put(customer.getId(), customer);
    }

    @Override
    public Product findById(int id) {
        return productList.get(id);
    }

    @Override
    public void update(int id, Product customer) {
        productList.put(id, customer);

    }

    @Override
    public void remove(int id) {
        productList.remove(id);
    }
}
