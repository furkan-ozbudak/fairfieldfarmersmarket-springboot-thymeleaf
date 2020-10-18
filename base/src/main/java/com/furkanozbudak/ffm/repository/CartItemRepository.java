package com.furkanozbudak.ffm.repository;

import com.furkanozbudak.ffm.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

}
