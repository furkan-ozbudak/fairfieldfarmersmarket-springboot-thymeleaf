package com.furkanozbudak.ffm.service;

import com.furkanozbudak.ffm.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart findByUserEntity_Id(Long id);
    ShoppingCart save(ShoppingCart shoppingCart);
}
