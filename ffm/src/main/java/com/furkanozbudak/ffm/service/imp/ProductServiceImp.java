package com.furkanozbudak.ffm.service.imp;

import com.furkanozbudak.ffm.model.Product;
import com.furkanozbudak.ffm.repository.ProductRepository;
import com.furkanozbudak.ffm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product save(Product p) {
        return productRepository.save(p);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByUserEntity_Id(Long id) {

        return productRepository.findAllByUserEntity_Id(id);
    }

//    @Override
//    public List<Product> findByUserId(Long id) {
//        return productRepository.findByUserId(id);
//    }
}
