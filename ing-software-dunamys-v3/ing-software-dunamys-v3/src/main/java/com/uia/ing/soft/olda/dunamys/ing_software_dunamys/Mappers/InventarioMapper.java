package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.CategoriaInventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Inventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Proveedor;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.CategoriaInventarioServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ProveedorServicio;


@Component
public class InventarioMapper {
    private final ModelMapper modelMapper;
    private final CategoriaInventarioServicio categoriaInventarioServicio;
    private final ProveedorServicio proveedorServicio;

    public InventarioMapper(ModelMapper modelMapper
                            , CategoriaInventarioServicio categoriaInventarioServicio
                            , ProveedorServicio proveedorServicio) {
        this.categoriaInventarioServicio = categoriaInventarioServicio;
        this.proveedorServicio = proveedorServicio;
        this.modelMapper = modelMapper;
    }

    public InventarioDto convertToDto(Inventario inventario) {
        InventarioDto inventarioDTO = modelMapper.map(inventario, InventarioDto.class);
        inventarioDTO.setCategoriaInventarioId(inventario.getCategoriaInventario().getId());
        inventarioDTO.setProveedorId(inventario.getProveedor().getId());
        return inventarioDTO;
    }

    public Inventario convertToEntity(InventarioDto inventarioDTO) {
        Inventario inventario = modelMapper.map(inventarioDTO, Inventario.class);
        Optional<CategoriaInventario> categoriaInventario = categoriaInventarioServicio.findById(inventarioDTO.getCategoriaInventarioId());
        Optional<Proveedor> proveedor = proveedorServicio.findById(inventarioDTO.getProveedorId());
        inventario.setCategoriaInventario(categoriaInventario.get());
        inventario.setProveedor(proveedor.get());
        return inventario;
    }
}
