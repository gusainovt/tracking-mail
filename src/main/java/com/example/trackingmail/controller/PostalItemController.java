package com.example.trackingmail.controller;

import com.example.trackingmail.exception.PostalItemNotFoundException;
import com.example.trackingmail.model.dto.postalItem.PostalItemDto;
import com.example.trackingmail.model.dto.postalItem.RegisterItemDto;
import com.example.trackingmail.model.dto.postalOffice.PostalOfficeDto;
import com.example.trackingmail.service.PostalItemService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postal-items")
@RequiredArgsConstructor
public class PostalItemController {

    private final PostalItemService itemService;

    @PostMapping
    public ResponseEntity<PostalItemDto> registerPostalItem(@RequestBody RegisterItemDto postalItem) {
        return ResponseEntity.ok(itemService.createPostalItem(postalItem));
    }

    @PatchMapping("/arrive/{postalItemId}")
    public ResponseEntity<PostalItemDto> arrivePostalItem(@RequestParam Long postalItemId,
                                                          @RequestBody PostalOfficeDto postalOfficeDto) {
        return ResponseEntity.ok(itemService.arrivePostalItem(postalItemId, postalOfficeDto));
    }

    @PatchMapping("/depature/{postalItemId}")
    public ResponseEntity<PostalItemDto> departurePostalItem(@RequestParam Long postalItemId) {
        return ResponseEntity.ok(itemService.departurePostalItem(postalItemId));
    }

    @PatchMapping("/receive/{postalItemId}")
    public ResponseEntity<PostalItemDto> receivePostalItem(@RequestParam Long postalItemId) {
        return ResponseEntity.ok(itemService.receivePostalItem(postalItemId));
    }

}
