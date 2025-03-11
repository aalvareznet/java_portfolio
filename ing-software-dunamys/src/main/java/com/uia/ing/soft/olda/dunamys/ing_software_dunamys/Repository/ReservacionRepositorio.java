package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Reservacion;
@Repository
public interface ReservacionRepositorio extends JpaRepository<Reservacion, Long>{

    @Query("SELECT r FROM Reservacion r WHERE r.habitacion.id = :habitacionId " +
            "AND (:fechaIngreso BETWEEN r.fechaIngreso AND r.fechaSalida " +
            "OR :fechaSalida BETWEEN r.fechaIngreso AND r.fechaSalida)")
    List<Reservacion> findReservasPorHabitacionYFechas(Integer habitacionId, Date fechaIngreso, Date fechaSalida);

    @Query("SELECT r FROM Reservacion r WHERE :fechaIngreso BETWEEN r.fechaIngreso AND r.fechaSalida " +
            "OR :fechaSalida BETWEEN r.fechaIngreso AND r.fechaSalida")
    List<Reservacion> findReservasEnRangoDeFechas(Date fechaIngreso, Date fechaSalida);
}
