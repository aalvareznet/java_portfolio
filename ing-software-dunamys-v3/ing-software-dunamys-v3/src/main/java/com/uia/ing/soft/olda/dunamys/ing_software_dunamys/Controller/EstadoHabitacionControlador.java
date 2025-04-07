package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoHabitacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoHabitacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.EstadoHabitacionServicio;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/v1/estadoHabitacion")
public class EstadoHabitacionControlador {
    @Autowired
    private EstadoHabitacionServicio servicio;

    @PostMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<EstadoHabitacionDto> crearEstadoHabitacion(@PathVariable Integer userId
                                                                , @Valid @RequestBody EstadoHabitacionCrearDto estadoHabitacion){
            EstadoHabitacionDto estadoHabitacionCreado = servicio.crear(userId, estadoHabitacion);
            if (estadoHabitacionCreado != null) {
                return ResponseEntity.ok(estadoHabitacionCreado);
            }
            return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/{id}/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<String> eliminarEstadoHabitacion(@PathVariable Integer id
                                                                    , @PathVariable Integer userId){
        String estadoHabitacionEliminado = servicio.borrar(id, userId);
        if (estadoHabitacionEliminado != null) {
            return ResponseEntity.ok(estadoHabitacionEliminado);
        }
        return ResponseEntity.badRequest().build();
    }

}
