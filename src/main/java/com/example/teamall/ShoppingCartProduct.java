package com.example.teamall;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShoppingCartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long shopId;
    Long productId;
    String name;

    int count;

    public ShoppingCartProduct(){

    }

    public ShoppingCartProduct(Long productId,Long shopId,String name) {
        this.productId = productId;
        this.name = name;
        this.shopId = shopId;
        this.count = 1;
    }
    public ShoppingCartProduct(Long shopId,Product product){
        this.shopId = shopId;
        this.productId = product.getId();
        this.name = product.getName();
        this.count = 1;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }
    @Override
    public String toString() {
        return "{productId:"+id+",name:"+name+",count:"+count+"}";
    }

    public void reCalcCount(int count) {
        this.count += count;
    }

}
