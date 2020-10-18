package com.furkanozbudak.ffm.service.imp;

import com.furkanozbudak.ffm.model.CartItem;
import com.furkanozbudak.ffm.repository.CartItemRepository;
import com.furkanozbudak.ffm.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartItemServiceImp implements CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }
}
