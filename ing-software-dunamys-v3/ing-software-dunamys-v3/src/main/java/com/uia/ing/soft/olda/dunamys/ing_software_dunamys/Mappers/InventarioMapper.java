package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioCrearDto;
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
    public Inventario convertToEntity(InventarioDto inventarioDTO) {
        Inventario inventario = modelMapper.map(inventarioDTO, Inventario.class);
        Optional<CategoriaInventario> categoriaInventario = categoriaInventarioServicio.findById(inventarioDTO.getCategoriaInventarioId());
        Optional<Proveedor> proveedor = proveedorServicio.findById(inventarioDTO.getProveedorId());
        inventario.setCategoriaInventario(categoriaInventario.get());
        inventario.setProveedor(proveedor.get());
        return inventario;
    }
    
    public Inventario convertCreateDtoToEntity(InventarioCrearDto dto) {
        Inventario inventario = new Inventario();
        // Se asignan los campos directos
        inventario.setNombreItem(dto.getNombreItem());
        inventario.setStock(dto.getStock());
        inventario.setPrecio(dto.getPrecio());
        // No se asigna ningún id ni campo de versión, para que Hibernate genere la entidad nueva

        // Mapeo de la categoría: se consulta y se asigna, de lo contrario se lanza una excepción controlada.
        Optional<CategoriaInventario> categoriaOpt = categoriaInventarioServicio.findById(dto.getCategoriaInventarioId());
        if(categoriaOpt.isPresent()) {
            inventario.setCategoriaInventario(categoriaOpt.get());
        } else {
            throw new IllegalArgumentException("No se encontró CategoriaInventario con id: " + dto.getCategoriaInventarioId());
        }

        // Mapeo del proveedor de forma similar
        Optional<Proveedor> proveedorOpt = proveedorServicio.findById(dto.getProveedorId());
        if(proveedorOpt.isPresent()) {
            inventario.setProveedor(proveedorOpt.get());
        } else {
            throw new IllegalArgumentException("No se encontró Proveedor con id: " + dto.getProveedorId());
        }

        return inventario;
    }
    public InventarioDto convertToDto(Inventario inventario) {
        InventarioDto inventarioDTO = modelMapper.map(inventario, InventarioDto.class);
        inventarioDTO.setCategoriaInventarioId(inventario.getCategoriaInventario().getId());
        inventarioDTO.setProveedorId(inventario.getProveedor().getId());
        return inventarioDTO;
    }


}
