package com.example.teamall;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Store {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

    public Store() {
    }


    public void addProduct(Product product) {
        productList.add(product);
    }


    public String printProductList(){
        String s = "";
        for(int i = 0;i < productList.size();i++){
            s += "{"+productList.get(i).getName()+"},";
        }
        return s;
    }

}
