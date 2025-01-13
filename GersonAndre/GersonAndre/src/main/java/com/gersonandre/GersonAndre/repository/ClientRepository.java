package com.gersonandre.GersonAndre.repository;

import com.gersonandre.GersonAndre.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
