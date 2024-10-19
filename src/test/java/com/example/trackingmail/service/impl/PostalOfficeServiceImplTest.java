package com.example.trackingmail.service.impl;

import com.example.trackingmail.model.PostalOffice;
import com.example.trackingmail.repository.PostalOfficeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.example.trackingmail.constants.ConstantsTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostalOfficeServiceImplTest {

    @InjectMocks
    private PostalOfficeServiceImpl postalOfficeService;

    @Mock
    private PostalOfficeRepository postalOfficeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePostalOffice() {

        when(postalOfficeRepository.findByName(POSTAL_OFFICE.getName()))
                .thenReturn(Optional.of(POSTAL_OFFICE));

        PostalOffice result = postalOfficeService.createPostalOffice(POSTAL_OFFICE);

        assertEquals(POSTAL_OFFICE, result);
        verify(postalOfficeRepository).findByName(POSTAL_OFFICE.getName());
    }

    @Test
    void testCreatePostalOfficeNotExists() {
        when(postalOfficeRepository.findByName(POSTAL_OFFICE.getName()))
                .thenReturn(Optional.empty());
        when(postalOfficeRepository.save(any(PostalOffice.class))).thenReturn(POSTAL_OFFICE);

        PostalOffice result = postalOfficeService.createPostalOffice(POSTAL_OFFICE);

        assertEquals(POSTAL_OFFICE, result);
        verify(postalOfficeRepository).findByName(POSTAL_OFFICE.getName());
        verify(postalOfficeRepository).save(POSTAL_OFFICE);
    }
}