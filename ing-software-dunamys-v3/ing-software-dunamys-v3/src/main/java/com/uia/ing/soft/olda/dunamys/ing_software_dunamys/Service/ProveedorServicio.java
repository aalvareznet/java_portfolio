package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ProveedorCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ProveedorDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.ProveedorMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Proveedor;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ProveedorRepositorio;

@Service
public class ProveedorServicio extends BaseService<Proveedor, Integer>{


    @Autowired
    private ProveedorRepositorio repo;
    @Autowired
    private LogAuditoriaServicio auditoria;
    @Autowired
    private ProveedorMapper mapper;

    protected ProveedorRepositorio getRepository() {
        return repo;
    }

    public ProveedorDto agregar(Integer usuarioId, ProveedorCrearDto proveedorDto){
        Proveedor proveedor = mapper.convertCreateToEntity(proveedorDto);
        Proveedor proveedorCreado = create(proveedor);
        if (proveedorCreado != null) {
            auditoria.guardarAccion(usuarioId, "Proveedor creado", "proveedor");
            return mapper.convertToDto(proveedorCreado);
        }
        return null;
    }

    public List<ProveedorDto> obtenerProveedores(){
        List<Proveedor> proveedores = this.findAll();
        List<ProveedorDto> proveedoresDto = proveedores.stream().map(proveedor -> mapper.convertToDto(proveedor)).toList();
        return proveedoresDto;
    }

    public ProveedorDto obtenerProveedor(Integer id){
        Optional<Proveedor> proveedor = this.findById(id);
        if(proveedor.isPresent()){
            return mapper.convertToDto(proveedor.get());
        }
        return null;
    }
    public ProveedorDto actualizar(Integer id, ProveedorCrearDto proveedorDto, Integer usuarioId){
        Optional<Proveedor> proveedorEncontrado = this.findById(id);
        if(proveedorEncontrado.isPresent()){
            Proveedor proveedorEntrante = mapper.convertCreateToEntity(proveedorDto);
            Proveedor proveedorParaActualizar = proveedorEncontrado.get();
            proveedorParaActualizar.setNombre(proveedorEntrante.getNombre());
            proveedorParaActualizar.setCorreoReferencia(proveedorEntrante.getCorreoReferencia());
            proveedorParaActualizar.setTelefono(proveedorEntrante.getTelefono());            
            Proveedor proveedorActualizado = this.update(proveedorParaActualizar);
            auditoria.guardarAccion(usuarioId, "Proveedor actualizado", "proveedor");
            return mapper.convertToDto(proveedorActualizado);
        }
        return null;
    }
    public String borrar(Integer usuarioId, Integer id){
        Optional<Proveedor> proveedor = this.findById(id);
        if(proveedor.isPresent()){
            this.delete(id);
            auditoria.guardarAccion(usuarioId, "Proveedor eliminado", "proveedor");
            return "Proveedor eliminado";
        }
        return null;
    }
}
