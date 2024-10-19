package com.example.trackingmail.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "postal_item")
public class PostalItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recipient_name")
    private String recipientName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PostalType type;

    @Column(name = "recipient_index")
    private String recipientIndex;

    @Column(name = "recipient_address")
    private String recipientAddress;

    @OneToMany
    private List<Status> movementHistory;

    public enum PostalType { LETTER, PACKAGE, PARCEL, POSTCARD }

}
