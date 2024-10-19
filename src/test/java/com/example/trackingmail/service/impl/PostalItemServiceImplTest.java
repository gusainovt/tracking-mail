package com.example.trackingmail.service.impl;


import com.example.trackingmail.exception.PostalItemNotFoundException;
import com.example.trackingmail.mapper.PostalItemMapper;
import com.example.trackingmail.mapper.PostalOfficeMapper;
import com.example.trackingmail.mapper.StatusMapper;
import com.example.trackingmail.model.PostalItem;
import com.example.trackingmail.model.PostalOffice;
import com.example.trackingmail.model.Status;
import com.example.trackingmail.model.dto.postalItem.PostalItemDto;
import com.example.trackingmail.model.dto.postalItem.RegisterItemDto;
import com.example.trackingmail.model.dto.postalOffice.PostalOfficeDto;
import com.example.trackingmail.model.dto.status.StatusDto;
import com.example.trackingmail.repository.PostalItemRepository;
import com.example.trackingmail.service.PostalOfficeService;
import com.example.trackingmail.service.StatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.example.trackingmail.constants.ConstantsTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostalItemServiceImplTest {
        @InjectMocks
        private PostalItemServiceImpl postalItemService;

        @Mock
        private PostalItemRepository itemRepository;

        @Mock
        private PostalOfficeService officeService;

        @Mock
        private StatusService statusService;

        @Mock
        private PostalItemMapper itemMapper;

        @Mock
        private PostalOfficeMapper officeMapper;

        @Mock
        private StatusMapper statusMapper;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

    @Test
    void testCreatePostalItem() {
        RegisterItemDto registerItemDto = REGISTER_ITEM_DTO;
        PostalItemDto expectedPostalItemDto = POSTAL_ITEM_DTO;

        when(itemMapper.registerItemDtoToPostalItem(any(RegisterItemDto.class))).thenReturn(POSTAL_ITEM);
        when(itemRepository.save(any(PostalItem.class))).thenReturn(POSTAL_ITEM);
        when(officeMapper.registerItemDtoToPostalOffice(any(RegisterItemDto.class))).thenReturn(POSTAL_OFFICE);
        when(officeService.createPostalOffice(any(PostalOffice.class))).thenReturn(POSTAL_OFFICE);
        when(statusService.createStatus(any(PostalOffice.class), any())).thenReturn(new Status());
        when(itemMapper.postalItemToPostalItemDto(any(PostalItem.class))).thenReturn(expectedPostalItemDto);

        PostalItemDto result = postalItemService.createPostalItem(registerItemDto);

        assertNotNull(result);
        assertEquals(expectedPostalItemDto, result);
        verify(statusService).createStatus(any(), any());
    }

    @Test
    void testArrivePostalItem() {
        Long postalItemId = POSTAL_ITEM.getId();

        when(itemRepository.findById(postalItemId)).thenReturn(Optional.of(POSTAL_ITEM));
        when(officeMapper.postalOfficeDtoToPostalOffice(any(PostalOfficeDto.class))).thenReturn(POSTAL_OFFICE);
        when(statusService.createStatus(eq(POSTAL_OFFICE), any())).thenReturn(new Status());
        when(itemRepository.save(any(PostalItem.class))).thenReturn(POSTAL_ITEM);
        when(officeService.createPostalOffice(any(PostalOffice.class))).thenReturn(POSTAL_OFFICE);
        when(itemMapper.postalItemToPostalItemDto(any(PostalItem.class))).thenReturn(POSTAL_ITEM_DTO);

        PostalItemDto result = postalItemService.arrivePostalItem(postalItemId, POSTAL_OFFICE_DTO);

        assertNotNull(result);
        assertEquals(POSTAL_ITEM_DTO, result);
        verify(itemRepository).findById(postalItemId);
        verify(statusService).createStatus(eq(POSTAL_OFFICE), any());
    }

    @Test
    void testDeparturePostalItem() {
        Long postalItemId = POSTAL_ITEM.getId();

        when(officeMapper.statusDtoToPostalOffice(any(StatusDto.class))).thenReturn(POSTAL_OFFICE);
        when(statusService.createStatus(eq(POSTAL_OFFICE), any())).thenReturn(new Status());
        when(itemRepository.findById(postalItemId)).thenReturn(Optional.of(POSTAL_ITEM));
        when(itemRepository.save(any(PostalItem.class))).thenReturn(POSTAL_ITEM);
        when(itemMapper.postalItemToPostalItemDto(any(PostalItem.class))).thenReturn(POSTAL_ITEM_DTO);
        when(statusMapper.toListStatusDto(any())).thenReturn(STATUSES_DTO);

        PostalItemDto result = postalItemService.departurePostalItem(postalItemId);

        assertNotNull(result);
        assertEquals(POSTAL_ITEM_DTO, result);
        verify(itemRepository, times(2)).findById(postalItemId);
        verify(statusService).createStatus(eq(POSTAL_OFFICE), any());
    }



    @Test
    void testReceivePostalItem() {
        Long postalItemId = POSTAL_ITEM.getId();

        when(itemRepository.findById(postalItemId)).thenReturn(Optional.of(POSTAL_ITEM));
        when(statusService.createStatus(any(PostalOffice.class), any())).thenReturn(new Status());
        when(itemRepository.save(any(PostalItem.class))).thenReturn(POSTAL_ITEM);
        when(officeMapper.statusDtoToPostalOffice(any(StatusDto.class))).thenReturn(POSTAL_OFFICE);
        when(itemRepository.save(any(PostalItem.class))).thenReturn(POSTAL_ITEM);
        when(itemMapper.postalItemToPostalItemDto(any(PostalItem.class))).thenReturn(POSTAL_ITEM_DTO);
        when(statusMapper.toListStatusDto(any())).thenReturn(STATUSES_DTO);

        PostalItemDto result = postalItemService.receivePostalItem(postalItemId);

        assertNotNull(result);
        assertEquals(POSTAL_ITEM_DTO, result);
        verify(itemRepository).findById(postalItemId);
        verify(statusService).createStatus(any(), eq(Status.PostalStatus.DELIVERED));
    }

    @Test
    void testGetCurrentStatus() {
        Long postalItemId = POSTAL_ITEM.getId();


        when(itemRepository.findById(postalItemId)).thenReturn(Optional.of(POSTAL_ITEM));
        when(statusMapper.toListStatusDto(any())).thenReturn(STATUSES_DTO);

        StatusDto result = postalItemService.getCurrentStatus(postalItemId);

        assertNotNull(result);
        assertEquals(STATUS_DTO, result);
    }

    @Test
    void testGetPostalItemMovementHistory() {
        Long postalItemId = POSTAL_ITEM.getId();

        when(itemRepository.findById(postalItemId)).thenReturn(Optional.of(POSTAL_ITEM));
        when(statusMapper.toListStatusDto(any())).thenReturn(STATUSES_DTO);

        List<StatusDto> result = postalItemService.getPostalItemMovementHistory(postalItemId);

        assertNotNull(result);
        assertEquals(STATUSES_DTO, result);
    }

    @Test
    void testGetPostalItem() {
        Long postalItemId = POSTAL_ITEM.getId();

        when(itemRepository.findById(postalItemId)).thenReturn(Optional.of(POSTAL_ITEM));

        PostalItem result = postalItemService.getPostalItem(postalItemId);

        assertNotNull(result);
        assertEquals(POSTAL_ITEM, result);
    }

    @Test
    void testGetPostalItemsNotFound() {
        Long postalItemId = POSTAL_ITEM.getId();

        when(itemRepository.findById(postalItemId)).thenReturn(Optional.empty());

        assertThrows(PostalItemNotFoundException.class, () -> {
            postalItemService.getPostalItem(postalItemId);
        });
    }
    }