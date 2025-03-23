package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoReservacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoReservacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.EstadoReservacionServicio;

@RestController
@RequestMapping("/api/v1/estadoReservacion")
public class EstadoReservacionControlador {
    @Autowired
    private EstadoReservacionServicio servicio;
    
    @PostMapping("/{userId}")
    public ResponseEntity<EstadoReservacionDto> crearEstadoReservacion(@PathVariable Integer userId
                                                                    , @RequestBody EstadoReservacionCrearDto estadoReservacion){
        EstadoReservacionDto estadoReservacionCreado = servicio.crear(userId, estadoReservacion);
        if (estadoReservacionCreado != null) {
            return ResponseEntity.ok(estadoReservacionCreado);
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<String> eliminarEstadoReservacion(@PathVariable Integer id
                                                            , @PathVariable Integer userId){
        String mensaje = servicio.borrar(id, userId);
        if (mensaje != null) {
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.badRequest().build();
    }
}
