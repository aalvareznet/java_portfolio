package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoHabitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.EstadoHabitacionRepositorio;

@Service
public class EstadoHabitacionServicio extends BaseService<EstadoHabitacion, Long>{

    @Autowired
    private EstadoHabitacionRepositorio repo;

    protected EstadoHabitacionRepositorio getRepository() {
        return repo;
    }

}
