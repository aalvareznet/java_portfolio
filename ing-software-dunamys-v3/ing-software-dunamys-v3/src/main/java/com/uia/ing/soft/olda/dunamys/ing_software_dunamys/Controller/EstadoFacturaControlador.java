package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoFacturaCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoFacturaDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.EstadoFacturaServicio;

@RestController
@RequestMapping("/api/v1/estadoFactura")
public class EstadoFacturaControlador {
    @Autowired
    private EstadoFacturaServicio servicio;

    @PostMapping("/{userId}")
    public ResponseEntity<EstadoFacturaDto> agregarEstadoFactura(@PathVariable Integer userId
                                                            , @RequestBody EstadoFacturaCrearDto estadoFactura){
        EstadoFacturaDto estadoFacturaDto = servicio.agregar(userId, estadoFactura);
        if (estadoFacturaDto != null) {
            return ResponseEntity.ok(estadoFacturaDto);
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<String> eliminarEstadoFactura(@PathVariable Integer userId
                                                      , @PathVariable Integer id){
        String mensaje = servicio.eliminar(userId, id);
        if (mensaje != null) {
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.badRequest().build();
    }

}
