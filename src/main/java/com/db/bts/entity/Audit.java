package com.db.bts.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "audit")
@Data
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "time")
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName ="id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "trader_id", referencedColumnName ="id")
    private Admin trader;

    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName ="id")
    private Transaction transaction;

    @Column(name = "message", nullable = false)
    private String message;
}
