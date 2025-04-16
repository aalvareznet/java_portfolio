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

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.HabitacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.HabitacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.HabitacionServicio;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/habitacion")
public class HabitacionControlador {
    @Autowired
    private HabitacionServicio servicio;

    @PostMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<HabitacionDto> crearHabitacion(@Valid @RequestBody HabitacionCrearDto habitacion, @PathVariable Integer userId) {
        if (habitacion == null || userId == null) {
            return ResponseEntity.badRequest().build();
        }
        HabitacionDto habitacionCreada = servicio.crear(habitacion, userId);
        if (habitacionCreada != null) {
            return ResponseEntity.ok(habitacionCreada);
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/{id}/actualizar/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<HabitacionDto> actualizarHabitacion(@PathVariable Integer id, @Valid @RequestBody HabitacionCrearDto habitacion, @PathVariable Integer userId) {
        if (id == null || habitacion == null || userId == null) {
            return ResponseEntity.badRequest().build();
        }
        HabitacionDto habitacionActualizada = servicio.actualizar(id, habitacion, userId);
        if (habitacionActualizada != null) {
            return ResponseEntity.ok(habitacionActualizada);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDto> obtenerHabitacion(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        HabitacionDto habitacion = servicio.obtenerHabitacionPorId(id);
        if (habitacion != null) {
            return ResponseEntity.ok(habitacion);
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/obtener")
    public ResponseEntity<List<HabitacionDto>> obtenerHabitaciones() {
        List<HabitacionDto> habitaciones = servicio.obtenerHabitaciones();
        if (habitaciones.size() > 0) {
            return ResponseEntity.ok(habitaciones);
        }
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable Integer id, @PathVariable Integer userId) {
        if (id == null || userId == null) {
            return ResponseEntity.badRequest().build();
        }
        String mensaje = servicio.eliminar(id, userId);
        if (mensaje != null) {
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.badRequest().build();
    }
}
