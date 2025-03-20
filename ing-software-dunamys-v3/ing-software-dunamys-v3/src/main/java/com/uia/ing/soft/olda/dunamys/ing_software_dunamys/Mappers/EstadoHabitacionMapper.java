package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoHabitacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoHabitacion;

@Component
public class EstadoHabitacionMapper {
    private final ModelMapper modelMapper;
    
    public EstadoHabitacionMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public EstadoHabitacionDto ConvertEntityToDTO(EstadoHabitacion estadoHabitacion) {
        return modelMapper.map(estadoHabitacion, EstadoHabitacionDto.class);
    }
    public EstadoHabitacion ConvertDTOToEntity(EstadoHabitacionDto estadoHabitacionDto) {
        return modelMapper.map(estadoHabitacionDto, EstadoHabitacion.class);
    }
}
