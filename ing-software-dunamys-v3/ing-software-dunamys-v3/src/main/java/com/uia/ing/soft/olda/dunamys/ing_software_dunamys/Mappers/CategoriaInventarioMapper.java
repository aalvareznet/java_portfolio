package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import org.modelmapper.ModelMapper;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.CategoriaInventarioDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.CategoriaInventario;

public class CategoriaInventarioMapper {

    private final ModelMapper modelMapper;

    public CategoriaInventarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoriaInventario ConvertDTOToEntity(CategoriaInventario categoriaInventarioDTO) {
        return modelMapper.map(categoriaInventarioDTO, CategoriaInventario.class);
    }
    public CategoriaInventarioDto ConvertEntityToDTO(CategoriaInventario categoriaInventario) {
        return modelMapper.map(categoriaInventario, CategoriaInventarioDto.class);
    }
}
