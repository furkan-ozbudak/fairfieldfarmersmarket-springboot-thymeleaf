package com.furkanozbudak.ffm.repository;

import com.furkanozbudak.ffm.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByUserEntity_Id(Long id);
}
