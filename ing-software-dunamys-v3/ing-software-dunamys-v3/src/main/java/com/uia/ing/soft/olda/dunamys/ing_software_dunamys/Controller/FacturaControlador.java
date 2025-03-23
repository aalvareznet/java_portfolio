package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.DetalleFacturaCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.FacturaCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.FacturaDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.FacturaServicio;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/factura")
public class FacturaControlador {
    @Autowired
    private FacturaServicio servicio;

    @PostMapping("/{userId}")
    public ResponseEntity<FacturaDto> crearFactura(@PathVariable Integer userId
                                                , @RequestBody FacturaCrearDto factura){
            FacturaDto nuevaFactura = servicio.crear(userId, factura); //tener cuidado de crear siempre la factura con status pendiente de pago
            if (nuevaFactura != null) {
                return ResponseEntity.ok(nuevaFactura);
            }
            return ResponseEntity.badRequest().build();
        }
    @Transactional
    @PostMapping("/{id}/detalle/{userId}")
    public ResponseEntity<FacturaDto> agregarDetalleFactura(@PathVariable Integer id
                                                        , @PathVariable Integer userId
                                                        , @RequestBody List<DetalleFacturaCrearDto> detalleFactura){
        FacturaDto facturaConDetalle = servicio.agregarDetalleFactura(id, userId, detalleFactura);
        if(facturaConDetalle != null){
            return ResponseEntity.ok(facturaConDetalle);
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/{facturaId}/{userId}") 
    public ResponseEntity<FacturaDto> pagarFactura(@PathVariable Integer facturaId
                                                , @PathVariable Integer userId){
        FacturaDto facturaPagada = servicio.pagarFactura(facturaId, userId);
        if(facturaPagada != null){
            return ResponseEntity.ok(facturaPagada);
        }
        return ResponseEntity.badRequest().build();
    }
}
