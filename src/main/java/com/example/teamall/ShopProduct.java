package com.example.teamall;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShopProduct {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    Long id;

    String name;
    Long productId;

    public ShopProduct() {
    }

    public ShopProduct(Long id,String name) {
        this.name = name;
        this.productId = id;
    }
}
