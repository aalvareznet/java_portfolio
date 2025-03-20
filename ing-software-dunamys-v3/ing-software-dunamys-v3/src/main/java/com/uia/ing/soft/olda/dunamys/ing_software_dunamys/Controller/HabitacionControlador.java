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

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.HabitacionServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.LogAuditoriaServicio;

@RestController
@RequestMapping("/api/v1/habitacion")
public class HabitacionControlador {
    @Autowired
    private HabitacionServicio servicio;
    @Autowired
    private LogAuditoriaServicio auditoria;

    @PostMapping("/{userId}")
    public ResponseEntity<Habitacion> crearHabitacion(@RequestBody Habitacion habitacion
                                                    , @PathVariable Integer userId) {
        Habitacion nuevaHabitacion = servicio.create(habitacion);
        if (nuevaHabitacion != null) {
            auditoria.guardarAccion(userId, "Crear nueva habitacion con el ID " + nuevaHabitacion.getId(), "habitacion");
            return ResponseEntity.ok(nuevaHabitacion);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}/actualizar/{userId}")
    public ResponseEntity<Habitacion> actualizarHabitacion(@PathVariable Integer id
                                                        , @RequestBody Habitacion habitacion
                                                        , @PathVariable Integer userId) {
        Optional<Habitacion> busquedaHabitacion = servicio.findById(id);
        if(busquedaHabitacion.isPresent()){
            Habitacion habitacionParaActualizar = busquedaHabitacion.get();
            habitacionParaActualizar.setCapacidad(habitacion.getCapacidad());
            habitacionParaActualizar.setEstadoHabitacion(habitacion.getEstadoHabitacion());
            habitacionParaActualizar.setNumeroHabitacion(habitacion.getNumeroHabitacion());
            habitacionParaActualizar.setTipoHabitacion(habitacion.getTipoHabitacion());
            Habitacion habitacionActualizada = servicio.update(habitacionParaActualizar);
            auditoria.guardarAccion(userId, "Habitacion numero " + habitacionActualizada.getId() + " actualizada", "habitacion");
            return ResponseEntity.ok(habitacionActualizada);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> obtenerHabitacion(@PathVariable Integer id) {
        Optional<Habitacion> habitacion = servicio.findById(id);
        if (habitacion != null) {
            return ResponseEntity.ok(habitacion.get());
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping
    public ResponseEntity<List<Habitacion>> obtenerHabitaciones() {
        List<Habitacion> habitaciones = servicio.findAll();
        if (habitaciones != null) {
            return ResponseEntity.ok(habitaciones);
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<Habitacion> eliminarHabitacion(@PathVariable Integer id
                                                        , @PathVariable Integer userId) {
        Optional<Habitacion> habitacion = servicio.findById(id);
        if (habitacion.isPresent()) {
            servicio.delete(id);
            auditoria.guardarAccion(userId, "Eliminar habitacion con el ID " + habitacion.get().getId(), "habitacion");
            return ResponseEntity.ok(habitacion.get());
        }
        return ResponseEntity.badRequest().build();
    }

}
