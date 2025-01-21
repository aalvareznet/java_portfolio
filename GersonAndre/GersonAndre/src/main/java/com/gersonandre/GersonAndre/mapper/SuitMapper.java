package com.gersonandre.GersonAndre.mapper;

import com.gersonandre.GersonAndre.dto.SuitDto;
import com.gersonandre.GersonAndre.model.Suit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuitMapper {
    SuitDto suitToSuitDto(Suit suit);
    Suit suitDtoToSuit(SuitDto suitDto);
}
