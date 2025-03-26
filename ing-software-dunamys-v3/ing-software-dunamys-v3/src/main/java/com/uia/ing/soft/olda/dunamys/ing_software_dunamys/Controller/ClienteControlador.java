package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteInfoPersonaDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Persona;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ClienteServicio;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteControlador {
    @Autowired
    private ClienteServicio servicio;
    //Las cuentas solo pueden ser creadas por clientes

    @PostMapping("/crear")
    public ResponseEntity<ClienteDto> crearCliente(@RequestBody ClienteCrearDto cliente) {
        ClienteDto clienteCreado = servicio.agregarClientePersonaUsuario(cliente);
        if(clienteCreado != null){
            return ResponseEntity.ok(clienteCreado);
        }
        return ResponseEntity.badRequest().build();
    }
    //Las cuentas pueden ser actualizadas por los clientes y por los administradores. La unica informacion que se va a poder actualizar es la informacion de la persona
    @PutMapping("/{id}/actualizar/{userId}")
    public ResponseEntity<ClienteDto> actualizarCliente(@PathVariable Integer id
                                                    , @RequestBody Persona persona
                                                    , @PathVariable Integer userId
                                                     ){
        ClienteDto cliente = servicio.actualizar(id, persona, userId);
        if(cliente != null){
            return ResponseEntity.ok(cliente);
        }   
        return ResponseEntity.notFound().build();
    }
    
    //Buscar a un cliente por ID es una operación que puede ser realizada por los administradores
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerClientePorId(@PathVariable Integer id){
        ClienteDto cliente = servicio.obtenerClientePorId(id);
        if(cliente != null){
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.notFound().build();
    }
    //Borrar a un cliente es una operación que puede ser realizada por los administradores y por los mismos clientes
    @DeleteMapping("/{userId}/{clientId}")
    public ResponseEntity<String> borrarCliente(@PathVariable Integer userId
                                                , @PathVariable Integer clientId){
        String mensaje = servicio.borrar(userId, clientId);
        if(mensaje != null){
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> obtenerTodosLosClientes(){
        List<ClienteDto> clientes = servicio.obtenerTodos();
        if(clientes.size() > 0){
            return ResponseEntity.ok(clientes);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<ClienteInfoPersonaDto> obtenerClientePorIdUsuario(@PathVariable Integer id){
        ClienteInfoPersonaDto cliente = servicio.obtenerClientePorIdUsuario(id);
        if(cliente != null){
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.notFound().build();
    }
}
