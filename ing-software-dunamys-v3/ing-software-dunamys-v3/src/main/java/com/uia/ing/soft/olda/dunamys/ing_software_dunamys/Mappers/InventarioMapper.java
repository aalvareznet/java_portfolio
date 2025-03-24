package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.CategoriaInventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Inventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Proveedor;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.CategoriaInventarioRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ProveedorRepositorio;


@Component
public class InventarioMapper {
    private final ModelMapper modelMapper;
    private final CategoriaInventarioRepositorio categoriaInventarioRepositorio;
    private final ProveedorRepositorio proveedorRepositorio;

    public InventarioMapper(ModelMapper modelMapper
                            , CategoriaInventarioRepositorio categoriaInventarioRepositorio
                            , ProveedorRepositorio proveedorRepositorio) {
        this.categoriaInventarioRepositorio = categoriaInventarioRepositorio;
        this.proveedorRepositorio = proveedorRepositorio;
        this.modelMapper = modelMapper;
    }
    public Inventario convertToEntity(InventarioDto inventarioDTO) {
        Inventario inventario = modelMapper.map(inventarioDTO, Inventario.class);
        Optional<CategoriaInventario> categoriaInventario = categoriaInventarioRepositorio.findById(inventarioDTO.getCategoriaInventarioId());
        Optional<Proveedor> proveedor = proveedorRepositorio.findById(inventarioDTO.getProveedorId());
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
        Optional<CategoriaInventario> categoriaOpt = categoriaInventarioRepositorio.findById(dto.getCategoriaInventarioId());
        if(categoriaOpt.isPresent()) {
            inventario.setCategoriaInventario(categoriaOpt.get());
        } else {
            throw new IllegalArgumentException("No se encontró CategoriaInventario con id: " + dto.getCategoriaInventarioId());
        }

        // Mapeo del proveedor de forma similar
        Optional<Proveedor> proveedorOpt = proveedorRepositorio.findById(dto.getProveedorId());
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
