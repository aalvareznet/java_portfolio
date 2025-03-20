package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import org.modelmapper.ModelMapper;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ConsumoDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Consumo;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ClienteServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.InventarioServicio;

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
        return consumo;
    }

    public ConsumoDto ConvertEntityToDTO(Consumo consumo) {
        ConsumoDto consumoDto = modelMapper.map(consumo, ConsumoDto.class);
        return consumoDto;
    }

}
