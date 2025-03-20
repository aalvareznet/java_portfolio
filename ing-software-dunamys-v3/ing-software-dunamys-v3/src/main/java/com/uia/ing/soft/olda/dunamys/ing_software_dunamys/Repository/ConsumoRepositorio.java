package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Consumo;

@Repository
public interface ConsumoRepositorio extends JpaRepository<Consumo, Integer>{
    public List<Consumo> findByFechaAndCliente(Date date, Cliente cliente);
    public List<Consumo> findByFechaBetweenAndCliente(Date fechaInicio, Date fechaFin, Cliente cliente);
}
