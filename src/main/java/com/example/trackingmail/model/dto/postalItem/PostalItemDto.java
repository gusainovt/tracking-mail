package com.example.trackingmail.model.dto.postalItem;

import com.example.trackingmail.model.PostalItem;
import lombok.Data;

@Data
public class PostalItemDto {

    private String recipientName;

    private PostalItem.PostalType type;

    private String recipientIndex;

    private String recipientAddress;



}
