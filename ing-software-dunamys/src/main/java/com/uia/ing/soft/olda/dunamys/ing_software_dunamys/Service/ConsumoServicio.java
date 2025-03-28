package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.Date;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Exceptions.ClientNotFoundException;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Consumo;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ClienteRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ConsumoRepositorio;

@Service
public class ConsumoServicio extends BaseService<Consumo, Long>{

    @Autowired
    private ConsumoRepositorio repo;
    @Autowired
    private ClienteRepositorio clienteRepo;

    protected ConsumoRepositorio getRepository() {
        return repo;
    }

    public List<Consumo> obtenerConsumosPorFechaYCliente(Date fecha, Long cliente){
        Optional<Cliente> clienteEncontrado = Optional.ofNullable(clienteRepo.findById(cliente).orElseThrow(()-> new ClientNotFoundException("No se encontro cliente con el ID " + cliente)));
        return repo.findByFechaAndCliente(fecha, clienteEncontrado.get());
    }
    public List<Consumo> obtenerConsumosEntreFechasYCliente(Date fechaInicio, Date fechaFin, Long cliente){
        Optional<Cliente> clienteEncontrado = Optional.ofNullable(clienteRepo.findById(cliente).orElseThrow(()-> new ClientNotFoundException("No se encontro cliente con el ID " + cliente)));
        return repo.findByFechaBetweenAndCliente(fechaInicio, fechaFin, clienteEncontrado.get());
    }

}
