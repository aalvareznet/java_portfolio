package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioPorHabitacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioPorHabitacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Inventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.InventarioPorHabitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.InventarioPorHabitacionTipo;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Reservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.InventarioRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ReservacionRepositorio;

@Component
public class InventarioPorHabitacionMapper {
    private final ModelMapper modelMapper;
    private final ReservacionRepositorio reservacionRepositorio;
    private final InventarioRepositorio inventarioRepositorio;

    public InventarioPorHabitacionMapper(ModelMapper modelMapper, 
                                         ReservacionRepositorio reservacionRepositorio,
                                         InventarioRepositorio inventarioRepositorio) {
        this.modelMapper = modelMapper;
        this.reservacionRepositorio = reservacionRepositorio;
        this.inventarioRepositorio = inventarioRepositorio;
        configureModelMapper();
    }

    private void configureModelMapper(){
        modelMapper.addMappings(new PropertyMap<InventarioPorHabitacionCrearDto,InventarioPorHabitacion>() {
            @Override
            protected void configure(){
                skip(destination.getId());
            }
        });
    }
    public InventarioPorHabitacion ConvertDTOToEntity(InventarioPorHabitacionDto inventarioPorHabitacionDto){
        InventarioPorHabitacion inventarioPorHabitacion = modelMapper.map(inventarioPorHabitacionDto, InventarioPorHabitacion.class);
        Optional<Reservacion> reservacion = reservacionRepositorio.findById(inventarioPorHabitacionDto.getReservacionId());
        Optional<Inventario> inventario = inventarioRepositorio.findById(inventarioPorHabitacionDto.getInventarioId());
        InventarioPorHabitacionTipo tipo = InventarioPorHabitacionTipo.valueOf(inventarioPorHabitacionDto.getTipo().toUpperCase());
        inventarioPorHabitacion.setReservacion(reservacion.get());
        inventarioPorHabitacion.setInventario(inventario.get());
        inventarioPorHabitacion.setTipo(tipo);
        return inventarioPorHabitacion;
    }

    public InventarioPorHabitacion ConvertCreateDtoToEntity(InventarioPorHabitacionCrearDto inventarioPorHabitacionCrearDto){
        InventarioPorHabitacion inventarioPorHabitacion = modelMapper.map(inventarioPorHabitacionCrearDto, InventarioPorHabitacion.class);
        Optional<Reservacion> reservacion = reservacionRepositorio.findById(inventarioPorHabitacionCrearDto.getReservacionId());
        Optional<Inventario> inventario = inventarioRepositorio.findById(inventarioPorHabitacionCrearDto.getInventarioId());
        InventarioPorHabitacionTipo tipo = InventarioPorHabitacionTipo.valueOf(inventarioPorHabitacionCrearDto.getTipo().toUpperCase());
        inventarioPorHabitacion.setReservacion(reservacion.get());
        inventarioPorHabitacion.setInventario(inventario.get());
        inventarioPorHabitacion.setTipo(tipo);
        return inventarioPorHabitacion;
    }

    public InventarioPorHabitacionDto ConvertEntityToDto(InventarioPorHabitacion inventarioPorHabitacion){
        InventarioPorHabitacionDto inventarioPorHabitacionDto = modelMapper.map(inventarioPorHabitacion, InventarioPorHabitacionDto.class);
        inventarioPorHabitacionDto.setReservacionId(inventarioPorHabitacion.getReservacion().getId());
        inventarioPorHabitacionDto.setInventarioId(inventarioPorHabitacion.getInventario().getId());
        inventarioPorHabitacionDto.setTipo(inventarioPorHabitacion.getTipo().name());
        return inventarioPorHabitacionDto;
    }

}
