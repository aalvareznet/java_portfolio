package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.HabitacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.HabitacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoHabitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.TipoHabitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.EstadoHabitacionServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.TipoHabitacionServicio;

@Component
public class HabitacionMapper {
    private final ModelMapper modelMapper;
    private final EstadoHabitacionServicio estadoHabitacionServicio;
    private final TipoHabitacionServicio tipoHabitacionServicio;

    public HabitacionMapper(ModelMapper modelMapper
                          , EstadoHabitacionServicio estadoHabitacionServicio
                          , TipoHabitacionServicio tipoHabitacionServicio) {
        this.estadoHabitacionServicio = estadoHabitacionServicio;
        this.tipoHabitacionServicio = tipoHabitacionServicio;
        this.modelMapper = modelMapper;
    }

    public Habitacion ConvertDTOToEntity(HabitacionDto habitacionDto) {
        Habitacion habitacion = modelMapper.map(habitacionDto, Habitacion.class);
        Optional<EstadoHabitacion> estadoHabitacion = estadoHabitacionServicio.findById(habitacionDto.getEstadoHabitacionId());
        Optional<TipoHabitacion> tipoHabitacion = tipoHabitacionServicio.findById(habitacionDto.getTipoHabitacionId());
        habitacion.setEstadoHabitacion(estadoHabitacion.get());
        habitacion.setTipoHabitacion(tipoHabitacion.get());
        return habitacion;
    }
    public Habitacion ConvertDTOToEntity(HabitacionCrearDto habitacionDto) {
        Habitacion habitacion = modelMapper.map(habitacionDto, Habitacion.class);
        Optional<EstadoHabitacion> estadoHabitacion = estadoHabitacionServicio.findById(habitacionDto.getEstadoHabitacionId());
        Optional<TipoHabitacion> tipoHabitacion = tipoHabitacionServicio.findById(habitacionDto.getTipoHabitacionId());
        habitacion.setEstadoHabitacion(estadoHabitacion.get());
        habitacion.setTipoHabitacion(tipoHabitacion.get());
        return habitacion;
    }

    public HabitacionDto ConvertEntityToDTO(Habitacion habitacion) {
        HabitacionDto habitacionDto = modelMapper.map(habitacion, HabitacionDto.class);
        habitacionDto.setEstadoHabitacionId(habitacion.getEstadoHabitacion().getId());
        habitacionDto.setTipoHabitacionId(habitacion.getTipoHabitacion().getId());
        return habitacionDto;
    }


}
