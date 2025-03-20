package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.HabitacionRepositorio;

@Service
public class HabitacionServicio extends BaseService<Habitacion, Integer>{

    @Autowired
    private HabitacionRepositorio repo;

    protected HabitacionRepositorio getRepository() {
        return repo;
    }

}
