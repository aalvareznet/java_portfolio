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
    
    public InventarioPorHabitacionDto crear(List<InventarioPorHabitacionCrearDto> entidad, Integer usuarioId){
        for (InventarioPorHabitacionCrearDto inventarioPorHabitacionCrearDto : entidad) {
            InventarioPorHabitacion inventarioAgregado = this.create(mapper.ConvertCreateDtoToEntity(inventarioPorHabitacionCrearDto));
            if(inventarioAgregado != null){
                auditoria.guardarAccion(usuarioId, "Crear nueva linea", "inventario_por_habitacion");
                InventarioPorHabitacionDto inventarioDto = mapper.ConvertEntityToDto(inventarioAgregado);
                return inventarioDto;
            }
        }
        return null;
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

    // Validar que existen ambos registros
    if (entrada.isEmpty() || salida.isEmpty()) {
        throw new IllegalStateException("Inventario de entrada o salida no registrado");
    }

    // Mapa para comparar cantidades (ID del Inventario -> Cantidad)
    Map<Integer, Integer> consumo = new HashMap<>();

    // Procesar entrada: sumar cantidades iniciales
    for (InventarioPorHabitacion item : entrada) {
        consumo.put(item.getInventario().getId(), item.getCantidad());
    }

    // Procesar salida: restar cantidades finales
    for (InventarioPorHabitacion item : salida) {
        int cantidadEntrada = consumo.getOrDefault(item.getInventario().getId(), 0);
        int diferencia = cantidadEntrada - item.getCantidad();
        
        if (diferencia > 0) {
            consumo.put(item.getInventario().getId(), diferencia);
        } else {
            consumo.remove(item.getInventario().getId()); // No hubo consumo
        }
    }

    // Convertir a DTOs
    return consumo.entrySet().stream()
            .map(entry -> new InventarioConsumidoDto(
                    entry.getKey(),
                    entry.getValue()
            ))
            .toList();
        
    }

}
