package com.example.trackingmail.controller;

import com.example.trackingmail.model.dto.status.StatusDto;
import com.example.trackingmail.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statuses")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @GetMapping("/movement-history/{postalItemId}")
    public ResponseEntity<List<StatusDto>> getPostalItemMovementHistory(@RequestParam Long postalItemId) {
        return ResponseEntity.ok(statusService.getPostalItemMovementHistory(postalItemId));
    }

    @GetMapping("/{postalItemId}")
    public ResponseEntity<StatusDto> getPostalItemStatus(@RequestParam Long postalItemId) {
        return ResponseEntity.ok(statusService.getCurrentStatus(postalItemId));
    }

}
