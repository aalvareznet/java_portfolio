package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoReservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.EstadoReservacionServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.LogAuditoriaServicio;

@RestController
@RequestMapping("/api/v1/estadoReservacion")
public class EstadoReservacionControlador {
    @Autowired
    private EstadoReservacionServicio servicio;
    @Autowired
    private LogAuditoriaServicio auditoria;
    
    @PostMapping("/{userId}")
    public ResponseEntity<EstadoReservacion> crearEstadoReservacion(@PathVariable Integer userId
                                                                    , @RequestBody EstadoReservacion estadoReservacion){
        EstadoReservacion nuevoEstadoReservacion = servicio.create(estadoReservacion);
        if (nuevoEstadoReservacion != null) {
            auditoria.guardarAccion(userId, "Crear nuevo estado de reservacion", "estadoReservacion");
            return ResponseEntity.ok(nuevoEstadoReservacion);
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<String> eliminarEstadoReservacion(@PathVariable Long id
                                                            , @PathVariable Integer userId){
        Optional<EstadoReservacion> estadoReservacion = servicio.findById(id);
        if (estadoReservacion.isPresent()) {
            servicio.delete(id);
            auditoria.guardarAccion(userId, "Eliminar estado de reservacion", "estadoReservacion");
            return ResponseEntity.ok("Estado de reservacion eliminado");
        }
        return ResponseEntity.notFound().build();
    }
}
