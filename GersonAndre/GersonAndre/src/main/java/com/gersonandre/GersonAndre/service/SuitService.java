package com.gersonandre.GersonAndre.service;

import com.gersonandre.GersonAndre.model.Suit;
import com.gersonandre.GersonAndre.repository.SuitRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SuitService extends BaseService<Suit, Long>{
    @Autowired
    private SuitRepository repo;
    @Override
    protected SuitRepository getRepository() {
        return repo;
    }



}
