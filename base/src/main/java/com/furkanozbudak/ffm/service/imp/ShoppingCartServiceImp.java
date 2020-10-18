package com.furkanozbudak.ffm.service.imp;

import com.furkanozbudak.ffm.model.ShoppingCart;
import com.furkanozbudak.ffm.repository.ShoppingCartRepository;
import com.furkanozbudak.ffm.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShoppingCartServiceImp implements ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart findByUserEntity_Id(Long id) {
        return shoppingCartRepository.findByUserEntity_Id(id);
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }
}
