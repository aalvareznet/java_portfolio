package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ReservacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoReservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Reservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.TipoReservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ClienteServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.EstadoReservacionServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.HabitacionServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.TipoReservacionServicio;

@Component
public class ReservacionMapper {
    private final ModelMapper modelMapper;
    private final HabitacionServicio habitacionServicio;
    private final EstadoReservacionServicio estadoReservacionServicio;
    private final TipoReservacionServicio tipoReservacionServicio;
    private final ClienteServicio clienteServicio;

    public ReservacionMapper(ModelMapper modelMapper
                            , HabitacionServicio habitacionServicio
                            , EstadoReservacionServicio estadoReservacionServicio
                            , TipoReservacionServicio tipoReservacionServicio
                            , ClienteServicio clienteServicio) {
        this.habitacionServicio = habitacionServicio;
        this.estadoReservacionServicio = estadoReservacionServicio;
        this.tipoReservacionServicio = tipoReservacionServicio;
        this.clienteServicio = clienteServicio;
        this.modelMapper = modelMapper;
    }

    public ReservacionDto convertToDto(Reservacion reservacion) {
        ReservacionDto reservacionDto = modelMapper.map(reservacion, ReservacionDto.class);
        reservacionDto.setHabitacionId(reservacion.getHabitacion().getId());
        reservacionDto.setEstadoReservacionId(reservacion.getEstadoReservacion().getId());
        reservacionDto.setTipoReservacionId(reservacion.getTipoReservacion().getId());
        reservacionDto.setClienteId(reservacion.getCliente().getId());
        return reservacionDto;
    }

    public Reservacion convertToEntity(ReservacionDto reservacionDto) {
        Reservacion reservacion = modelMapper.map(reservacionDto, Reservacion.class);
        Optional<Habitacion> habitacion = habitacionServicio.findById(reservacionDto.getHabitacionId());
        Optional<EstadoReservacion> estadoReservacion = estadoReservacionServicio.findById(reservacionDto.getEstadoReservacionId());
        Optional<TipoReservacion> tipoReservacion = tipoReservacionServicio.findById(reservacionDto.getTipoReservacionId());
        Optional<Cliente> cliente = clienteServicio.findById(reservacionDto.getClienteId());
        reservacion.setHabitacion(habitacion.get());
        reservacion.setEstadoReservacion(estadoReservacion.get());
        reservacion.setTipoReservacion(tipoReservacion.get());
        reservacion.setCliente(cliente.get());
        return reservacion;
    }
}
