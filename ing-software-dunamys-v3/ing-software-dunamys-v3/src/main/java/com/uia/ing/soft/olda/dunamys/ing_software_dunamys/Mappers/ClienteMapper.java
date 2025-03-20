package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Persona;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.User;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.UserRepository;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.PersonaServicio;

public class ClienteMapper {
    private final ModelMapper modelMapper;
    private final PersonaServicio personaServicio;
    private final UserRepository userRepository;

    public ClienteMapper(ModelMapper modelMapper
                        , PersonaServicio personaServicio
                        , UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.personaServicio = personaServicio;
    }

    public Cliente ConvertDTOToEntity(ClienteDto clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        Optional<Persona> persona = personaServicio.findById(clienteDTO.getPersonaId());
        Optional<User> user = userRepository.findById(clienteDTO.getUsuarioId());
        cliente.setPersona(persona.get());
        cliente.setUsuario(user.get());
        return cliente;
    }
    public ClienteDto ConvertEntityToDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDto.class);
    }
}
