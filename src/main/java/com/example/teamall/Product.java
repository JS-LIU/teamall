package com.example.teamall;

import javax.persistence.*;

@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String name;

    public String getName() {
        return name;
    }
    public Long getId(){
        return id;
    }
    public Product(){}
    public Product(String name) {
        this.name = name;
    }

    public String toString(){
        return "{id:"+id+",name:"+name+"},";
    }
}
