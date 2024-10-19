package com.example.trackingmail.controller;

import com.example.trackingmail.model.dto.postalItem.PostalItemDto;
import com.example.trackingmail.model.dto.postalItem.RegisterItemDto;
import com.example.trackingmail.model.dto.postalOffice.PostalOfficeDto;
import com.example.trackingmail.model.dto.status.StatusDto;
import com.example.trackingmail.service.PostalItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postal-items")
@RequiredArgsConstructor
public class PostalItemController {

    private final PostalItemService itemService;

    @Operation(summary = "Register a new postal item",
            description = "This endpoint allows for the registration of a new postal item.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Postal item registered successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PostalItemDto.class))),
            })
    @PostMapping
    public ResponseEntity<PostalItemDto> registerPostalItem(@RequestBody RegisterItemDto postalItem) {
        return ResponseEntity.ok(itemService.createPostalItem(postalItem));
    }

    @Operation(summary = "Mark postal item as arrived at post office",
            description = "Updates the status of the postal item to indicate it has arrived at the specified post office.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Postal item arrival updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PostalItemDto.class))),
                    @ApiResponse(responseCode = "200", description = "Postal item not found")
                     })
    @PatchMapping("/arrive/{postalItemId}")
    public ResponseEntity<PostalItemDto> arrivePostalItem(@RequestParam Long postalItemId,
                                                          @RequestBody PostalOfficeDto postalOfficeDto) {
        return ResponseEntity.ok(itemService.arrivePostalItem(postalItemId, postalOfficeDto));
    }

    @Operation(summary = "Mark postal item as departed from post office",
            description = "Updates the status of the postal item to indicate it has departed from the post office.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Postal item departure updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PostalItemDto.class))),
                    @ApiResponse(responseCode = "200", description = "Postal item not found")
            })
    @PatchMapping("/depature/{postalItemId}")
    public ResponseEntity<PostalItemDto> departurePostalItem(@RequestParam Long postalItemId) {
        return ResponseEntity.ok(itemService.departurePostalItem(postalItemId));
    }

    @Operation(summary = "Receive postal item by addressee",
            description = "Marks the postal item as received by the addressee.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Postal item received successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PostalItemDto.class))),
                    @ApiResponse(responseCode = "200", description = "Postal item not found"),
            })
    @PatchMapping("/receive/{postalItemId}")
    public ResponseEntity<PostalItemDto> receivePostalItem(@RequestParam Long postalItemId) {
        return ResponseEntity.ok(itemService.receivePostalItem(postalItemId));
    }

    @Operation(summary = "Get the movement history of a postal item",
            description = "Retrieves the complete movement history for the specified postal item.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Movement history received successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = "200", description = "Postal item not found")
            })
    @GetMapping("statuses/movement-history/{postalItemId}")
    public ResponseEntity<List<StatusDto>> getPostalItemMovementHistory(@RequestParam Long postalItemId) {
        return ResponseEntity.ok(itemService.getPostalItemMovementHistory(postalItemId));
    }

    @Operation(summary = "Get the current status of a postal item",
            description = "Retrieves the current status of the specified postal item.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Current status received successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StatusDto.class))),
                    @ApiResponse(responseCode = "404", description = "Postal item not found")
            })
    @GetMapping("statuses/{postalItemId}")
    public ResponseEntity<StatusDto> getPostalItemStatus(@RequestParam Long postalItemId) {
        return ResponseEntity.ok(itemService.getCurrentStatus(postalItemId));
    }

}
