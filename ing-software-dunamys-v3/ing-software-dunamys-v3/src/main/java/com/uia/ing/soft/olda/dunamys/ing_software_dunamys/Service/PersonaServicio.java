package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Persona;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.PersonaRepositorio;

@Service
public class PersonaServicio extends BaseService<Persona, Integer>{

    @Autowired
    private PersonaRepositorio repo;

    
    protected PersonaRepositorio getRepository() {
        return repo;
    }

}
