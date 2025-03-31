package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ConsumoCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ConsumoDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Consumo;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ConsumoServicio;

@RestController
@RequestMapping("/api/v1/consumo")
public class ConsumoControlador {

    @Autowired
    private ConsumoServicio servicio;

    @PostMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'PAYMENTS')")
    public ResponseEntity<ConsumoDto> agregarConsumo(@RequestBody ConsumoCrearDto consumo
                                                , @PathVariable Integer userId){
        ConsumoDto consumoCreado = servicio.agregar(consumo, userId);
        if(consumoCreado != null){
            return ResponseEntity.ok(consumoCreado);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}/actualizar/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ConsumoDto> actualizarConsumo(@PathVariable Integer id
                                                    , @RequestBody ConsumoCrearDto consumo
                                                    , @PathVariable Integer userId){
        ConsumoDto consumoActualizado = servicio.actualizar(id, consumo, userId);
        if(consumoActualizado != null){
            return ResponseEntity.ok(consumoActualizado);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'PAYMENTS')")
    public ResponseEntity<List<ConsumoDto>> obtenerConsumos(){
        List<ConsumoDto> consumos = servicio.obtenerTodos();
        if(consumos.size() > 0){
            return ResponseEntity.ok(consumos);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'PAYMENTS')")
    public ResponseEntity<List<Consumo>> obtenerConsumosPorFechaActualYCliente(@PathVariable Integer id){
        Date fecha = new Date();
        List<Consumo> consumos = servicio.obtenerConsumosPorFechaYCliente(fecha, id);
        if(consumos != null){
            return ResponseEntity.ok(consumos);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}/{fechaInicio}/{fechaFin}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'PAYMENTS')")
    public ResponseEntity<List<Consumo>> obtenerConsumosPorFechasYCliente(@PathVariable Integer id
                                                                        , @PathVariable Date fechaInicio
                                                                        , @PathVariable Date fechaFin){
        List<Consumo> consumos = servicio.obtenerConsumosEntreFechasYCliente(fechaInicio, fechaFin, id);
        if(consumos != null){
            return ResponseEntity.ok(consumos);
        }
        return ResponseEntity.notFound().build();
    }
}
