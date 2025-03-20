package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Factura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.FacturaRepositorio;

@Service
public class FacturaServicio extends BaseService<Factura, Integer>{

    @Autowired
    private FacturaRepositorio repo;

    protected FacturaRepositorio getRepository() {
        return repo;
    }

}
