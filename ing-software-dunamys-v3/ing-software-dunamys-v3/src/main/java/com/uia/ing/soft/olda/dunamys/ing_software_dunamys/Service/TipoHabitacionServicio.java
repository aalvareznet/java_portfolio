package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.TipoHabitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.TipoHabitacionRepositorio;

@Service
public class TipoHabitacionServicio extends BaseService<TipoHabitacion, Integer>{


    @Autowired
    private TipoHabitacionRepositorio repo;

    protected TipoHabitacionRepositorio getRepository() {
        return repo;
    }

}
