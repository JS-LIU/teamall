package com.example.teamall;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String name;
    String telephone;

    public User() {
    }

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


}
