package com.db.bts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert
@Table(name = "transactions")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    @CreationTimestamp
    private Date time;

    @Column(nullable = false)
    private float amount;

    @Column(name = "commission_value", nullable = false)
    private float commissionValue;

    @Column(name = "commission_type", nullable = false)
    private String commissionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private String type;

    @ColumnDefault("1")
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issuer_id", referencedColumnName = "id")
    @JsonIgnore
    private Admin trader;

    @Column(name = "bitcoin")
    private Float bitcoin;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName ="id")
    private Role roleId;

}
