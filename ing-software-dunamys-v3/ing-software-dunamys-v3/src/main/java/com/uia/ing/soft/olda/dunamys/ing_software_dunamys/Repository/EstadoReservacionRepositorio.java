package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoReservacion;

@Repository
public interface EstadoReservacionRepositorio extends JpaRepository<EstadoReservacion, Integer>{

}
