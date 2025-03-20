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

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoHabitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.EstadoHabitacionServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.LogAuditoriaServicio;
@RestController
@RequestMapping("/api/v1/estadoHabitacion")
public class EstadoHabitacionControlador {
    @Autowired
    private EstadoHabitacionServicio servicio;
    @Autowired
    private LogAuditoriaServicio auditoria;

    @PostMapping("/{userId}")
    public ResponseEntity<EstadoHabitacion> crearEstadoHabitacion(@PathVariable Integer userId
                                                                , @RequestBody EstadoHabitacion estadoHabitacion){
        EstadoHabitacion nuevoEstadoHabitacion = servicio.create(estadoHabitacion);
        if (nuevoEstadoHabitacion != null) {
            auditoria.guardarAccion(userId, "Crear nuevo estado de habitacion", "estadoHabitacion");
            return ResponseEntity.ok(nuevoEstadoHabitacion);
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<String> eliminarEstadoHabitacion(@PathVariable Integer id
                                                                    , @PathVariable Integer userId){
        Optional<EstadoHabitacion> estadoHabitacion = servicio.findById(id);
        if (estadoHabitacion.isPresent()) {
            servicio.delete(id);
            auditoria.guardarAccion(userId, "Eliminar estado de habitacion", "estadoHabitacion");
            return ResponseEntity.ok("Estado de habitacion eliminado");
        }
        return ResponseEntity.notFound().build();
    }

}
