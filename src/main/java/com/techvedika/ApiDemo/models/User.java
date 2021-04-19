package com.techvedika.ApiDemo.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USR_NAME")
    private String name;

    @Column(name = "EMAIL")
    private String emailId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "MOBL_NO")
    private Long mobileNo;

    @Column(name = "CITY")
    private String city;

}
