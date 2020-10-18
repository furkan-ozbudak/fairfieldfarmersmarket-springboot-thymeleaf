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
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart") //cascade = CascadeType.ALL)
    List<CartItem> cartItems;

    @OneToOne(cascade = CascadeType.PERSIST)
    private UserEntity userEntity;

}
