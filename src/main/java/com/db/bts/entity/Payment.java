package com.db.bts.entity;

import lombok.Data;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "payments")
@Data

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pid;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "amount")
    private String amount;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "trader_id", nullable = false)
    private String trader_id;

    @Column(name = "status")
    private String status;

}
