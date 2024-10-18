package com.example.trackingmail.model.dto.status;

import com.example.trackingmail.model.Status;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class StatusDto {

    private Timestamp arrivalTime;

    private Status.PostalStatus postalStatus;

    private String index;

    private String name;

    private String address;

}
