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
//    private List<TransactionStatistics> transactionStatistics;
    private TransactionStatistics buy;
    private TransactionStatistics sell;
    private List<Transaction> transactionList;
    private String from;
    private String to;

    public TransactionTimeModel(TransactionStatistics buy, TransactionStatistics sell, List<Transaction> transactionList, String from, String to) {
        this.buy = buy;
        this.sell = sell;
        this.transactionList = transactionList;
        this.from = from;
        this.to = to;
    }
}
