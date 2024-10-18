package com.example.trackingmail.service.impl;

import com.example.trackingmail.mapper.PostalItemMapper;
import com.example.trackingmail.mapper.PostalOfficeMapper;
import com.example.trackingmail.model.PostalItem;
import com.example.trackingmail.model.PostalOffice;
import com.example.trackingmail.model.Status;
import com.example.trackingmail.model.dto.postalItem.PostalItemDto;
import com.example.trackingmail.model.dto.postalItem.RegisterItemDto;
import com.example.trackingmail.model.dto.postalOffice.PostalOfficeDto;
import com.example.trackingmail.repository.PostalItemRepository;
import com.example.trackingmail.service.PostalOfficeService;
import com.example.trackingmail.service.StatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static com.example.trackingmail.constants.ConstantsTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostalItemServiceImplTest {
    @InjectMocks
    private PostalItemServiceImpl out;

    @Mock
    private PostalItemRepository itemRepositoryMock;

    @Mock
    private PostalOfficeService officeServiceMock;

    @Mock
    private StatusService statusServiceMock;

    @Mock
    private PostalItemMapper itemMapperMock;

    @Mock
    private PostalOfficeMapper officeMapperMock;


    @BeforeEach
    void setUp() {

    }
    @Test
    void createPostalItem() {

    }

    @Test
    void arrivePostalItem() {
    }

    @Test
    void departurePostalItem() {
    }

    @Test
    void receivePostalItem() {
    }

    @Test
    void getPostalItem() {
    }
}