package com.db.bts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class PaymentStatusModel {

    private int paymentId;
    private String status;
}
