package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.InventarioServicio;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioControlador {
    @Autowired
    private InventarioServicio servicio;

    @PostMapping("/{userId}")
    public ResponseEntity<InventarioDto> agregarInventario(@PathVariable Integer userId
                                                        , @RequestBody InventarioCrearDto inventario){
        InventarioDto inventarioAgregado = servicio.agregar(userId, inventario);
        if(inventarioAgregado != null){
            return ResponseEntity.ok(inventarioAgregado);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping
    public ResponseEntity<List<InventarioDto>> obtenerTodosLosInventarios(){
        List<InventarioDto> inventarios = servicio.obtenerInventarios();
        if(inventarios.size() > 0){
            return ResponseEntity.ok(inventarios);
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<InventarioDto> obtenerInventarioPorId(@PathVariable Integer id) {
        InventarioDto inventario = servicio.obtenerInventario(id);
        if(inventario != null){
            return ResponseEntity.ok(inventario);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}/actualizar/{userId}")
    public ResponseEntity<InventarioDto> actualizarInventario(@PathVariable Integer id
                                                        , @RequestBody InventarioCrearDto inventario
                                                        , @PathVariable Integer userId) {
        InventarioDto inventarioActualizado = servicio.actualizar(id, inventario, userId);
        if(inventarioActualizado != null){
            return ResponseEntity.ok(inventarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}/{cantidad}/{userId}")
    public ResponseEntity<InventarioDto> agregarPedidos(@PathVariable Integer id
                                                    , @PathVariable Integer cantidad
                                                    , @PathVariable Integer userId) {
        InventarioDto inventario = servicio.obtenerInventario(id);
        if(inventario != null){
            return ResponseEntity.ok(inventario);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<String> eliminarInventario(@PathVariable Integer id
                                                    , @PathVariable Integer userId){
        String mensaje = servicio.borrar(id, userId);
        if(mensaje != null){
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.notFound().build();
    }
}
