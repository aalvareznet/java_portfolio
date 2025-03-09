package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Reservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.HabitacionRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ReservacionRepositorio;

@Service
public class ReservacionServicio extends BaseService<Reservacion, Long>{

    @Autowired
    private ReservacionRepositorio repo;
    @Autowired
    private HabitacionRepositorio habitacionRepo;

    protected ReservacionRepositorio getRepository() {
        return repo;
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
