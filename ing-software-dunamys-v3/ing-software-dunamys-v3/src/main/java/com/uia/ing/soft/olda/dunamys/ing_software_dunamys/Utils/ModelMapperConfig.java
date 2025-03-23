package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Utils;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ReservacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoReservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Reservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.TipoReservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ClienteServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.EstadoReservacionServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.HabitacionServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.TipoReservacionServicio;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(HabitacionServicio habitacionServicio,
                                   ClienteServicio clienteServicio,
                                   TipoReservacionServicio tipoReservacionServicio,
                                   EstadoReservacionServicio estadoReservacionServicio) {
        ModelMapper modelMapper = new ModelMapper();

        // Converter para mapear habitacionId a Habitacion
        Converter<Integer, Habitacion> habitacionConverter = new AbstractConverter<Integer, Habitacion>() {
            @Override
            protected Habitacion convert(Integer source) {
                return source != null ? habitacionServicio.findById(source).get() : null;
            }
        };

        // Converter para mapear clienteId a Cliente
        Converter<Integer, Cliente> clienteConverter = new AbstractConverter<Integer, Cliente>() {
            @Override
            protected Cliente convert(Integer source) {
                return source != null ? clienteServicio.findById(source).get() : null;
            }
        };

        // Converter para mapear tipoReservacionId a TipoReservacion
        Converter<Integer, TipoReservacion> tipoReservacionConverter = new AbstractConverter<Integer, TipoReservacion>() {
            @Override
            protected TipoReservacion convert(Integer source) {
                return source != null ? tipoReservacionServicio.findById(source).get() : null;
            }
        };

        // Converter para mapear estadoReservacionId a EstadoReservacion
        Converter<Integer, EstadoReservacion> estadoReservacionConverter = new AbstractConverter<Integer, EstadoReservacion>() {
            @Override
            protected EstadoReservacion convert(Integer source) {
                return source != null ? estadoReservacionServicio.findById(source).get() : null;
            }
        };

        // Configuración explícita para mapear ReservacionCrearDto a Reservacion
        modelMapper.addMappings(new PropertyMap<ReservacionCrearDto, Reservacion>() {
            @Override
            protected void configure() {
                // Ignoramos el id de destino para evitar conflictos
                skip(destination.getId());
                // Mapeo de cada id a la entidad correspondiente utilizando los convertidores
                using(habitacionConverter).map(source.getHabitacionId(), destination.getHabitacion());
                using(clienteConverter).map(source.getClienteId(), destination.getCliente());
                using(tipoReservacionConverter).map(source.getTipoReservacionId(), destination.getTipoReservacion());
                using(estadoReservacionConverter).map(source.getEstadoReservacionId(), destination.getEstadoReservacion());
            }
        });

        return modelMapper;
    }
}
