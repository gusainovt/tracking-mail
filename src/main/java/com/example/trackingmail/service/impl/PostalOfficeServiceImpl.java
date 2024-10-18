package com.example.trackingmail.service.impl;

import com.example.trackingmail.model.PostalOffice;
import com.example.trackingmail.repository.PostalOfficeRepository;
import com.example.trackingmail.service.PostalOfficeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Service
@Slf4j
@RequiredArgsConstructor
public class PostalOfficeServiceImpl implements PostalOfficeService {

    private final PostalOfficeRepository postalOfficeRepository;

    /**
     * Finds a post office in the database or creates a new one
     * @param postalOffice {@link PostalOffice}
     * @return {@link PostalOffice}
     */
    @Override
    public PostalOffice createPostalOffice(PostalOffice postalOffice) {
        log.info("Method called: createPostalOffice");
        return postalOfficeRepository.findByName(postalOffice.getName())
                .orElse(postalOfficeRepository.save(postalOffice));
    }
}
