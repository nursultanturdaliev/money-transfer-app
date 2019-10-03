package com.nursultanturdaliev.moneytransferapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @JsonIgnore
    private Integer id;

    @Column(length = 3, nullable = false, unique = true)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    @OneToMany(mappedBy = "currency")
    @JsonBackReference
    private List<Transaction> transaction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
