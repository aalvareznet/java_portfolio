package com.gersonandre.GersonAndre.mapper;

import com.gersonandre.GersonAndre.dto.ShoeDto;
import com.gersonandre.GersonAndre.model.Shoe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoeMapper {
    ShoeDto shoeToShoeDto(Shoe shoe);
    Shoe shoeDtoToShoe(ShoeDto shoeDto);
}
