package com.db.bts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class TransactionStatistics {

    private long count;
    private String type;
    private double amount;
    private double commission;

    public TransactionStatistics(long count, String type, double amount, double commission) {
        this.count = count;
        this.type = type;
        this.amount = amount;
        this.commission = commission;
    }
}
