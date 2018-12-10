package com.example.teamall;

import javax.persistence.*;

@Entity
public class ShopProduct {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String name;
    Long productId;
    Long shopId;
    Long stockNum;

    public ShopProduct(Long productId, int count) {
        this.productId = productId;

    }
    public Long getId() {
        return id;
    }

    public ShopProduct() {
    }

    public ShopProduct(Long id,String name,Long shopId) {
        this.name = name;
        this.productId = id;
        this.shopId = shopId;
    }

    public ShopProduct(Product product,Long stockNum,Long shopId) {
        this.productId = product.getId();
        this.name = product.getName();
        this.stockNum = stockNum;
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public Long getProductId() {
        return productId;
    }
}
