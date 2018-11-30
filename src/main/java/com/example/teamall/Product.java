package com.example.teamall;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
    @GeneratedValue
    @Id
    Long id;

    String name;

    public String getName() {
        return name;
    }
    public Product(){}
    public Product(String name) {
        this.name = name;
    }
    public String toString(){
        return "{id:"+id+",name:"+name+"},";
    }
}
