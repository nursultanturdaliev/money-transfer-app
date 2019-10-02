package com.nursultanturdaliev.moneytransferapp.model;

import javax.persistence.*;

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    private Integer id;

    @Column(length = 3, nullable = false, unique = true)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
