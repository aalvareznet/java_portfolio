package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.HabitacionRepositorio;

public class HabitacionServicio extends BaseService<Habitacion, Long>{

    @Autowired
    private HabitacionRepositorio repo;

    protected HabitacionRepositorio getRepository() {
        return repo;
    }

}
