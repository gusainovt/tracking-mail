package com.example.trackingmail.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "postal_office")
public class PostalOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String index;

    private String name;

    private String address;
}
