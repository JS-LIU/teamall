package com.example.teamall;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shop {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String name;

    public Shop(String name) {
        this.name = name;
    }

    public Shop() {
    }


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<ShopProduct> shopProductList = new ArrayList<>();

    public List<ShopProduct> getShopProductList() {
        return shopProductList;
    }


    public ShopProduct findShopProductById(long productId) {
        for(ShopProduct shopproduct:shopProductList){
            if(shopproduct.getId() == productId){
                return shopproduct;
            }
        }
        return null;
    }

    public void addShopProduct(ShopProduct shopProduct) {
        shopProductList.add(shopProduct);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
