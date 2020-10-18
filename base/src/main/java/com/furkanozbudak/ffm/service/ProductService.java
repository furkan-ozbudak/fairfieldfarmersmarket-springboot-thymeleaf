package com.furkanozbudak.ffm.service;

import com.furkanozbudak.ffm.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product p);
    void deleteById(Long id);

    List<Product> findAllByUserEntity_Id(Long id);

}
