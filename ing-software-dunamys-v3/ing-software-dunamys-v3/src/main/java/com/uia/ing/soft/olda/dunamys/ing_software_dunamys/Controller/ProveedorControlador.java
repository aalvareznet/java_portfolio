package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Proveedor;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.LogAuditoriaServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ProveedorServicio;

@RestController
@RequestMapping("/api/v1/proveedor")
public class ProveedorControlador {
    @Autowired
    private ProveedorServicio servicio;
    @Autowired
    private LogAuditoriaServicio auditoria;
    
    @PostMapping("/{userId}")
    public ResponseEntity<Proveedor> agregarProveedor(@PathVariable Integer userId
                                                    , @RequestBody Proveedor proveedor){
        Proveedor proveedorCreado = servicio.create(proveedor);
        if (proveedorCreado != null) {
            auditoria.guardarAccion(userId, "Proveedor creado", "proveedor");
            return ResponseEntity.ok(proveedorCreado);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping
    public ResponseEntity<List<Proveedor>> obtenerTodosLosProveedores(){
        List<Proveedor> busquedaProveedor = servicio.findAll();
        if(!busquedaProveedor.isEmpty()){
            return ResponseEntity.ok(busquedaProveedor);
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable Integer id){
        Optional<Proveedor> busquedaProveedor = servicio.findById(id);
        if(busquedaProveedor.isPresent()){
            return ResponseEntity.ok(busquedaProveedor.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}/actualizar/{userId}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Integer id
                                                        , @RequestBody Proveedor proveedor
                                                        , @PathVariable Integer userId){
        Optional<Proveedor> busquedaProveedor = servicio.findById(id);
        if(busquedaProveedor.isPresent()){
            Proveedor proveedorParaActualizar = busquedaProveedor.get();
            proveedorParaActualizar.setNombre(proveedor.getNombre());
            proveedorParaActualizar.setTelefono(proveedor.getTelefono());
            proveedorParaActualizar.setCorreoReferencia(proveedor.getCorreoReferencia());
            Proveedor proveedorActualizado = servicio.update(proveedorParaActualizar);
            auditoria.guardarAccion(userId, "Proveedor con el ID " + proveedorActualizado.getId() + " actualizado", "proveedor");
            return ResponseEntity.ok(proveedorActualizado);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<String> borrarProveedor(@PathVariable Integer id
                                                , @PathVariable Integer userId){
        Optional<Proveedor> busquedaProveedor = servicio.findById(id);
        if(busquedaProveedor.isPresent()){
            servicio.delete(id);
            auditoria.guardarAccion(userId, "Proveedor ID " + busquedaProveedor.get().getId() + " eliminado", "proveedor");
            return ResponseEntity.ok("Proveedor ID " + busquedaProveedor.get().getId() + " eliminado");
        }
        return ResponseEntity.notFound().build();
    }
}
