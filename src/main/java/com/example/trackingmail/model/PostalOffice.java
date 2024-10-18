package com.example.trackingmail.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "postal_office")
@AllArgsConstructor
@NoArgsConstructor
public class PostalOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String index;

    private String name;

    private String address;
}
