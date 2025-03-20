package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Inventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.InventarioServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.LogAuditoriaServicio;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioControlador {
    @Autowired
    private InventarioServicio servicio;
    @Autowired
    private LogAuditoriaServicio auditoria;

    @PostMapping("/{userId}")
    public ResponseEntity<Inventario> agregarInventario(@PathVariable Integer userId
                                                        , @RequestBody Inventario inventario){
        Inventario inventarioAgragado = servicio.create(inventario);
        if(inventarioAgragado != null){
            return ResponseEntity.ok(inventarioAgragado);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping
    public ResponseEntity<List<Inventario>> obtenerTodosLosInventarios(){
        List<Inventario> busquedaInventario = servicio.findAll();
        if(!busquedaInventario.isEmpty()){
            return ResponseEntity.ok(busquedaInventario);
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerInventarioPorId(@PathVariable Integer id) {
        Optional<Inventario> busquedaInventario = servicio.findById(id);
        if(busquedaInventario.isPresent()){
            return ResponseEntity.ok(busquedaInventario.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}/actualizar/{userId}")
    public ResponseEntity<Inventario> actualizarInventario(@PathVariable Integer id
                                                        , @RequestBody Inventario inventario
                                                        , @PathVariable Integer userId) {
        Optional<Inventario> busquedaInventario = servicio.findById(id);
        if(busquedaInventario.isPresent()){
            Inventario inventarioParaActualizar = busquedaInventario.get();
            inventarioParaActualizar.setNombreItem(inventario.getNombreItem());
            inventarioParaActualizar.setCategoriaInventario(inventario.getCategoriaInventario());
            inventarioParaActualizar.setPrecio(inventario.getPrecio());
            inventarioParaActualizar.setProveedor(inventario.getProveedor());
            Inventario inventarioActualizado = servicio.update(inventarioParaActualizar);
            auditoria.guardarAccion(userId, "Inventario con el id " + inventarioActualizado.getId() + " ha sido actualizado", "inventario");
            return ResponseEntity.ok(inventarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}/{cantidad}/{userId}")
    public ResponseEntity<Inventario> agregarPedidos(@PathVariable Integer id
                                                    , @PathVariable Integer cantidad
                                                    , @PathVariable Integer userId) {
        Optional<Inventario> busquedaInventario = servicio.findById(id);
        if(busquedaInventario.isPresent()){
            Inventario inventarioParaActualizar = busquedaInventario.get();
            inventarioParaActualizar.setStock(inventarioParaActualizar.getStock()+cantidad);
            Inventario inventarioActualizado = servicio.update(inventarioParaActualizar);
            return ResponseEntity.ok(inventarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<String> eliminarInventario(@PathVariable Integer id
                                                    , @PathVariable Integer userId){
        Optional<Inventario> busquedaInventario = servicio.findById(id);
        if(busquedaInventario.isPresent()){
            servicio.delete(id);
            auditoria.guardarAccion(userId, "Inventario ID " + busquedaInventario.get().getId() + " ha sido eliminado", "inventario");
            return ResponseEntity.ok("Inventario eliminado");
        }
        return ResponseEntity.notFound().build();
    }
}
