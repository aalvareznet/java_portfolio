package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.TipoHabitacion;

@Repository
public interface TipoHabitacionRepositorio extends JpaRepository<TipoHabitacion, Long>{

}
