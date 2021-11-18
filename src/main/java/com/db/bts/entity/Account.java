package com.db.bts.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "bitcoin")
    private float bitcoin;

    @Column(name = "balance")
    private float balance;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName ="id")
    private User user;

}
