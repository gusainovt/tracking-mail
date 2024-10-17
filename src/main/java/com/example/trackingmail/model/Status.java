package com.example.trackingmail.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "status")
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp arrivalTime;

    @Enumerated(EnumType.STRING)
    private PostalStatus postalStatus;

    @OneToOne
    private PostalOffice postalOffice;

    @ManyToOne
    private PostalItem postalItem;
    public enum PostalStatus { PENDING, ARRIVED, DEPARTURE, DELIVERED }

}
