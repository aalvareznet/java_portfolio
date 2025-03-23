package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ReservacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ReservacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.ReservacionMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Reservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.HabitacionRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ReservacionRepositorio;

@Service
public class ReservacionServicio extends BaseService<Reservacion, Integer>{

    @Autowired
    private ReservacionRepositorio repo;
    @Autowired
    private HabitacionRepositorio habitacionRepo;
    @Autowired
    private LogAuditoriaServicio auditoria;
    @Autowired
    private ReservacionMapper mapper;

    protected ReservacionRepositorio getRepository() {
        return repo;
    }
    public ReservacionDto agregar(ReservacionCrearDto reservacionDto){
        Reservacion reservacion = mapper.ConvertCreateDTOToEntity(reservacionDto);
        Reservacion reservacionAgregada = this.create(reservacion);
        if(reservacionAgregada != null){
            return mapper.ConvertEntityToDto(reservacionAgregada);
        }
        return null;
    }

    public List<ReservacionDto> obtenerReservaciones(){
        List<Reservacion> reservaciones = this.findAll();
        List<ReservacionDto> reservacionesDto = reservaciones.stream()
                                                            .map(reservacion -> mapper.ConvertEntityToDto(reservacion))
                                                            .collect(Collectors.toList());
        return reservacionesDto;
    }

    public ReservacionDto obtenerReservacion(Integer id){
        Optional<Reservacion> reservacionBuscada = this.findById(id);
        if(reservacionBuscada.isPresent()){
            return mapper.ConvertEntityToDto(reservacionBuscada.get());
        }
        return null;
    }

    public ReservacionDto actualizar(Integer id, ReservacionCrearDto reservacionDto, Integer usuarioId){
        Optional<Reservacion> reservacionBuscada = this.findById(id);
        if(reservacionBuscada.isPresent()){
            Reservacion reservacionEntrante = mapper.ConvertCreateDTOToEntity(reservacionDto);
            Reservacion reservacionParaActualizar = reservacionBuscada.get();
            reservacionParaActualizar.setCantidadPersonas(reservacionEntrante.getCantidadPersonas());
            reservacionParaActualizar.setCliente(reservacionEntrante.getCliente());
            reservacionParaActualizar.setEstadoReservacion(reservacionEntrante.getEstadoReservacion());
            reservacionParaActualizar.setFechaIngreso(reservacionEntrante.getFechaIngreso());
            reservacionParaActualizar.setFechaSalida(reservacionEntrante.getFechaSalida());
            reservacionParaActualizar.setHabitacion(reservacionEntrante.getHabitacion());
            reservacionParaActualizar.setTipoReservacion(reservacionEntrante.getTipoReservacion());
            auditoria.guardarAccion(usuarioId, "Reservacion numero " + reservacionParaActualizar.getId() + " actualizada", "reservaciones");
            Reservacion reservacionActualizada = this.update(reservacionParaActualizar);
            return mapper.ConvertEntityToDto(reservacionActualizada);
        }
        return null;
    }



    public boolean verificarDisponibilidadHabitacion(Integer habitacionId, Date fechaIngreso, Date fechaSalida) {
        List<Reservacion> reservas = repo.findReservasPorHabitacionYFechas(habitacionId, fechaIngreso, fechaSalida);
        return reservas.isEmpty(); // Si no hay reservas en ese rango, está disponible
    }

    // Retorna una lista de habitaciones disponibles en un rango de fechas
    public List<Habitacion> obtenerHabitacionesDisponibles(Date fechaIngreso, Date fechaSalida) {
        List<Habitacion> todasLasHabitaciones = habitacionRepo.findAll();
        
        // Filtrar habitaciones ocupadas en ese rango de fechas
        List<Integer> habitacionesOcupadas = repo.findReservasEnRangoDeFechas(fechaIngreso, fechaSalida)
                .stream().map(reservacion -> reservacion.getHabitacion().getId()).collect(Collectors.toList());

        return todasLasHabitaciones.stream()
                .filter(habitacion -> !habitacionesOcupadas.contains(habitacion.getId()))
                .collect(Collectors.toList());
    }

}
