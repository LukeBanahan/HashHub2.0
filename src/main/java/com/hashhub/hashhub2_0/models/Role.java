package com.hashhub.hashhub2_0.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users = new ArrayList<>();
}


/***************************************************************************************
 *    Title: RunGroop.java
 *    Author: teddysmithdev
 *    Date: 12/20/2022
 *    Code version: 1.0
 *    Availability: https://github.com/teddysmithdev/RunGroop-Java
 *    Accessed: 01/02/2024
 *
 ***************************************************************************************/