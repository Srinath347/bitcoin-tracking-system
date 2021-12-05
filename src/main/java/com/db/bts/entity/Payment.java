package com.db.bts.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payments")
@Data

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pid;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "amount")
    private float amount;

    @Column(name = "time", nullable = false)
    @CreationTimestamp
    private Date time;

    @Column(name = "trader_id", nullable = false)
    private int trader_id;

    @ColumnDefault("0")
    private int status;

}
