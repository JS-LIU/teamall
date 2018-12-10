package com.example.teamall;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    private Long shopId;
    private Long userId;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<ShoppingCartProduct> productList = new ArrayList<>();

    String name;
    public ShoppingCart() {

    }


    public ShoppingCart(Long shopId,String name) {
        this.name = name;
        this.shopId = shopId;

    }

    public ShoppingCart(Long userId,Long shopId) {
        this.userId = userId;
        this.shopId = shopId;

    }

    public List<ShoppingCartProduct> getProductList() {
        return productList;
    }

    public List addProduct(ShoppingCartProduct shoppingCartProduct){
        ShoppingCartProduct shoppingCartP = findShoppingCartProduct(shoppingCartProduct.getProductId());

        if(shoppingCartP == null){
            productList.add(shoppingCartProduct);
        }else{
            shoppingCartP.reCalcCount(shoppingCartProduct.getCount());
        }
        return productList;
    }

    private ShoppingCartProduct findShoppingCartProduct(Long shoppingCartProductId) {
        for(ShoppingCartProduct shoppingCartProduct:productList){
            if(shoppingCartProduct.getProductId().equals(shoppingCartProductId)){
                return shoppingCartProduct;
            }
        }
        return null;
    }


    public Long getShopId() {
        return shopId;
    }
}
