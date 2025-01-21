package com.gersonandre.GersonAndre.mapper;

import com.gersonandre.GersonAndre.dto.ShirtDto;
import com.gersonandre.GersonAndre.model.Shirt;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShirtMapper {
    ShirtDto shirtToShirtDto(Shirt shirt);
    Shirt shirtDtoToShirt(ShirtDto shirtDto);
}
