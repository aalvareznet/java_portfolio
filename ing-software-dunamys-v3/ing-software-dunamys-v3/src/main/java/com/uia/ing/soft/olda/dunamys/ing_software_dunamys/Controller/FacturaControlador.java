package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.DetalleFactura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Factura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.DetalleFacturaServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.FacturaServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.LogAuditoriaServicio;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/factura")
public class FacturaControlador {
    @Autowired
    private FacturaServicio servicio;
    @Autowired
    private LogAuditoriaServicio auditoria;
    @Autowired
    private DetalleFacturaServicio detalleFacturaServicio;

    @PostMapping("/{userId}")
    public ResponseEntity<Factura> crearFactura(@PathVariable Integer userId
                                                , @RequestBody Factura factura){
            Factura nuevaFactura = servicio.create(factura); //tener cuidado de crear siempre la factura con status pendiente de pago
            if (nuevaFactura != null) {
                auditoria.guardarAccion(userId, "Crear nueva factura con el ID " + nuevaFactura.getId(), "factura");
                return ResponseEntity.ok(nuevaFactura);
            }
            return ResponseEntity.badRequest().build();
        }
    @Transactional
    @PostMapping("/{id}/detalle/{userId}")
    public ResponseEntity<Factura> agregarDetalleFactura(@PathVariable Integer id
                                                        , @PathVariable Integer userId
                                                        , @RequestBody List<DetalleFactura> detalleFactura){
        Optional<Factura> factura = servicio.findById(id);
        if (factura.isPresent()) {
            for (DetalleFactura detalle : detalleFactura) {
                detalleFacturaServicio.create(DetalleFactura.builder()
                        .factura(factura.get())
                        .cantidad(detalle.getCantidad())
                        .descripcionItem(detalle.getDescripcionItem())
                        .precioUnitario(detalle.getPrecioUnitario())
                        .build());
            }
            auditoria.guardarAccion(userId, "Agregar detalle a la factura numero " + factura.get().getId(), "factura");
            return ResponseEntity.ok(factura.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{facturaId}/{userId}") 
    public ResponseEntity<Factura> pagarFactura(@PathVariable Integer facturaId
                                                , @PathVariable Integer userId
                                                , @RequestBody Factura factura){
        Optional<Factura> busquedaFactura = servicio.findById(facturaId);
        if(busquedaFactura.isPresent()){
            Factura facturaParaActualizar = busquedaFactura.get();
            facturaParaActualizar.setEstadoFactura(factura.getEstadoFactura());
            Factura facturaActualizada = servicio.update(facturaParaActualizar);
            auditoria.guardarAccion(userId, "Factura " + facturaActualizada.getId() + " ha sido pagada", "factura");
            return ResponseEntity.ok(facturaActualizada);
        }
        return ResponseEntity.notFound().build();
    }
}
