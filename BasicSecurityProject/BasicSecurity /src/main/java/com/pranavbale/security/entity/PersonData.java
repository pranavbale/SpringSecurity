package com.pranavbale.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonData {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String address;
    private String number;
    private String email;
}
