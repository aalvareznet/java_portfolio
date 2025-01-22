package com.gersonandre.GersonAndre.mapper;

import com.gersonandre.GersonAndre.dto.AccessoryDto;
import com.gersonandre.GersonAndre.model.Accessory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccessoryMapper {
    AccessoryMapper mapper = Mappers.getMapper(AccessoryMapper.class);

    AccessoryDto accessoryToAccessoryDto(Accessory accessory);
    Accessory accessoryDtoToAccessory(AccessoryDto accessoryDto);

}
