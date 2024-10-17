package com.example.trackingmail.service;

import com.example.trackingmail.model.dto.postalItem.PostalItemDto;
import com.example.trackingmail.model.dto.postalItem.RegisterItemDto;
import com.example.trackingmail.model.dto.postalOffice.PostalOfficeDto;
import com.example.trackingmail.model.PostalItem;
import com.example.trackingmail.model.PostalOffice;

public interface PostalItemService {
    PostalItemDto createPostalItem(RegisterItemDto registerItemDto);

    PostalItemDto arrivePostalItem(Long id, PostalOfficeDto postalOfficeDto) ;

    PostalItemDto departurePostalItem(Long id);

    PostalItemDto receivePostalItem(Long id);
}
