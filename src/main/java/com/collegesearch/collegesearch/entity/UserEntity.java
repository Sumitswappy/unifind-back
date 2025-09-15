package com.collegesearch.collegesearch.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="user-details")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String firstName;
    String lastName;
    long phone;
    @Column(unique = true)
    String email;
    String address;
    String city;
    String state;
    String password;
    String photofile;
    String profilephotoUri;
    Boolean isAdmin;
    Boolean isCollegeUser;
}
