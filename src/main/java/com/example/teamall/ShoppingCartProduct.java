package com.example.teamall;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShoppingCartProduct {
    @Id
    @GeneratedValue
    Long id;

    Long productId;
    String name;
    public ShoppingCartProduct(Long id,String name) {
        this.productId = id;
        this.name = name;
    }
}
