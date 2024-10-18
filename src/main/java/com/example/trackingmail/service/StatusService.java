package com.example.trackingmail.service;

import com.example.trackingmail.model.PostalOffice;
import com.example.trackingmail.model.Status;
import com.example.trackingmail.model.dto.status.StatusDto;

import java.util.List;

public interface StatusService {
    Status createStatus(PostalOffice postalOffice, Status.PostalStatus postalStatus);

}
