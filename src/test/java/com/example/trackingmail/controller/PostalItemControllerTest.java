package com.example.trackingmail.controller;

import com.example.trackingmail.model.dto.postalItem.PostalItemDto;
import com.example.trackingmail.model.dto.postalItem.RegisterItemDto;
import com.example.trackingmail.model.dto.postalOffice.PostalOfficeDto;
import com.example.trackingmail.model.dto.status.StatusDto;
import com.example.trackingmail.service.PostalItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostalItemControllerTest {

    @InjectMocks
    private PostalItemController postalItemController;

    @Mock
    private PostalItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterPostalItem() {
        RegisterItemDto registerItemDto = new RegisterItemDto();
        PostalItemDto postalItemDto = new PostalItemDto();

        when(itemService.createPostalItem(any(RegisterItemDto.class))).thenReturn(postalItemDto);

        ResponseEntity<PostalItemDto> response = postalItemController.registerPostalItem(registerItemDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(postalItemDto, response.getBody());
        verify(itemService).createPostalItem(registerItemDto);
    }

    @Test
    void testArrivePostalItem() {
        Long postalItemId = 1L;
        PostalOfficeDto postalOfficeDto = new PostalOfficeDto();
        PostalItemDto postalItemDto = new PostalItemDto();

        when(itemService.arrivePostalItem(anyLong(), any(PostalOfficeDto.class))).thenReturn(postalItemDto);

        ResponseEntity<PostalItemDto> response = postalItemController.arrivePostalItem(postalItemId, postalOfficeDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(postalItemDto, response.getBody());
        verify(itemService).arrivePostalItem(postalItemId, postalOfficeDto);
    }

    @Test
    void testDeparturePostalItem() {
        Long postalItemId = 1L;
        PostalItemDto postalItemDto = new PostalItemDto();

        when(itemService.departurePostalItem(anyLong())).thenReturn(postalItemDto);

        ResponseEntity<PostalItemDto> response = postalItemController.departurePostalItem(postalItemId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(postalItemDto, response.getBody());
        verify(itemService).departurePostalItem(postalItemId);
    }

    @Test
    void testReceivePostalItem() {
        Long postalItemId = 1L;
        PostalItemDto postalItemDto = new PostalItemDto();

        when(itemService.receivePostalItem(anyLong())).thenReturn(postalItemDto);

        ResponseEntity<PostalItemDto> response = postalItemController.receivePostalItem(postalItemId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(postalItemDto, response.getBody());
        verify(itemService).receivePostalItem(postalItemId);
    }

    @Test
    void testGetPostalMovementHistory() {
        Long postalItemId = 1L;
        List<StatusDto> statusList = Collections.singletonList(new StatusDto());

        when(itemService.getPostalItemMovementHistory(anyLong())).thenReturn(statusList);

        ResponseEntity<List<StatusDto>> response = postalItemController.getPostalItemMovementHistory(postalItemId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(statusList, response.getBody());
        verify(itemService).getPostalItemMovementHistory(postalItemId);
    }

    @Test
    void testGetCurrentStatus() {
        Long postalItemId = 1L;
        StatusDto statusDto = new StatusDto();

        when(itemService.getCurrentStatus(anyLong())).thenReturn(statusDto);

        ResponseEntity<StatusDto> response = postalItemController.getPostalItemStatus(postalItemId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(statusDto, response.getBody());
        verify(itemService).getCurrentStatus(postalItemId);
    }
}