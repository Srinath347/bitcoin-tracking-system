package com.db.bts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class TransactionCancelModel {

    private int userId;
    private int transactionId;
    private int traderId;
}
