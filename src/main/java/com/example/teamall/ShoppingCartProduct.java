package com.example.teamall;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShoppingCartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Long productId;
    String name;
    int count;
    public ShoppingCartProduct(Long id,String name) {
        this.productId = id;
        this.name = name;
        this.count = 1;
    }

    public ShoppingCartProduct(Product p){
        this.productId = p.getId();
        this.name = p.getName();
        this.count = 1;
    }

    @Override
    public String toString() {
        return "{productId:"+id+",name:"+name+",count:"+count+"}";
    }

    public ShoppingCartProduct(){

    }
}
