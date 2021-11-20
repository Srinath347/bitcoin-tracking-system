package com.db.bts.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@DynamicInsert
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private Timestamp time;

    @Column(nullable = false)
    private float amount;

    @Column(name = "commission_value", nullable = false)
    private float commissionValue;

    @Column(name = "commission_type", nullable = false)
    private String commissionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String type;

    @ColumnDefault("0")
    private int status;

}
