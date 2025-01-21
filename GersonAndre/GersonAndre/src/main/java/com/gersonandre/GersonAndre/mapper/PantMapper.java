package com.gersonandre.GersonAndre.mapper;

import com.gersonandre.GersonAndre.dto.PantDto;
import com.gersonandre.GersonAndre.model.Pant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PantMapper {
    PantDto pantToPantDto(Pant pant);
    Pant PantDtoToPant(PantDto pantDto);
}
