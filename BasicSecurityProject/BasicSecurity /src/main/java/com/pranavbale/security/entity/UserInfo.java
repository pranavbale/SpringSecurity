package com.pranavbale.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


// it is used to store a user information in to the database
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue
    private UUID userId;
    private String userName;
    private String email;
    private String password;
    private String roles;
}
