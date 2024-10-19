package com.example.trackingmail.constants;

import com.example.trackingmail.model.PostalItem;
import com.example.trackingmail.model.PostalOffice;
import com.example.trackingmail.model.Status;
import com.example.trackingmail.model.dto.postalItem.PostalItemDto;
import com.example.trackingmail.model.dto.postalItem.RegisterItemDto;
import com.example.trackingmail.model.dto.postalOffice.PostalOfficeDto;
import com.example.trackingmail.model.dto.status.StatusDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.trackingmail.model.PostalItem.PostalType.*;
import static com.example.trackingmail.model.Status.PostalStatus.*;

public class ConstantsTest {

     public static final PostalItem POSTAL_ITEM = new PostalItem(
             1L,
             "Test postal office",
             PACKAGE,
             "11111111",
             "Test address",
             new ArrayList<>());

     public static final PostalOffice POSTAL_OFFICE = new PostalOffice(
             1L,
             "Test postal office",
             "11111111",
             "Test address"
     );
     public static final StatusDto STATUS_DTO = new StatusDto(
             Timestamp.valueOf(LocalDateTime.now()),
             PENDING,
             POSTAL_OFFICE.getIndex(),
             POSTAL_OFFICE.getName(),
             POSTAL_OFFICE.getAddress());
     public static final Status STATUS = new Status(
             1L,
             Timestamp.valueOf(LocalDateTime.now()),
             PENDING,
             POSTAL_OFFICE,
             POSTAL_ITEM);
     static {
          POSTAL_ITEM.getMovementHistory().add(STATUS);
     }
     public static final RegisterItemDto REGISTER_ITEM_DTO = new RegisterItemDto(
             "Test postal office",
             PACKAGE,
             "123456",
             "123 Test Street",
             "654321",
             "Main Office",
             "456 Test Avenue");


     public static final PostalItemDto POSTAL_ITEM_DTO = new PostalItemDto(
             "John Doe",
             PACKAGE,
             "123456",
             "123 Test Street");
     public static final PostalOfficeDto POSTAL_OFFICE_DTO = new PostalOfficeDto(
             "654321",
             "Main Office",
             "456 Test Avenue");

     public static final List<StatusDto> STATUSES_DTO = new ArrayList<>(List.of(STATUS_DTO));
}
