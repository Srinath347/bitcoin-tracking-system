package com.db.bts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionModel {

    private int id;
    private Date time;
    private float amount;
    private float commissionValue;
    private String commissionType;
    private int userId;
    private int traderId;
    private String type;
    private int status;
    private Float bitcoin;
}
