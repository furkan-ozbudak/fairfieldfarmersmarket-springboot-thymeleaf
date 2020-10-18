package com.furkanozbudak.ffm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

    @ManyToOne
    private UserEntity userEntity;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private OrderEntity orderEntity;

    @OneToOne(mappedBy = "product")
    private CartItem cartItem;


}
