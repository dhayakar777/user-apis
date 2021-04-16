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

    @Column(name = "CRTD_BY")
    private String createdBy;

    @Column(name = "UPDTD_BY")
    private String updatedBy;
}
