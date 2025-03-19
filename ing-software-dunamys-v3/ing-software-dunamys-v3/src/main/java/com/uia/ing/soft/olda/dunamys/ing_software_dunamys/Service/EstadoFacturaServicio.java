package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoFactura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.EstadoFacturaRepositorio;

@Service
public class EstadoFacturaServicio extends BaseService<EstadoFactura, Long>{

    @Autowired
    private EstadoFacturaRepositorio repo;

    protected EstadoFacturaRepositorio getRepository() {
        return repo;    
    }

}
