package com.example.trackingmail.mapper;

import com.example.trackingmail.model.Status;
import com.example.trackingmail.model.dto.status.StatusDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StatusMapper {
    @Mappings({
            @Mapping(target = "index", source = "postalOffice.index"),
            @Mapping(target = "name", source = "postalOffice.name"),
            @Mapping(target = "address", source = "postalOffice.address")
    })
    StatusDto statusToStatusDto(Status status);

    List<StatusDto> toListStatusDto(List<Status> statuses);
}
