package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteInfoPersonaDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Persona;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.PersonaRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.User;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.UserRepository;

@Component
public class ClienteMapper {
    private final ModelMapper modelMapper;
    private final PersonaRepositorio personaRepositorio;
    private final UserRepository userRepository;

    public ClienteMapper(ModelMapper modelMapper
                        , PersonaRepositorio personaRepositorio
                        , UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.personaRepositorio = personaRepositorio;
    }

    public Cliente ConvertDTOToEntity(ClienteDto clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        Optional<Persona> persona = personaRepositorio.findById(clienteDTO.getPersonaId());
        Optional<User> user = userRepository.findById(clienteDTO.getUsuarioId());
        cliente.setPersona(persona.get());
        cliente.setUsuario(user.get());
        return cliente;
    }
    public ClienteDto ConvertEntityToDTO(Cliente cliente) {
        ClienteDto clienteDto = modelMapper.map(cliente, ClienteDto.class);
        clienteDto.setPersonaId(cliente.getPersona().getId());
        clienteDto.setUsuarioId(cliente.getUsuario().getId());
        return clienteDto;
    }

    public ClienteInfoPersonaDto ConvertEntityToInfoDto(Cliente cliente){
        ClienteInfoPersonaDto clienteDto = modelMapper.map(cliente, ClienteInfoPersonaDto.class);
        clienteDto.setPersonaId(cliente.getPersona().getId());
        clienteDto.setUsuarioId(cliente.getUsuario().getId());
        clienteDto.setNombrePersona(cliente.getPersona().getNombre());
        clienteDto.setPrimerApellidoPersona(cliente.getPersona().getPrimerApellido());
        clienteDto.setSegundoApellidoPerson(cliente.getPersona().getSegundoApellido());
        clienteDto.setEmail(cliente.getPersona().getCorreo());
        clienteDto.setTelefono(cliente.getPersona().getTelefono());
        clienteDto.setPais(cliente.getPersona().getPais());
        return clienteDto;
    }
}
