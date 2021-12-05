package com.db.bts.model;

import com.db.bts.entity.Admin;
import com.db.bts.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
//@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class TransactionSearchModel {

    private int id;
    private Date time;
    private double amount;
    private double commissionValue;
    private String commissionType;
    private User user;
    private String type;
    private Admin trader;
    private float bitcoin;

    public TransactionSearchModel(int id, Date time, double amount, double commissionValue, String commissionType, User user, String type, Admin trader, float bitcoin) {
        this.id = id;
        this.time = time;
        this.amount = amount;
        this.commissionValue = commissionValue;
        this.commissionType = commissionType;
        this.user = user;
        this.type = type;
        this.trader = trader;
        this.bitcoin = bitcoin;
    }
}
