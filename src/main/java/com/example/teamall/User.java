package com.example.teamall;

import javax.persistence.*;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    String name;
    String telephone;
    @OneToOne(cascade = CascadeType.ALL)
    ShoppingCart shoppingCart = new ShoppingCart();

    public User(String telephone, String name) {
        this.name = name;
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getName() {
        return name;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public User() {
    }
    private ShoppingCart createShoppingCart(){
        this.shoppingCart = new ShoppingCart();
        return shoppingCart;
    }
}
