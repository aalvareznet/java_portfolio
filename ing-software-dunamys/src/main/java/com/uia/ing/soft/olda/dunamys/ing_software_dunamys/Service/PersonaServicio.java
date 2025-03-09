package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Persona;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.PersonaRepositorio;

public class PersonaServicio extends BaseService<Persona, Long>{

    @Autowired
    private PersonaRepositorio repo;

    
    protected PersonaRepositorio getRepository() {
        return repo;
    }

}
