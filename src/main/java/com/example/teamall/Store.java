package com.example.teamall;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Store {
    @GeneratedValue
    @Id
    Long id;


    private ArrayList productList = new ArrayList();

    public Store() {
    }


    public void addProduct(String name) {
        productList.add(name);
    }

    public int getCount() {
        return 1;
    }

}
