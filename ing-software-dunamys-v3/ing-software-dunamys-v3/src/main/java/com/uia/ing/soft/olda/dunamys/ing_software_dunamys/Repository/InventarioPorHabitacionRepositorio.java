package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.InventarioPorHabitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.InventarioPorHabitacionTipo;

public interface InventarioPorHabitacionRepositorio extends JpaRepository<InventarioPorHabitacion, Integer>{
    List<InventarioPorHabitacion> findByReservacionId(Integer id);
    List<InventarioPorHabitacion> findByReservacionIdAndTipo(Integer id, InventarioPorHabitacionTipo tipo);
}
