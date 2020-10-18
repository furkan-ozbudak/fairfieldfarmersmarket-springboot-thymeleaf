package com.furkanozbudak.ffm.service;

import com.furkanozbudak.ffm.model.CartItem;

public interface CartItemService {
    CartItem save(CartItem cartItem);
    CartItem findById(Long id);
    void deleteById(Long id);
}
