package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Persona;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ClienteServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.LogAuditoriaServicio;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteControlador {
    @Autowired
    private ClienteServicio servicio;
    @Autowired
    private LogAuditoriaServicio logServicio;
    //Las cuentas solo pueden ser creadas por clientes

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody ClienteCrearDto cliente) {
        Cliente clienteCreado = servicio.agregarClientePersonaUsuario(cliente);
        if(clienteCreado != null){
            return ResponseEntity.ok(clienteCreado);
        }
        return ResponseEntity.badRequest().build();
    }
    //Las cuentas pueden ser actualizadas por los clientes y por los administradores. La unica informacion que se va a poder actualizar es la informacion de la persona
    @PutMapping("/{id}/actualizar/{userId}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer id
                                                    , @RequestBody Persona persona
                                                    , @PathVariable Integer userId
                                                     ){
        Cliente cliente = servicio.actualizar(id, persona, userId);
        if(cliente != null){
            return ResponseEntity.ok(cliente);
        }   
        return ResponseEntity.notFound().build();
    }
    
    //Buscar a un cliente por ID es una operación que puede ser realizada por los administradores
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Integer id){
        Optional<Cliente> cliente = servicio.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }
    //Borrar a un cliente es una operación que puede ser realizada por los administradores y por los mismos clientes
    @DeleteMapping("/{userId}/{clientId}")
    public ResponseEntity<String> borrarCliente(@PathVariable Integer userId
                                                , @PathVariable Integer clientId){
        Optional<Cliente> cliente = servicio.findById(clientId);
        if(cliente.isPresent()){
            logServicio.guardarAccion(userId, "Cliente borrado", "cliente");
            servicio.delete(clientId);
            return ResponseEntity.ok("Cliente eliminado");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes(){
        List<Cliente> clientes = servicio.findAll();
        if(clientes.size() > 0){
            return ResponseEntity.ok(clientes);
        }
        return ResponseEntity.notFound().build();
    }
    //Endpoints adicionales x
}
