package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioConsumidoDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioPorHabitacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioPorHabitacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.InventarioPorHabitacionMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.InventarioPorHabitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.InventarioPorHabitacionTipo;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.InventarioPorHabitacionRepositorio;

@Service
public class InventarioPorHabitacionServicio extends BaseService<InventarioPorHabitacion, Integer>{

    @Autowired
    private InventarioPorHabitacionRepositorio repo;
    @Autowired
    private LogAuditoriaServicio auditoria;
    @Autowired
    private InventarioPorHabitacionMapper mapper;

    protected InventarioPorHabitacionRepositorio getRepository(){
        return repo;
    }
    
    public List<InventarioPorHabitacionDto> crear(List<InventarioPorHabitacionCrearDto> entidad, Integer usuarioId){
        List<InventarioPorHabitacion> inventarioPorHabitacion = entidad.stream().map(inventario -> mapper.ConvertCreateDtoToEntity(inventario)).toList();
        List<InventarioPorHabitacion> inventarioPorHabitacionResultante = inventarioPorHabitacion.stream().map(inventario -> this.create(inventario)).toList();
        if(inventarioPorHabitacionResultante.isEmpty()){
            return null;
        }
        auditoria.guardarAccion(usuarioId, "Crear nuevo inventario por habitacion", "inventarioPorHabitacion");
        List<InventarioPorHabitacionDto> inventarioPorHabitacionDto = inventarioPorHabitacionResultante.stream().map(inventario -> mapper.ConvertEntityToDto(inventario)).toList();
        return inventarioPorHabitacionDto;
    }
    
    public List<InventarioPorHabitacionDto> listarPorReservacion(Integer id){
        List<InventarioPorHabitacion> inventarioPorHabitacion = repo.findByReservacionId(id);
        if(inventarioPorHabitacion.isEmpty()){
            return null;
        }
        List<InventarioPorHabitacionDto> inventarioPorHabitacionDto = inventarioPorHabitacion.stream().map(inventario -> mapper.ConvertEntityToDto(inventario)).toList();
        return inventarioPorHabitacionDto;
    }

    public List<InventarioConsumidoDto> listarProductosConsumidosPorReservacion(Integer reservacionId){
        List<InventarioPorHabitacion> entrada = repo.findByReservacionIdAndTipo(reservacionId, InventarioPorHabitacionTipo.CONFIRMADO_ENTRADA);
        List<InventarioPorHabitacion> salida = repo.findByReservacionIdAndTipo(reservacionId, InventarioPorHabitacionTipo.CONFIRMADO_SALIDA);

        if (entrada.isEmpty() || salida.isEmpty()) {
            throw new IllegalStateException("Inventario de entrada o salida no registrado");
        }

        Map<Integer, Integer> consumo = new HashMap<>();

        // Paso 1: Almacenar cantidad inicial de la entrada
        for (InventarioPorHabitacion itemEntrada : entrada) {
            consumo.put(itemEntrada.getInventario().getId(), itemEntrada.getCantidad());
        }

        // Paso 2: Restar la cantidad de la salida para obtener lo consumido
        for (InventarioPorHabitacion itemSalida : salida) {
            int cantidadEntrada = consumo.getOrDefault(itemSalida.getInventario().getId(), 0);
            int cantidadConsumida = cantidadEntrada - itemSalida.getCantidad(); // ✅ Entrada - Salida = Consumo
            
            if (cantidadConsumida > 0) {
                consumo.put(itemSalida.getInventario().getId(), cantidadConsumida);
            } else {
                consumo.remove(itemSalida.getInventario().getId());
            }
        }

        return consumo.entrySet().stream()
                .map(entry -> new InventarioConsumidoDto(entry.getKey(), entry.getValue()))
                .toList();  
    }

}
