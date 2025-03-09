package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoReservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.EstadoReservacionRepositorio;

@Service
public class EstadoReservacionServicio extends BaseService<EstadoReservacion, Long>{

    @Autowired
    private EstadoReservacionRepositorio repo;

    protected EstadoReservacionRepositorio getRepository() {
        return repo;    
    }

}
