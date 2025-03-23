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

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.HabitacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.HabitacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.HabitacionServicio;

@RestController
@RequestMapping("/api/v1/habitacion")
public class HabitacionControlador {
    @Autowired
    private HabitacionServicio servicio;

    @PostMapping("/{userId}")
    public ResponseEntity<HabitacionDto> crearHabitacion(@RequestBody HabitacionCrearDto habitacion
                                                    , @PathVariable Integer userId) {
        HabitacionDto habitacionCreada = servicio.crear(habitacion, userId);
        if (habitacionCreada != null) {
            return ResponseEntity.ok(habitacionCreada);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}/actualizar/{userId}")
    public ResponseEntity<HabitacionDto> actualizarHabitacion(@PathVariable Integer id
                                                        , @RequestBody HabitacionCrearDto habitacion
                                                        , @PathVariable Integer userId) {
        HabitacionDto habitacionActualizada = servicio.actualizar(id, habitacion, userId);
        if (habitacionActualizada != null) {
            return ResponseEntity.ok(habitacionActualizada);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDto> obtenerHabitacion(@PathVariable Integer id) {
        HabitacionDto habitacion = servicio.obtenerHabitacionPorId(id);
        if(habitacion != null){
            return ResponseEntity.ok(habitacion);
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<HabitacionDto>> obtenerHabitaciones() {
        List<HabitacionDto> habitaciones = servicio.obtenerHabitaciones();
        if(habitaciones.size() > 0){
            return ResponseEntity.ok(habitaciones);
        }
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable Integer id
                                                        , @PathVariable Integer userId) {
        String mensaje = servicio.eliminar(id, userId);
        if (mensaje != null) {
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.badRequest().build();
    }

}
