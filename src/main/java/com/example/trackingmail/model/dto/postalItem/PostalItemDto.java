package com.example.trackingmail.model.dto.postalItem;

import com.example.trackingmail.model.PostalItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostalItemDto {

    private String recipientName;

    private PostalItem.PostalType type;

    private String recipientIndex;

    private String recipientAddress;

}
