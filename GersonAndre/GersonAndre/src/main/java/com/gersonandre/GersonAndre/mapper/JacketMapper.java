package com.gersonandre.GersonAndre.mapper;

import com.gersonandre.GersonAndre.dto.JacketDto;
import com.gersonandre.GersonAndre.model.Jacket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JacketMapper {
    JacketDto jacketToJacketDto(Jacket jacket);
    Jacket jacketDtoToJack0et(JacketDto jacketDto);
}
