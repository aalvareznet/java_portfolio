package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Proveedor;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ProveedorRepositorio;

public class ProveedorServicio extends BaseService<Proveedor, Long>{


    @Autowired
    private ProveedorRepositorio repo;

    protected ProveedorRepositorio getRepository() {
        return repo;
    }

}
