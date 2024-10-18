package com.example.trackingmail.service.impl;

import com.example.trackingmail.exception.PostalItemNotFoundException;
import com.example.trackingmail.mapper.StatusMapper;
import com.example.trackingmail.model.PostalItem;
import com.example.trackingmail.model.PostalOffice;
import com.example.trackingmail.model.Status;
import com.example.trackingmail.model.dto.status.StatusDto;
import com.example.trackingmail.repository.StatusRepository;
import com.example.trackingmail.service.PostalItemService;
import com.example.trackingmail.service.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    /**
     * Creates a new status and saves it in the database
     * @param postalOffice {@link PostalOffice}
     * @param postalStatus {@link Status.PostalStatus}
     * @return {@link Status}
     */
    @Override
    public Status createStatus(PostalOffice postalOffice, Status.PostalStatus postalStatus) {
        log.info("Method called: createStatus");
        Status status = new Status();
        status.setArrivalTime(Timestamp.valueOf(LocalDateTime.now()));
        status.setPostalStatus(postalStatus);
        status.setPostalOffice(postalOffice);
        log.info("The status has been successfully created");
        return statusRepository.save(status);
    }
}
