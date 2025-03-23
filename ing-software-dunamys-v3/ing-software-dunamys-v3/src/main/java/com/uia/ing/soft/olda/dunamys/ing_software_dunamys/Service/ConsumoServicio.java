package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.Date;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ConsumoCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ConsumoDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Exceptions.ClientNotFoundException;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.ConsumoMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Consumo;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ClienteRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ConsumoRepositorio;

@Service
public class ConsumoServicio extends BaseService<Consumo, Integer>{

    @Autowired
    private ConsumoRepositorio repo;
    @Autowired
    private ClienteRepositorio clienteRepo;
    @Autowired
    private LogAuditoriaServicio auditoria;
    @Autowired
    private ConsumoMapper mapper;

    protected ConsumoRepositorio getRepository() {
        return repo;
    }

    public ConsumoDto agregar(ConsumoCrearDto consumoDto, Integer usuarioId){
        Consumo consumo = mapper.ConvertCreateDTOToEntity(consumoDto);
        Consumo consumoResultante = this.create(consumo);
        if(consumoResultante != null){
            auditoria.guardarAccion(usuarioId, "Crear nuevo consumo", "consumo");
            return mapper.ConvertEntityToDTO(consumoResultante);
        }
        return null;
    }

    public ConsumoDto actualizar(Integer id, ConsumoCrearDto consumoDto, Integer usuarioId){
        Consumo consumo = mapper.ConvertCreateDTOToEntity(consumoDto);
        Optional<Consumo> busquedaConsumo = this.findById(id);
        if(busquedaConsumo.isPresent()){
            Consumo consumoParaActualizar = busquedaConsumo.get();
            consumoParaActualizar.setInventario(consumo.getInventario());
            consumoParaActualizar.setCantidad(consumo.getCantidad());
            consumoParaActualizar.setCliente(consumo.getCliente());
            consumoParaActualizar.setFecha(consumo.getFecha());
            Consumo consumoActualizado = this.update(consumoParaActualizar);
            auditoria.guardarAccion(usuarioId, "Actualizar consumo numero " + consumoActualizado.getId(), "consumo");
            return mapper.ConvertEntityToDTO(consumoActualizado);
        }
        return null;
    }

    public List<ConsumoDto> obtenerTodos(){
        List<Consumo> consumos = this.findAll();
        List<ConsumoDto> consumosDto = consumos.stream()
                                                .map(consumo -> mapper.ConvertEntityToDTO(consumo))
                                                .toList();
        return consumosDto;
    }

    public List<Consumo> obtenerConsumosPorFechaYCliente(Date fecha, Integer cliente){
        Optional<Cliente> clienteEncontrado = Optional.ofNullable(clienteRepo.findById(cliente).orElseThrow(()-> new ClientNotFoundException("No se encontro cliente con el ID " + cliente)));
        return repo.findByFechaAndCliente(fecha, clienteEncontrado.get());
    }
    public List<Consumo> obtenerConsumosEntreFechasYCliente(Date fechaInicio, Date fechaFin, Integer cliente){
        Optional<Cliente> clienteEncontrado = Optional.ofNullable(clienteRepo.findById(cliente).orElseThrow(()-> new ClientNotFoundException("No se encontro cliente con el ID " + cliente)));
        return repo.findByFechaBetweenAndCliente(fechaInicio, fechaFin, clienteEncontrado.get());
    }

}
