package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Consumo;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ConsumoServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.LogAuditoriaServicio;

@RestController
@RequestMapping("/api/v1/consumo")
public class ConsumoControlador {

    @Autowired
    private ConsumoServicio servicio;
    private LogAuditoriaServicio auditoria;

    @PostMapping("/{userId}")
    public ResponseEntity<Consumo> agregarConsumo(@RequestBody Consumo consumo
                                                , @PathVariable Integer userId){
        Consumo consumoResultante = servicio.create(consumo);
        if (consumoResultante != null) {
            auditoria.guardarAccion(userId, "Crear nuevo consumo", "consumo");
            return ResponseEntity.ok(consumoResultante);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}/actualizar/{userId}")
    public ResponseEntity<Consumo> actualizarConsumo(@PathVariable Integer id
                                                    , @RequestBody Consumo consumo
                                                    , @PathVariable Integer userId){
        Optional<Consumo> busquedaConsumo = servicio.findById(id);
        if(busquedaConsumo.isPresent()){
            Consumo consumoParaActualizar = busquedaConsumo.get();
            consumoParaActualizar.setInventario(consumo.getInventario());
            consumoParaActualizar.setCantidad(consumo.getCantidad());
            consumoParaActualizar.setCliente(consumo.getCliente());
            consumoParaActualizar.setFecha(consumo.getFecha());
            Consumo consumoActualizado = servicio.update(consumoParaActualizar);
            auditoria.guardarAccion(userId, "Actualizar consumo numero " + consumoActualizado.getId(), "consumo");
            return ResponseEntity.ok(consumoActualizado);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping()
    public ResponseEntity<List<Consumo>> obtenerConsumos(){
        List<Consumo> consumos = servicio.findAll();
        if(consumos != null){
            return ResponseEntity.ok(consumos);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Consumo>> obtenerConsumosPorFechaActualYCliente(@PathVariable Integer id){
        Date fecha = new Date();
        List<Consumo> consumos = servicio.obtenerConsumosPorFechaYCliente(fecha, id);
        if(consumos != null){
            return ResponseEntity.ok(consumos);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}/{fechaInicio}/{fechaFin}")
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
