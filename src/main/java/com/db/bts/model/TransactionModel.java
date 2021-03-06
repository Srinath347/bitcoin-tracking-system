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
	// mandatory
	private float bitcoin;
	private String commissionType;
	private int userId;
	private String type;
	// mandatory when trader initiates
	private Integer traderId;
	private float amount;
	private float commissionValue;
	private Integer roleId;
	private int status;

}
