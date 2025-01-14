package com.gersonandre.GersonAndre.service;

import com.gersonandre.GersonAndre.model.Client;
import com.gersonandre.GersonAndre.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class ClientService extends BaseService<Client, Long>{
    @Autowired
    private ClientRepository repo;

    @Override
    protected ClientRepository getRepository() {
        return repo;
    }
}
