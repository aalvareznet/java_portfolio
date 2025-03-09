package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ClienteServicio extends BaseService<Cliente, Long>{

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
    public Cliente agregarClientePersonaUsuario(Persona persona, RegisterRequest registerRequest){
        AuthResponse userAgregado = authService.register(registerRequest);
        if(userAgregado == null){
            throw new IllegalStateException("Error al crear el usuario");
        }
        Optional<User> busquedaUser = userRepo.findByUsername(registerRequest.getUsername());
        if(!busquedaUser.isPresent()){
            throw new IllegalStateException("Error al buscar usuario");
        }
        Persona personaAgregada = personaServicio.create(persona);
        if(personaAgregada == null){
            throw new IllegalStateException("Error crear la persona usuario");
        }
        Cliente cliente = Cliente.builder()
                            .cantidadVisitas(0)
                            .persona(personaAgregada)
                            .usuario(busquedaUser.get())
                            .build(); 
        Cliente clienteCreado = this.create(cliente);
        return clienteCreado;
    }
}
