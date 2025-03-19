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

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoFactura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.EstadoFacturaServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.LogAuditoriaServicio;

@RestController
@RequestMapping("/api/v1/estadoFactura")
public class EstadoFacturaControlador {
    @Autowired
    private EstadoFacturaServicio servicio;
    @Autowired
    private LogAuditoriaServicio auditoria;

    @PostMapping("/{userId}")
    public ResponseEntity<EstadoFactura> agregarEstadoFactura(@PathVariable Integer userId
                                                            , @RequestBody EstadoFactura estadoFactura){
        EstadoFactura estadoFacturaResultante = servicio.create(estadoFactura);
        if (estadoFacturaResultante != null) {
            auditoria.guardarAccion(userId, "Crear nuevo estado de factura", "estadoFactura");
            return ResponseEntity.ok(estadoFacturaResultante);
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<String> eliminarEstadoFactura(@PathVariable Integer userId
                                                      , @PathVariable Long id){
        Optional<EstadoFactura> estadoFactura = servicio.findById(id);
        if (estadoFactura.isPresent()) {
            servicio.delete(id);
            auditoria.guardarAccion(userId, "Eliminar estado de factura", "estadoFactura");
            return ResponseEntity.ok("Estado de factura eliminado");
        }
        return ResponseEntity.notFound().build();                                                
    }

}
