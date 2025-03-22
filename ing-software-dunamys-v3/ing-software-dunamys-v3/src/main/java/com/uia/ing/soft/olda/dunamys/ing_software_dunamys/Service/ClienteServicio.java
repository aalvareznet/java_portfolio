package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteCrearDto;
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

    protected ClienteRepositorio getRepository() {
        return repo;
    }

    @Transactional
    public Cliente agregarClientePersonaUsuario(ClienteCrearDto cliente) {
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
        return clienteCreado;
    }

    public Cliente actualizar(Integer id, Persona persona, Integer usuarioId){
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
                return this.findById(id).get();
            }
        }
        return null;
    }
}
