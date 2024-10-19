package com.example.trackingmail.service.impl;

import com.example.trackingmail.model.Status;
import com.example.trackingmail.repository.StatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.example.trackingmail.constants.ConstantsTest.*;
import static com.example.trackingmail.model.Status.PostalStatus.PENDING;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatusServiceImplTest {

    @InjectMocks
    private StatusServiceImpl statusService;

    @Mock
    private StatusRepository statusRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStatus() {
        when(statusRepository.save(any(Status.class))).thenReturn(STATUS);

        Status result = statusService.createStatus(POSTAL_OFFICE, PENDING);

        assertEquals(STATUS, result);
        verify(statusRepository).save(any(Status.class));
    }
}