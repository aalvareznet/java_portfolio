package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.HabitacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.HabitacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.HabitacionMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.HabitacionRepositorio;

@Service
public class HabitacionServicio extends BaseService<Habitacion, Integer>{

    @Autowired
    private HabitacionRepositorio repo;

    @Autowired
    private LogAuditoriaServicio auditoria;

    @Autowired
    private HabitacionMapper mapper;

    protected HabitacionRepositorio getRepository() {
        return repo;
    }

    public HabitacionDto crear(HabitacionCrearDto habitacionDto, Integer usuarioId){
        Habitacion habitacion = mapper.ConvertDTOToEntity(habitacionDto);
        Habitacion nueHabitacion = this.create(habitacion);
        if(nueHabitacion != null){
            auditoria.guardarAccion(usuarioId, "Crear nueva habitacion con el ID " + nueHabitacion.getId(), "habitacion");
            return mapper.ConvertEntityToDTO(nueHabitacion);
        }
        return null;
    }

    public HabitacionDto actualizar(Integer id, HabitacionCrearDto habitacionDto, Integer userId){
        Optional<Habitacion> busquedaHabitacion = this.findById(id);
        if(busquedaHabitacion.isPresent()){
            Habitacion habitacionConvertida = mapper.ConvertDTOToEntity(habitacionDto);
            Habitacion habitacionParaActualizar = busquedaHabitacion.get();
            habitacionParaActualizar.setCapacidad(habitacionConvertida.getCapacidad());
            habitacionParaActualizar.setEstadoHabitacion(habitacionConvertida.getEstadoHabitacion());
            habitacionParaActualizar.setNumeroHabitacion(habitacionConvertida.getNumeroHabitacion());
            habitacionParaActualizar.setTipoHabitacion(habitacionConvertida.getTipoHabitacion());
            Habitacion habitacionActualizada = this.update(habitacionParaActualizar);
            auditoria.guardarAccion(userId, "Habitacion numero " + habitacionActualizada.getId() + " actualizada", "habitacion");
            return mapper.ConvertEntityToDTO(habitacionActualizada);            
        }
        return null;
    }
    public List<HabitacionDto> obtenerHabitaciones(){
        List<Habitacion> habitaciones = this.findAll();
        List<HabitacionDto> habitacionesDto = habitaciones.stream()
                                                        .map(habitacion -> mapper.ConvertEntityToDTO(habitacion))
                                                        .toList();  
        return habitacionesDto;
    }

    public String eliminar(Integer id, Integer usuarioId){
        Optional<Habitacion> habitacion = this.findById(id);
        if(habitacion.isPresent()){
            this.delete(id);
            auditoria.guardarAccion(usuarioId, "Eliminar habitacion con el ID " + habitacion.get().getId(), "habitacion");
            return "Habitacion eliminada";
        }
        return null;
    }
    public HabitacionDto obtenerHabitacionPorId(Integer id){
        Optional<Habitacion> habitacion = this.findById(id);
        if(habitacion.isPresent()){
            return mapper.ConvertEntityToDTO(habitacion.get());
        }
        return null;
    }
}
