package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.HabitacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.HabitacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoHabitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.TipoHabitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.EstadoHabitacionRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.TipoHabitacionRepositorio;

@Component
public class HabitacionMapper {
    @Lazy
    private final ModelMapper modelMapper;
    private final EstadoHabitacionRepositorio estadoHabitacionRepositorio;
    private final TipoHabitacionRepositorio tipoHabitacionRepositorio;

    public HabitacionMapper(ModelMapper modelMapper,
                            EstadoHabitacionRepositorio estadoHabitacionRepositorio,
                            TipoHabitacionRepositorio tipoHabitacionRepositorio) {
        this.estadoHabitacionRepositorio = estadoHabitacionRepositorio;
        this.tipoHabitacionRepositorio = tipoHabitacionRepositorio;
        this.modelMapper = modelMapper;
        configureModelMapper();
    }

    private void configureModelMapper() {
        modelMapper.addMappings(new PropertyMap<HabitacionCrearDto, Habitacion>() {
            @Override
            protected void configure() {
                skip(destination.getId()); // Skip the id property to avoid conflicts
            }
        });
    }

    public Habitacion ConvertDTOToEntity(HabitacionDto habitacionDto) {
        Habitacion habitacion = modelMapper.map(habitacionDto, Habitacion.class);
        Optional<EstadoHabitacion> estadoHabitacion = estadoHabitacionRepositorio.findById(habitacionDto.getEstadoHabitacionId());
        Optional<TipoHabitacion> tipoHabitacion = tipoHabitacionRepositorio.findById(habitacionDto.getTipoHabitacionId());
        habitacion.setEstadoHabitacion(estadoHabitacion.get());
        habitacion.setTipoHabitacion(tipoHabitacion.get());
        return habitacion;
    }

    public Habitacion ConvertCreateDTOToEntity(HabitacionCrearDto habitacionDto) {
        Habitacion habitacion = modelMapper.map(habitacionDto, Habitacion.class);
        Optional<EstadoHabitacion> estadoHabitacion = estadoHabitacionRepositorio.findById(habitacionDto.getEstadoHabitacionId());
        Optional<TipoHabitacion> tipoHabitacion = tipoHabitacionRepositorio.findById(habitacionDto.getTipoHabitacionId());
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
