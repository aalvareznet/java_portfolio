package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ConsumoDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Consumo;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Inventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ClienteServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.InventarioServicio;

@Component
public class ConsumoMapper {

    private final ModelMapper modelMapper;
    private final InventarioServicio inventarioServicio;
    private final ClienteServicio clienteServicio;

    public ConsumoMapper(ModelMapper modelMapper
                        , InventarioServicio inventarioServicio
                        , ClienteServicio clienteServicio) {
        this.modelMapper = modelMapper;
        this.inventarioServicio = inventarioServicio;
        this.clienteServicio = clienteServicio;
    }

    public Consumo ConvertDTOToEntity(ConsumoDto consumoDto) {
        Consumo consumo = modelMapper.map(consumoDto, Consumo.class);
        Optional<Inventario> inventario = inventarioServicio.findById(consumoDto.getInventarioId());
        Optional<Cliente> cliente = clienteServicio.findById(consumoDto.getClienteId());
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
