package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoReservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Reservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.LogAuditoriaServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ReservacionServicio;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/reservacion")
public class ReservacionControlador {
    @Autowired
    private ReservacionServicio servicio;
    @Autowired
    private LogAuditoriaServicio auditoria;

    @PostMapping
    public ResponseEntity<Reservacion> agregarReservacion(@RequestBody Reservacion reservacion) {
        Reservacion reservacionAgregada = servicio.create(reservacion);
        if (reservacionAgregada != null) {
            return ResponseEntity.ok(reservacionAgregada);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<Reservacion>> obtenerTodasLasReservaciones() {
        List<Reservacion> busquedaReservacion = servicio.findAll();
        if (!busquedaReservacion.isEmpty()) {
            return ResponseEntity.ok(busquedaReservacion);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservacion> obtenerReservacionPorId(@PathVariable Integer id) {
        Optional<Reservacion> busquedaReservacion = servicio.findById(id);
        if (busquedaReservacion.isPresent()) {
            return ResponseEntity.ok(busquedaReservacion.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/actualizar/{userId}")
    public ResponseEntity<Reservacion> actualizarReservacion(@PathVariable Integer id,
            @RequestBody Reservacion reservacion, @PathVariable Integer userId) {
        Optional<Reservacion> busquedaReservacion = servicio.findById(id);
        if (busquedaReservacion.isPresent()) {
            Reservacion reservacionParaActualizar = busquedaReservacion.get();
            reservacionParaActualizar.setFechaIngreso(reservacion.getFechaIngreso());
            reservacionParaActualizar.setFechaSalida(reservacion.getFechaSalida());
            reservacionParaActualizar.setCantidadPersonas(reservacion.getCantidadPersonas());
            reservacionParaActualizar.setHabitacion(reservacion.getHabitacion());
            reservacionParaActualizar.setTipoReservacion(reservacion.getTipoReservacion());
            Reservacion reservacionActualizada = servicio.update(reservacionParaActualizar);
            auditoria.guardarAccion(userId, "Reservacion ID " + reservacionActualizada.getId() + " actualizada",
                    "reservacion");
            return ResponseEntity.ok(reservacionActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/cancelar/{userId}")
    public ResponseEntity<Reservacion> cancelarReservacion(@PathVariable Integer id,
            @RequestBody EstadoReservacion estadoReservacion, @PathVariable Integer userId) {
        Optional<Reservacion> busquedaReservacion = servicio.findById(id);
        if (busquedaReservacion.isPresent()) {
            Reservacion reservacionParaActualizar = busquedaReservacion.get();
            reservacionParaActualizar.setEstadoReservacion(estadoReservacion);
            Reservacion reservacionActualizada = servicio.update(reservacionParaActualizar);
            auditoria.guardarAccion(userId, "Reservacion ID " + reservacionActualizada.getId() + " cancelada",
                    "reservacion");
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
