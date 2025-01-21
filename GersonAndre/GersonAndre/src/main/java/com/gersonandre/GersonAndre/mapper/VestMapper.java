package com.gersonandre.GersonAndre.mapper;

import com.gersonandre.GersonAndre.dto.VestDto;
import com.gersonandre.GersonAndre.model.Vest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VestMapper {
    VestDto vestToVestDto(Vest vest);
    Vest vestDtoToVest(VestDto vestDto);
}
