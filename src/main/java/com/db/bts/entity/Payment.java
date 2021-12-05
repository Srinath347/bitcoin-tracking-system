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

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "amount")
    private float amount;

    @Column(name = "time", nullable = false)
    @CreationTimestamp
    private Date time;

    @ManyToOne
    @JoinColumn(name = "trader_id", referencedColumnName ="id")
    private Admin trader;

    @ColumnDefault("0")
    private int status;

}
