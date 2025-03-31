package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.Role;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ClienteServicio;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final ClienteServicio clienteServicio;

    public AdminController(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
    }
    
    @PostMapping("/crear/{rol}")
    public ResponseEntity<ClienteDto> crearCliente(@RequestBody ClienteCrearDto cliente, @PathVariable String rol) {
        Role rolEntrante = Role.valueOf(rol);
        ClienteDto clienteCreado = clienteServicio.agregarUsuarioOtrosRoles(cliente, rolEntrante);
        if(clienteCreado != null){
            return ResponseEntity.ok(clienteCreado);
        }
        return ResponseEntity.badRequest().build();
    }
}
