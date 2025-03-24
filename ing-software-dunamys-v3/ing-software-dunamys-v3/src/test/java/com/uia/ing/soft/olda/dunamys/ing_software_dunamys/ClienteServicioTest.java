package com.uia.ing.soft.olda.dunamys.ing_software_dunamys;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Persona;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ClienteRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.User;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.UserRepository;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ClienteServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.PersonaServicio;

@ExtendWith(MockitoExtension.class)
class ClienteServicioTest {

    @Mock
    private ClienteRepositorio clienteRepo;
    
    @Mock
    private PersonaServicio personaServicio;
    
    @Mock
    private UserRepository userRepo;
    
    @InjectMocks
    private ClienteServicio clienteServicio;

    @Test
    void testAgregarClientePersonaUsuario_Exitoso() {
        // Configurar mocks
        ClienteCrearDto dto = new ClienteCrearDto(/* datos de prueba */);
        User userMock = new User(/* usuario simulado */);
        Persona personaMock = new Persona(/* persona simulada */);
        
        when(userRepo.findByUsername(anyString())).thenReturn(Optional.of(userMock));
        when(personaServicio.create(any(Persona.class))).thenReturn(personaMock);
        
        // Ejecutar
        ClienteDto resultado = clienteServicio.agregarClientePersonaUsuario(dto);
        
        // Verificar
        assertNotNull(resultado);
        verify(clienteRepo, times(1)).save(any(Cliente.class));
    }
}
