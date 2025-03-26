package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer>{
    Optional<Cliente> findByUsuarioId(Integer id);
}
