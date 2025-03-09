package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.TipoHabitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.TipoHabitacionRepositorio;

public class TipoReservacionRepositorio extends BaseService<TipoHabitacion, Long>{

    @Autowired
    private TipoHabitacionRepositorio repo;

    protected TipoHabitacionRepositorio getRepository() {
        return repo;
    }

}
