package com.db.bts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionModel {

    private int id;
    private Timestamp time;
    private float amount;
    private float commissionValue;
    private String commissionType;
    private UserModel user;
    private String type;
    private int status;

}
