package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import org.modelmapper.ModelMapper;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;

public class ClienteMapper {
    private final ModelMapper modelMapper;

    public ClienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Cliente ConvertDTOToEntity(ClienteDto clienteDTO) {
        return modelMapper.map(clienteDTO, Cliente.class);
    }
    public ClienteDto ConvertEntityToDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDto.class);
    }
}
