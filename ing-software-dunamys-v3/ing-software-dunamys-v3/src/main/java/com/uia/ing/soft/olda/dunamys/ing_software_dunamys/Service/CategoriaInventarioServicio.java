package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.CategoriaInventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.CategoriaInventarioRepositorio;

@Service
public class CategoriaInventarioServicio extends BaseService<CategoriaInventario, Long>{

    @Autowired
    private CategoriaInventarioRepositorio repo;

    protected CategoriaInventarioRepositorio getRepository() {
       return repo;
    }

    

}
