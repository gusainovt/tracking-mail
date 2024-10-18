package com.example.trackingmail.mapper;

import com.example.trackingmail.model.dto.postalItem.PostalItemDto;
import com.example.trackingmail.model.PostalItem;
import com.example.trackingmail.model.dto.postalItem.RegisterItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostalItemMapper {

    PostalItemDto postalItemToPostalItemDto(PostalItem postalItem);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "movementHistory", ignore = true)
    })
    PostalItem postalItemDtoToPostalItem(PostalItemDto postalItemDto);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "movementHistory", ignore = true)
    })
    PostalItem registerItemDtoToPostalItem(RegisterItemDto registerItemDto);
}
