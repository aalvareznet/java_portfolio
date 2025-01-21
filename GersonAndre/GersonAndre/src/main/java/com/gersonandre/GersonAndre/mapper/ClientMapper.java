package com.gersonandre.GersonAndre.mapper;

import com.gersonandre.GersonAndre.dto.ClientDto;
import com.gersonandre.GersonAndre.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto clientToClientDto(Client client);
    Client clientDtoToClient(ClientDto clientDto);
}
