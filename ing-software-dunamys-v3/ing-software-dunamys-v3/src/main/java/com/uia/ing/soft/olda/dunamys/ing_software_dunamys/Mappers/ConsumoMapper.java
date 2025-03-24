package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ConsumoCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ConsumoDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Consumo;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Inventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ClienteRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.InventarioRepositorio;

@Component
public class ConsumoMapper {

    private final ModelMapper modelMapper;
    private final InventarioRepositorio inventarioRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    public ConsumoMapper(ModelMapper modelMapper
                        , InventarioRepositorio inventarioRepositorio
                        , ClienteRepositorio clienteRepositorio) {
        this.modelMapper = modelMapper;
        this.inventarioRepositorio = inventarioRepositorio;
        this.clienteRepositorio = clienteRepositorio;
    }

    public Consumo ConvertDTOToEntity(ConsumoDto consumoDto) {
        Consumo consumo = modelMapper.map(consumoDto, Consumo.class);
        Optional<Inventario> inventario = inventarioRepositorio.findById(consumoDto.getInventarioId());
        Optional<Cliente> cliente = clienteRepositorio.findById(consumoDto.getClienteId());
        consumo.setCliente(cliente.get());
        consumo.setInventario(inventario.get());
        return consumo;
    }
    public Consumo ConvertCreateDTOToEntity(ConsumoCrearDto consumoDto) {
        Consumo consumo = modelMapper.map(consumoDto, Consumo.class);
        Optional<Inventario> inventario = inventarioRepositorio.findById(consumoDto.getInventarioId());
        Optional<Cliente> cliente = clienteRepositorio.findById(consumoDto.getClienteId());
        consumo.setCliente(cliente.get());
        consumo.setInventario(inventario.get());
        return consumo;
    }

    public ConsumoDto ConvertEntityToDTO(Consumo consumo) {
        ConsumoDto consumoDto = modelMapper.map(consumo, ConsumoDto.class);
        consumoDto.setClienteId(consumo.getCliente().getId());
        consumoDto.setInventarioId(consumo.getInventario().getId());
        return consumoDto;
    }

}
