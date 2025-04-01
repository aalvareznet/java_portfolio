package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ReservacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ReservacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ReservacionServicio;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/reservacion")
public class ReservacionControlador {
    @Autowired
    private ReservacionServicio servicio;

    @PostMapping
    public ResponseEntity<ReservacionDto> agregarReservacion(@RequestBody ReservacionCrearDto reservacion) {
        ReservacionDto reservacionAgregada = servicio.agregar(reservacion);
        if (reservacionAgregada != null) {
            return ResponseEntity.ok(reservacionAgregada);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<ReservacionDto>> obtenerTodasLasReservaciones() {
        List<ReservacionDto> reservaciones = servicio.obtenerReservaciones();
        if(reservaciones.size() > 0){
            return ResponseEntity.ok(reservaciones);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'PAYMENTS')")
    public ResponseEntity<ReservacionDto> obtenerReservacionPorId(@PathVariable Integer id) {
        ReservacionDto reservacion = servicio.obtenerReservacion(id);
        if(reservacion != null){
            return ResponseEntity.ok(reservacion);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/actualizar/{userId}")
    public ResponseEntity<ReservacionDto> actualizarReservacion(@PathVariable Integer id,
            @RequestBody ReservacionCrearDto reservacion, @PathVariable Integer userId) {
        ReservacionDto reservacionActualizada = servicio.actualizar(userId, reservacion, id);
        if(reservacionActualizada != null){
            return ResponseEntity.ok(reservacionActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/disponibilidad/{habitacionId}")
    public ResponseEntity<Boolean> verificarDisponibilidadHabitacion(
            @PathVariable Integer habitacionId,
            @RequestParam Date fechaIngreso,
            @RequestParam Date fechaSalida) {

        boolean disponible = servicio.verificarDisponibilidadHabitacion(habitacionId, fechaIngreso, fechaSalida);
        return ResponseEntity.ok(disponible);
    }
    
    // Obtiene una lista de habitaciones disponibles en un rango de fechas
    @GetMapping("/habitaciones-disponibles")
    public ResponseEntity<List<Habitacion>> obtenerHabitacionesDisponibles(
            @RequestParam Date fechaIngreso,
            @RequestParam Date fechaSalida) {

        List<Habitacion> habitacionesDisponibles = servicio.obtenerHabitacionesDisponibles(fechaIngreso, fechaSalida);
        return ResponseEntity.ok(habitacionesDisponibles);
    }

}
