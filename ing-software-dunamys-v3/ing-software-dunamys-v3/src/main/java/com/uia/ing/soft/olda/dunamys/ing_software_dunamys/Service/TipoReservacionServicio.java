package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.TipoReservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.TipoReservacionRepositorio;

@Service
public class TipoReservacionServicio extends BaseService<TipoReservacion, Long>{

    @Autowired
    private TipoReservacionRepositorio repo;

    @Override
    protected TipoReservacionRepositorio getRepository() {
        return repo;
    }
}
