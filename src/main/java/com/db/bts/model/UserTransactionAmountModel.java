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
public class UserTransactionAmountModel {

    private double totalAmount;
    private int userId;

    public UserTransactionAmountModel(double amount, int userId) {
        this.totalAmount = amount;
        this.userId = userId;
    }

}
