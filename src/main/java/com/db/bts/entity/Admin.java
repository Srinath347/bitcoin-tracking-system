package com.db.bts.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "cell_number")
    private String cellNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName ="id")
    private Role role;
}
