package com.furkanozbudak.ffm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;

    //for seller
    private double revenue;

    //for buyer and seller
    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private PaymentCard paymentCard;

    //for seller
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<Product> products;

    //for buyer
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntities;

    //for buyer
    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;













}
