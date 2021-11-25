package com.db.bts.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "membership")
@Data
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "commission_rate", nullable = false)
    private Float commissionRate;

    @Column(name = "name", nullable = false)
    private String name;

}
