package com.db.bts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
//@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class TransactionSearchModel {

    private double amount;
    private double commissionValue;
    private String commissionType;
    private String userName;
    private String type;

    public TransactionSearchModel(double amount, double commissionValue, String commissionType, String userName, String type) {
        this.amount = amount;
        this.commissionValue = commissionValue;
        this.commissionType = commissionType;
        this.userName = userName;
        this.type = type;
    }

}
