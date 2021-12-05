package com.db.bts.model;

import com.db.bts.entity.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class TransactionTimeModel {

//    private int totalBuyTransactions;
//    private int totalSellTransactions;
//    private float totalAmountBuy;
//    private float totalAmountSell;
//    private float totalCommission;
    private List<TransactionStatistics> transactionStatistics;
    private List<Transaction> transactionList;

    public TransactionTimeModel(List<TransactionStatistics> transactionStatistics, List<Transaction> transactionList) {
        this.transactionStatistics = transactionStatistics;
        this.transactionList = transactionList;
    }
}
