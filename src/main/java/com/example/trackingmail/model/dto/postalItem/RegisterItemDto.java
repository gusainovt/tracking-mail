package com.example.trackingmail.model.dto.postalItem;

import com.example.trackingmail.model.PostalItem;
import lombok.Data;

@Data
public class RegisterItemDto {

    private String recipientName;

    private PostalItem.PostalType type;

    private String recipientIndex;

    private String recipientAddress;

    private String index;

    private String name;

    private String address;
}
