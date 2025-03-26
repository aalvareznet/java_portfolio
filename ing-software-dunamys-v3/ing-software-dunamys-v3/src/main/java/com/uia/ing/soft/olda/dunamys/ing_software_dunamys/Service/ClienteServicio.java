package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteInfoPersonaDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.ClienteMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Persona;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ClienteRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.auth.AuthResponse;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.auth.AuthService;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.auth.RegisterRequest;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.User;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteServicio extends BaseService<Cliente, Integer> {

    @Autowired
    private ClienteRepositorio repo;
    @Autowired
    private AuthService authService;
    @Autowired
    private PersonaServicio personaServicio;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ClienteMapper mapper;
    @Autowired
    private LogAuditoriaServicio logServicio;

    protected ClienteRepositorio getRepository() {
        return repo;
    }

    @Transactional
    public ClienteDto agregarClientePersonaUsuario(ClienteCrearDto cliente) {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .username(cliente.getUsername())
                .password(cliente.getPassword())
                .build();
        AuthResponse userAgregado = authService.register(registerRequest);
        if (userAgregado == null) {
            throw new IllegalStateException("Error al crear el usuario");
        }
        Optional<User> busquedaUser = userRepo.findByUsername(cliente.getUsername());
        if (!busquedaUser.isPresent()) {
            throw new IllegalStateException("Error al buscar usuario");
        }
        Persona personaPorAgregar = Persona.builder()
                .nombre(cliente.getNombre())
                .primerApellido(cliente.getPrimerApellido())
                .segundoApellido(cliente.getSegundoApellido())
                .telefono(cliente.getTelefono())
                .correo(cliente.getCorreo())
                .pais(cliente.getPais())
                .build();
        Persona personaAgregada = personaServicio.create(personaPorAgregar);
        if (personaAgregada == null) {
            throw new IllegalStateException("Error crear la persona usuario");
        }
        Cliente clientePorCrear = Cliente.builder()
                .cantidadVisitas(0)
                .persona(personaAgregada)
                .usuario(busquedaUser.get())
                .build();
        Cliente clienteCreado = this.create(clientePorCrear);
        ClienteDto clienteDto = mapper.ConvertEntityToDTO(clienteCreado);
        return clienteDto;
    }

    public ClienteDto actualizar(Integer id, Persona persona, Integer usuarioId){
        Optional<Cliente> clienteEncontrado = this.findById(id);
        if(clienteEncontrado.isPresent()){
            Optional<Persona> personaEncontrada = personaServicio.findById(clienteEncontrado.get().getPersona().getId());
            if(personaEncontrada.isPresent()){
                Persona personaParaActualizar = personaEncontrada.get();
                personaParaActualizar.setNombre(persona.getNombre());
                personaParaActualizar.setPrimerApellido(persona.getPrimerApellido());
                personaParaActualizar.setSegundoApellido(persona.getSegundoApellido());
                personaParaActualizar.setTelefono(persona.getTelefono());
                personaParaActualizar.setCorreo(persona.getCorreo());
                personaParaActualizar.setPais(persona.getPais());
                personaServicio.update(personaParaActualizar);
                ClienteDto clienteDto = mapper.ConvertEntityToDTO(clienteEncontrado.get());
                return clienteDto;
            }
        }
        return null;
    }

    public ClienteDto obtenerClientePorId(Integer id){
        Optional<Cliente> cliente = this.findById(id);
        if(cliente.isPresent()){
            ClienteDto clienteDto = mapper.ConvertEntityToDTO(cliente.get());
            return clienteDto;
        }
        return null;
    }
    public String borrar(Integer userId, Integer clienteId){
        Optional<Cliente> cliente = this.findById(clienteId);
        if(cliente.isPresent()){
            this.delete(clienteId);
            logServicio.guardarAccion(userId, "Cliente borrado", "cliente");
            return "Cliente eliminado";
        }
        return null;
    }
    public List<ClienteDto> obtenerTodos(){
        List<Cliente> clientes = this.findAll();
        List<ClienteDto> clientesDto = clientes.stream()
                                                .map(cliente -> mapper.ConvertEntityToDTO(cliente))
                                                .collect(Collectors.toList());     
        return clientesDto;
    }
    public ClienteInfoPersonaDto obtenerClientePorIdUsuario(Integer id){
        Optional<Cliente> cliente = repo.findByUsuarioId(id);
        if(cliente.isPresent()){
            ClienteInfoPersonaDto clienteRespuesta = mapper.ConvertEntityToInfoDto(cliente.get());
            return clienteRespuesta;
        }
        return null;
    }
}
