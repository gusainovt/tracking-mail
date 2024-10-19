package com.example.trackingmail.model.dto.status;

import com.example.trackingmail.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {

    private Timestamp arrivalTime;

    private Status.PostalStatus postalStatus;

    private String index;

    private String name;

    private String address;

}
