package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ProveedorCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ProveedorDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ProveedorServicio;

@RestController
@RequestMapping("/api/v1/proveedor")
public class ProveedorControlador {
    @Autowired
    private ProveedorServicio servicio;
    
    @PostMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ProveedorDto> agregarProveedor(@PathVariable Integer userId
                                                    , @RequestBody ProveedorCrearDto proveedor){
        ProveedorDto proveedorCreado = servicio.agregar(userId, proveedor);
        if(proveedorCreado != null){
            return ResponseEntity.ok(proveedorCreado);
        }
        return ResponseEntity.badRequest().build();

    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<ProveedorDto>> obtenerTodosLosProveedores(){
        List<ProveedorDto> proveedores = servicio.obtenerProveedores();
        if(proveedores.size() > 0){
            return ResponseEntity.ok(proveedores);
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ProveedorDto> obtenerProveedorPorId(@PathVariable Integer id){
        ProveedorDto proveedor = servicio.obtenerProveedor(id);
        if(proveedor != null){
            return ResponseEntity.ok(proveedor);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}/actualizar/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ProveedorDto> actualizarProveedor(@PathVariable Integer id
                                                        , @RequestBody ProveedorCrearDto proveedor
                                                        , @PathVariable Integer userId){
        ProveedorDto proveedorActualizado = servicio.actualizar(id, proveedor, userId);
        if(proveedorActualizado != null){
            return ResponseEntity.ok(proveedorActualizado);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<String> borrarProveedor(@PathVariable Integer id
                                                , @PathVariable Integer userId){
        String mensaje = servicio.borrar(id, userId);
        if(mensaje != null){
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.notFound().build();
    }
}
