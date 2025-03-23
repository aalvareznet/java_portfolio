package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.InventarioDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.InventarioMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Inventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.InventarioRepositorio;

@Service
public class InventarioServicio extends BaseService<Inventario, Integer>{

    @Autowired
    private InventarioRepositorio repo;

    @Autowired
    private LogAuditoriaServicio auditoria;

    @Autowired
    private InventarioMapper mapper;

    protected InventarioRepositorio getRepository() {
        return repo;
    }
    public InventarioDto agregar(Integer usuarioId, InventarioCrearDto inventarioDto){
        Inventario inventario = mapper.convertCreateToEntity(inventarioDto);
        Inventario inventarioAgregado = create(inventario);
        if(inventarioAgregado != null){
            auditoria.guardarAccion(usuarioId, "Item de inventario agregado", "Inventario");
            return mapper.convertToDto(inventarioAgregado);
        }
        return null;
    }
    public List<InventarioDto> obtenerInventarios(){
        List<Inventario> inventarios = this.findAll();
        List<InventarioDto> inventarioDto = inventarios.stream()
                                                        .map(inventario -> mapper.convertToDto(inventario))
                                                        .toList();
        return inventarioDto;
    }
    public InventarioDto obtenerInventario(Integer id){
        Optional<Inventario> inventario = this.findById(id);
        if(inventario.isPresent()){
            return mapper.convertToDto(inventario.get());
        }
        return null;
    }
    public InventarioDto actualizar(Integer id, InventarioCrearDto inventarioDto, Integer usuarioId){
        Optional<Inventario> busquedaInventario = this.findById(id);
        if(busquedaInventario.isPresent()){
            Inventario inventarioConvertido = mapper.convertCreateToEntity(inventarioDto);
            Inventario inventarioParaActualizar = busquedaInventario.get();
            inventarioParaActualizar.setNombreItem(inventarioConvertido.getNombreItem());     
            inventarioParaActualizar.setStock(inventarioConvertido.getStock());
            inventarioParaActualizar.setPrecio(inventarioConvertido.getPrecio());
            inventarioParaActualizar.setCategoriaInventario(inventarioConvertido.getCategoriaInventario());
            inventarioParaActualizar.setProveedor(inventarioConvertido.getProveedor());
            Inventario inventarioActualizado = this.update(inventarioParaActualizar);     
            auditoria.guardarAccion(usuarioId, "Item de inventario actualizado", "Inventario");
            return mapper.convertToDto(inventarioActualizado);
        }
        return null;
    }
    public InventarioDto agregarPedido(Integer id, Integer cantidad, Integer usuarioId){
        Optional<Inventario> busquedaInventario = this.findById(id);
        if(busquedaInventario.isPresent()){
            Inventario inventarioParaActualizar = busquedaInventario.get();
            inventarioParaActualizar.setStock(inventarioParaActualizar.getStock()+cantidad);
            Inventario inventarioActualizado = this.update(inventarioParaActualizar);
            auditoria.guardarAccion(usuarioId, "Pedido agregado", "Inventario");
            return mapper.convertToDto(inventarioActualizado);
        }
        return null;
    }
    public String borrar(Integer id, Integer usuarioId){
        Optional<Inventario> busquedaInventario = this.findById(id);
        if(busquedaInventario.isPresent()){
            this.delete(id);
            auditoria.guardarAccion(usuarioId, "Item de inventario eliminado", "Inventario");
            return "Item de inventario eliminado";
        }
        return null;
    }
}
