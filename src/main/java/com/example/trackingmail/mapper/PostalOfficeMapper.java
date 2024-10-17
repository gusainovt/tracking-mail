package com.example.trackingmail.mapper;

import com.example.trackingmail.model.dto.postalItem.RegisterItemDto;
import com.example.trackingmail.model.dto.postalOffice.PostalOfficeDto;
import com.example.trackingmail.model.PostalOffice;
import com.example.trackingmail.model.dto.status.StatusDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostalOfficeMapper {

    PostalOfficeDto postalOfficeToPostalOfficeDto(PostalOffice postalOffice);

    @Mapping(target = "id", ignore = true)
    PostalOffice postalOfficeDtoToPostalOffice(PostalOfficeDto postalOfficeDto);

    @Mapping(target = "id", ignore = true)
    PostalOffice registerItemDtoToPostalOffice(RegisterItemDto registerItemDto);

    @Mapping(target = "id", ignore = true)
    PostalOffice statusDtoToPostalOffice(StatusDto statusDto);
}
