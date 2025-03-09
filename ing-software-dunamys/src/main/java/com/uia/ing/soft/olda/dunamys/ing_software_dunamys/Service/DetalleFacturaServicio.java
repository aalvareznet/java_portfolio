package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.DetalleFactura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.DetalleFacturaRepositorio;

@Service
public class DetalleFacturaServicio extends BaseService<DetalleFactura, Long>{

    @Autowired
    private DetalleFacturaRepositorio repo;

    protected DetalleFacturaRepositorio getRepository() {
        return repo;    
    }

}
