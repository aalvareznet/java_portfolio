package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoReservacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoReservacion;

@Component
public class EstadoReservacionMapper {
    private final ModelMapper modelMapper;
    public EstadoReservacionMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public EstadoReservacionDto ConvertEntityToDTO(EstadoReservacion estadoReservacion) {
        return modelMapper.map(estadoReservacion, EstadoReservacionDto.class);
    }
    public EstadoReservacion ConvertDTOToEntity(EstadoReservacionDto estadoReservacionDto) {
        return modelMapper.map(estadoReservacionDto, EstadoReservacion.class);
    }
}
