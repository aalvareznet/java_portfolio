package com.gersonandre.GersonAndre.mapper;

import com.gersonandre.GersonAndre.dto.ClientDto;
import com.gersonandre.GersonAndre.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper mapper = Mappers.getMapper(ClientMapper.class);
    ClientDto clientToClientDto(Client client);
    Client clientDtoToClient(ClientDto clientDto);
}
