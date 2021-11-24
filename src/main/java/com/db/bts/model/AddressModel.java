package com.db.bts.model;

import com.db.bts.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)

public class AddressModel {

    private int id;
    private String street;
    private String city;
    private String state;
    private int zipcode;
    private User user;
}