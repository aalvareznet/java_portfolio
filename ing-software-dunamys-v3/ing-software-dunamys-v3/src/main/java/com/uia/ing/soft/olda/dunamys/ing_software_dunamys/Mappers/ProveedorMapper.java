package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ProveedorCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ProveedorDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Proveedor;

@Component
public class ProveedorMapper {
    private final ModelMapper modelMapper;

    public ProveedorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Proveedor convertToEntity(ProveedorDto proveedorDto) {
        return modelMapper.map(proveedorDto, Proveedor.class);
    }
    public Proveedor convertCreateToEntity(ProveedorCrearDto proveedorDto) {
        return modelMapper.map(proveedorDto, Proveedor.class);
    }
    public ProveedorDto convertToDto(Proveedor proveedor) {
        return modelMapper.map(proveedor, ProveedorDto.class);
    }


}
