package com.db.bts.model;

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
public class UserModel {

    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String cellNumber;
    private String email;
    private String password;
    private int roleId;
    private int memberId;
}