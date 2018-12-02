package com.example.teamall;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<ShoppingCartProduct> productList = new ArrayList<>();

    public ShoppingCart() {

    }
    public List addProduct(ShoppingCartProduct product){
        productList.add(product);
        return productList;
    }

}
