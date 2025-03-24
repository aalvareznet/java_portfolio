package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ReservacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ReservacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoReservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Reservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.TipoReservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ClienteRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.EstadoReservacionRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.HabitacionRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.TipoReservacionRepositorio;

@Component
public class ReservacionMapper {
    private final ModelMapper modelMapper;
    private final HabitacionRepositorio habitacionRepositorio;
    private final EstadoReservacionRepositorio estadoReservacionRepositorio;
    private final TipoReservacionRepositorio tipoReservacionRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    public ReservacionMapper(ModelMapper modelMapper
                            , HabitacionRepositorio habitacionRepositorio
                            , EstadoReservacionRepositorio estadoReservacionRepositorio
                            , TipoReservacionRepositorio tipoReservacionRepositorio
                            , ClienteRepositorio clienteRepositorio) {
        this.habitacionRepositorio = habitacionRepositorio;
        this.estadoReservacionRepositorio = estadoReservacionRepositorio;
        this.tipoReservacionRepositorio = tipoReservacionRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.modelMapper = modelMapper;
    }
    public Reservacion ConvertDTOToEntity(ReservacionDto reservacionDto) {
        Reservacion reservacion = modelMapper.map(reservacionDto, Reservacion.class);
        Optional<Habitacion> habitacion = habitacionRepositorio.findById(reservacionDto.getHabitacionId());
        Optional<EstadoReservacion> estadoReservacion = estadoReservacionRepositorio.findById(reservacionDto.getEstadoReservacionId());
        Optional<TipoReservacion> tipoReservacion = tipoReservacionRepositorio.findById(reservacionDto.getTipoReservacionId());
        Optional<Cliente> cliente = clienteRepositorio.findById(reservacionDto.getClienteId());
        reservacion.setHabitacion(habitacion.get());
        reservacion.setEstadoReservacion(estadoReservacion.get());
        reservacion.setTipoReservacion(tipoReservacion.get());
        reservacion.setCliente(cliente.get());
        return reservacion;
    }
    public Reservacion ConvertCreateDTOToEntity(ReservacionCrearDto reservacionDto) {
        Reservacion reservacion = modelMapper.map(reservacionDto, Reservacion.class);
        Optional<Habitacion> habitacion = habitacionRepositorio.findById(reservacionDto.getHabitacionId());
        Optional<EstadoReservacion> estadoReservacion = estadoReservacionRepositorio.findById(reservacionDto.getEstadoReservacionId());
        Optional<TipoReservacion> tipoReservacion = tipoReservacionRepositorio.findById(reservacionDto.getTipoReservacionId());
        Optional<Cliente> cliente = clienteRepositorio.findById(reservacionDto.getClienteId());
        reservacion.setHabitacion(habitacion.get());
        reservacion.setEstadoReservacion(estadoReservacion.get());
        reservacion.setTipoReservacion(tipoReservacion.get());
        reservacion.setCliente(cliente.get());
        return reservacion;
    }
    public ReservacionDto ConvertEntityToDto(Reservacion reservacion) {
        ReservacionDto reservacionDto = modelMapper.map(reservacion, ReservacionDto.class);
        reservacionDto.setHabitacionId(reservacion.getHabitacion().getId());
        reservacionDto.setEstadoReservacionId(reservacion.getEstadoReservacion().getId());
        reservacionDto.setTipoReservacionId(reservacion.getTipoReservacion().getId());
        reservacionDto.setClienteId(reservacion.getCliente().getId());
        return reservacionDto;
    }


}
