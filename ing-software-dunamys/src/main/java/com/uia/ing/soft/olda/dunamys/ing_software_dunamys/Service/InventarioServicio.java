package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Inventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.InventarioRepositorio;

public class InventarioServicio extends BaseService<Inventario, Long>{

    @Autowired
    private InventarioRepositorio repo;

    protected InventarioRepositorio getRepository() {
        return repo;
    }

    
}
