package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioConsumidoDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioPorHabitacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioPorHabitacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.InventarioPorHabitacionServicio;

@RestController
@RequestMapping("/api/v1/inventarioPorHabitacion")
public class InventarioPorHabitacionControlador {

    private final InventarioPorHabitacionServicio servicio;

    public InventarioPorHabitacionControlador(InventarioPorHabitacionServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/crear/entrada/{userId}")
    public ResponseEntity<List<InventarioPorHabitacionDto>> crearEntrada(@RequestBody List<InventarioPorHabitacionCrearDto> entidad, @PathVariable Integer userId){
        List<InventarioPorHabitacionDto> inventarioPorHabitacionDto = servicio.crear(entidad, userId);
        if(inventarioPorHabitacionDto != null){
            return ResponseEntity.ok(inventarioPorHabitacionDto);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/crear/salida/{userId}")
    public ResponseEntity<List<InventarioPorHabitacionDto>> crearSalida(@RequestBody List<InventarioPorHabitacionCrearDto> entidad, @PathVariable Integer userId){
        List<InventarioPorHabitacionDto> inventarioPorHabitacionDto = servicio.crear(entidad, userId);
        if(inventarioPorHabitacionDto != null){
            return ResponseEntity.ok(inventarioPorHabitacionDto);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/listarPorReservacion/{id}")
    public ResponseEntity<List<InventarioPorHabitacionDto>> listarPorReservacion(@PathVariable Integer id){
        List<InventarioPorHabitacionDto> inventarioPorHabitacionDto = servicio.listarPorReservacion(id);
        if(inventarioPorHabitacionDto != null){
            return ResponseEntity.ok(inventarioPorHabitacionDto);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/consumidos/{id}")
    public ResponseEntity<List<InventarioConsumidoDto>> listarProductosConsumidosPorReservacion(@PathVariable Integer id){
        List<InventarioConsumidoDto> inventarioConsumidoDto = servicio.listarProductosConsumidosPorReservacion(id);
        if(inventarioConsumidoDto != null){
            return ResponseEntity.ok(inventarioConsumidoDto);
        }
        return ResponseEntity.badRequest().build();
    }
}

