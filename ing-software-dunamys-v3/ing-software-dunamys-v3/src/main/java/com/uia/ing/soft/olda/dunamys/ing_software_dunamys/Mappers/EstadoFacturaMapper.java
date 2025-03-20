package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoFacturaDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoFactura;

@Component
public class EstadoFacturaMapper {
    private final ModelMapper modelMapper;

    public EstadoFacturaMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public EstadoFacturaDto ConvertEntityToDTO(EstadoFactura estadoFactura) {
        return modelMapper.map(estadoFactura, EstadoFacturaDto.class);
    }
    public EstadoFactura ConvertDTOToEntity(EstadoFacturaDto estadoFacturaDto) {
        return modelMapper.map(estadoFacturaDto, EstadoFactura.class);
    }
}
