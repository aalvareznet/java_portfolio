package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Inventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.InventarioRepositorio;

@Service
public class InventarioServicio extends BaseService<Inventario, Integer>{

    @Autowired
    private InventarioRepositorio repo;

    protected InventarioRepositorio getRepository() {
        return repo;
    }

    
}
