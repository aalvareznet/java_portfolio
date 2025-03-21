package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Proveedor;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ProveedorRepositorio;

@Service
public class ProveedorServicio extends BaseService<Proveedor, Integer>{


    @Autowired
    private ProveedorRepositorio repo;

    protected ProveedorRepositorio getRepository() {
        return repo;
    }

}
