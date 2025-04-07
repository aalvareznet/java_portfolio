package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Consumo;

@Repository
public interface ConsumoRepositorio extends JpaRepository<Consumo, Integer>{
    public List<Consumo> findByFechaAndCliente(@Param("date") Date date, @Param("cliente") Cliente cliente);
    public List<Consumo> findByFechaBetweenAndCliente(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("cliente") Cliente cliente);
}
